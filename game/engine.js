/*
 * Цветной рейс — игровая логика без графики.
 *
 * Поле — сетка w×h. Каждая машина занимает len клеток в линию и смотрит
 * в одну сторону (U/D/L/R). По тапу машина едет по стрелке; она может уехать,
 * только если до края поля на её пути нет других машин.
 *
 * Уехавшая машина встаёт в свободный слот (самый левый), и в неё садятся
 * пассажиры её цвета из начала очереди. Полная машина уезжает и освобождает слот.
 *
 * Уровни генерируются так, чтобы решение существовало всегда:
 *  1. Машины добавляются по одной, и каждая новая принимается, только если
 *     поле по-прежнему можно полностью разобрать (проверка жадная: уезд
 *     машины только освобождает пути, поэтому жадный разбор полон).
 *  2. Берётся порядок разбора поля, и по нему строится очередь пассажиров:
 *     одновременно «на парковке» не больше parkAhead машин, пассажиры
 *     перемешиваются между ними. Пассажиров каждого цвета ровно столько,
 *     сколько мест в машинах этого цвета.
 *  3. Готовый уровень прогоняется через simulate() по тем же правилам, что
 *     в игре. Если проверка не прошла, берётся следующий seed.
 */
(function (root, factory) {
  const api = factory();
  if (typeof module === 'object' && module.exports) module.exports = api;
  else root.BusEngine = api;
})(typeof self !== 'undefined' ? self : this, function () {
  'use strict';

  const DIRS = { U: [0, -1], D: [0, 1], L: [-1, 0], R: [1, 0] };
  const CAP_BY_LEN = { 2: 4, 3: 6, 4: 8 };

  const LEVELS = [
    { name: 'Разминка', w: 6, h: 7, colors: 3, lens: [2, 2, 3], attempts: 600,
      minVehicles: 10, maxVehicles: 13, parkAhead: 1, slots: 4, seed: 1101 },
    { name: 'Час пик', w: 8, h: 9, colors: 5, lens: [2, 2, 3], attempts: 1500,
      minVehicles: 20, maxVehicles: 26, parkAhead: 2, slots: 4, seed: 2202 },
    { name: 'Большая пробка', w: 9, h: 11, colors: 6, lens: [2, 3, 3], attempts: 2500,
      minVehicles: 26, maxVehicles: 34, parkAhead: 3, slots: 4, seed: 3303 },
  ];

  function rng(seed) {
    let a = seed >>> 0;
    return function () {
      a = (a + 0x6d2b79f5) | 0;
      let t = Math.imul(a ^ (a >>> 15), 1 | a);
      t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t;
      return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
    };
  }

  function shuffle(arr, rand) {
    for (let i = arr.length - 1; i > 0; i--) {
      const j = Math.floor(rand() * (i + 1));
      [arr[i], arr[j]] = [arr[j], arr[i]];
    }
    return arr;
  }

  const isHorizontal = (v) => v.dir === 'L' || v.dir === 'R';

  function cellsOf(v) {
    const out = [];
    const h = isHorizontal(v);
    for (let i = 0; i < v.len; i++) out.push(h ? [v.x + i, v.y] : [v.x, v.y + i]);
    return out;
  }

  function makeOcc(w, h, vehicles) {
    const occ = new Array(w * h).fill(-1);
    for (const v of vehicles) for (const [x, y] of cellsOf(v)) occ[y * w + x] = v.id;
    return occ;
  }

  function clearOcc(occ, w, v) {
    for (const [x, y] of cellsOf(v)) if (occ[y * w + x] === v.id) occ[y * w + x] = -1;
  }

  /** Первая машина на пути v к краю поля: { id, dist }; id = -1, если путь свободен. */
  function firstBlocker(v, occ, w, h) {
    const [dx, dy] = DIRS[v.dir];
    let x, y;
    if (v.dir === 'R') { x = v.x + v.len; y = v.y; }
    else if (v.dir === 'L') { x = v.x - 1; y = v.y; }
    else if (v.dir === 'D') { x = v.x; y = v.y + v.len; }
    else { x = v.x; y = v.y - 1; }
    let dist = 0;
    while (x >= 0 && y >= 0 && x < w && y < h) {
      const id = occ[y * w + x];
      if (id !== -1 && id !== v.id) return { id, dist };
      x += dx; y += dy; dist++;
    }
    return { id: -1, dist };
  }

  /** Порядок, в котором можно убрать все машины, или null, если поле заклинило. */
  function removalOrder(w, h, vehicles, rand) {
    const occ = makeOcc(w, h, vehicles);
    let left = vehicles.slice();
    const order = [];
    while (left.length) {
      const free = left.filter((v) => firstBlocker(v, occ, w, h).id === -1);
      if (!free.length) return null;
      const v = rand ? free[Math.floor(rand() * free.length)] : free[0];
      clearOcc(occ, w, v);
      left = left.filter((u) => u !== v);
      order.push(v.id);
    }
    return order;
  }

  function generateBoard(rand, cfg) {
    const { w, h } = cfg;
    const vehicles = [];
    for (let a = 0; a < cfg.attempts && vehicles.length < cfg.maxVehicles; a++) {
      const len = cfg.lens[Math.floor(rand() * cfg.lens.length)];
      const horiz = rand() < 0.5;
      const maxX = horiz ? w - len : w - 1;
      const maxY = horiz ? h - 1 : h - len;
      if (maxX < 0 || maxY < 0) continue;
      const x = Math.floor(rand() * (maxX + 1));
      const y = Math.floor(rand() * (maxY + 1));
      const occ = makeOcc(w, h, vehicles);
      const probe = { id: vehicles.length, x, y, len, dir: horiz ? 'R' : 'D' };
      if (cellsOf(probe).some(([cx, cy]) => occ[cy * w + cx] !== -1)) continue;
      const dirs = shuffle(horiz ? ['L', 'R'] : ['U', 'D'], rand);
      for (const dir of dirs) {
        vehicles.push({ ...probe, dir });
        if (removalOrder(w, h, vehicles)) break;
        vehicles.pop();
      }
    }
    // Цвета раздаются по кругу, чтобы все цвета встречались примерно поровну.
    const colors = shuffle(vehicles.map((_, i) => i % cfg.colors), rand);
    vehicles.forEach((v, i) => {
      v.color = colors[i];
      v.cap = CAP_BY_LEN[v.len];
    });
    return vehicles;
  }

  /** Очередь пассажиров по порядку уезда машин: не больше parkAhead машин ждут одновременно. */
  function buildQueue(rand, vehicles, order, parkAhead) {
    const queue = [];
    const waiting = [];
    let i = 0;
    while (i < order.length || waiting.length) {
      while (waiting.length < parkAhead && i < order.length) {
        const v = vehicles[order[i++]];
        waiting.push({ color: v.color, left: v.cap });
      }
      const k = Math.floor(rand() * waiting.length);
      queue.push(waiting[k].color);
      if (--waiting[k].left === 0) waiting.splice(k, 1);
    }
    return queue;
  }

  /**
   * Проигрывает уровень по правилам игры: машины уезжают в порядке order,
   * паркуются в самый левый свободный слот, пассажир садится в самый левый
   * слот своего цвета. На парковке держим не больше maxParked машин.
   */
  function simulate(level, order, maxParked) {
    const { w, h, vehicles, queue } = level;
    const occ = makeOcc(w, h, vehicles);
    const slots = new Array(level.slots).fill(null);
    let head = 0;
    let next = 0;
    const limit = Math.min(maxParked, level.slots);
    for (;;) {
      while (head < queue.length) {
        const s = slots.findIndex((p) => p && p.color === queue[head] && p.seats > 0);
        if (s === -1) break;
        head++;
        if (--slots[s].seats === 0) slots[s] = null;
      }
      const parked = slots.filter(Boolean).length;
      if (head === queue.length && next === order.length) return parked === 0;
      if (next < order.length && parked < limit) {
        const v = vehicles[order[next++]];
        if (firstBlocker(v, occ, w, h).id !== -1) return false;
        clearOcc(occ, w, v);
        slots[slots.indexOf(null)] = { color: v.color, seats: v.cap };
        continue;
      }
      return false;
    }
  }

  function countsMatch(level) {
    const need = {};
    for (const v of level.vehicles) need[v.color] = (need[v.color] || 0) + v.cap;
    for (const c of level.queue) need[c] = (need[c] || 0) - 1;
    return Object.values(need).every((n) => n === 0);
  }

  const cache = new Map();

  function makeLevel(cfg) {
    if (cache.has(cfg)) return cache.get(cfg);
    for (let k = 0; k < 500; k++) {
      const rand = rng(cfg.seed + k * 7919);
      const vehicles = generateBoard(rand, cfg);
      if (vehicles.length < cfg.minVehicles) continue;
      const order = removalOrder(cfg.w, cfg.h, vehicles, rand);
      if (!order) continue;
      const queue = buildQueue(rand, vehicles, order, cfg.parkAhead);
      const level = { name: cfg.name, w: cfg.w, h: cfg.h, slots: cfg.slots, vehicles, queue, solution: order };
      if (!countsMatch(level) || !simulate(level, order, cfg.parkAhead)) continue;
      cache.set(cfg, level);
      return level;
    }
    throw new Error('Не удалось сгенерировать уровень ' + cfg.name);
  }

  return {
    DIRS, CAP_BY_LEN, LEVELS,
    rng, cellsOf, makeOcc, clearOcc, firstBlocker, removalOrder,
    generateBoard, buildQueue, simulate, countsMatch, makeLevel,
  };
});
