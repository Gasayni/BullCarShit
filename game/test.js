// Проверка уровней: node game/test.js
const E = require('./engine.js');

let ok = true;
E.LEVELS.forEach((cfg, i) => {
  const t = Date.now();
  const lv = E.makeLevel(cfg);
  const ms = Date.now() - t;
  const counts = E.countsMatch(lv);
  const solved = E.simulate(lv, lv.solution, cfg.parkAhead);
  const solvedFullSlots = E.simulate(lv, lv.solution, lv.slots);
  const cells = lv.vehicles.reduce((s, v) => s + v.len, 0);
  console.log(
    `Уровень ${i + 1} «${lv.name}»: ${lv.w}×${lv.h}, машин ${lv.vehicles.length}, ` +
      `заполнено ${Math.round((100 * cells) / (lv.w * lv.h))}%, пассажиров ${lv.queue.length}, ` +
      `счёт по цветам ${counts ? 'сходится' : 'НЕ сходится'}, решение ${solved ? 'есть' : 'НЕТ'}, ` +
      `с полными слотами ${solvedFullSlots ? 'тоже проходит' : 'не проходит'} (${ms} мс)`
  );
  if (!counts || !solved) ok = false;
});
process.exit(ok ? 0 : 1);
