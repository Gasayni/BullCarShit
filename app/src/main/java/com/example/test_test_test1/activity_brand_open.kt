package com.example.test_test_test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

// этот класс будет принимать всю веб страницу в тестовом виде и запишется в этот объект
// по отдельным элементам
var doc: Document? = null

//    var doc: Document? = null
private var secThread // Создаем второстепенный поток для парсинга сайта
        : Thread? = null
private var runnable: Runnable? = null

var iv_brand: ImageView? = null
var tv_about_brand: TextView? = null
private var brand_name_for_getWeb = "null"

class activity_brand_open : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_open)

        iv_brand = findViewById<ImageView>(R.id.iv_brand)
        tv_about_brand = findViewById<TextView>(R.id.tv_about_brand)
        val intent = intent

        // Проверяет сообщение, которое пришло от фрагмента и подставляет нужную картинку марки авто
        when (intent.getStringExtra("brand_name")) {
            "alfa_romeo" -> iv_brand?.setImageResource(R.drawable.alfa_romeo)
            "bmw" -> iv_brand?.setImageResource(R.drawable.bmw)
            "bentley" -> iv_brand?.setImageResource(R.drawable.bentley)
            "borgward" -> iv_brand?.setImageResource(R.drawable.borgward)
            "bugatti" -> iv_brand?.setImageResource(R.drawable.bugatti)
            "citroen" -> iv_brand?.setImageResource(R.drawable.citroen)
            "dacia" -> iv_brand?.setImageResource(R.drawable.dacia)
            "ds" -> iv_brand?.setImageResource(R.drawable.ds)
            "lamborghini" -> iv_brand?.setImageResource(R.drawable.lamborghini)
            "ferrari" -> iv_brand?.setImageResource(R.drawable.ferrari)
            "jaguar" -> iv_brand?.setImageResource(R.drawable.jaguar)
            "land-rover" -> iv_brand?.setImageResource(R.drawable.land_rover)
            "lotus" -> iv_brand?.setImageResource(R.drawable.lotus)
            "mercedes" -> iv_brand?.setImageResource(R.drawable.mercedes_benz)
            "mini" -> iv_brand?.setImageResource(R.drawable.mini)
            "opel" -> iv_brand?.setImageResource(R.drawable.opel)
            "peugeot" -> iv_brand?.setImageResource(R.drawable.peugeot)
            "polestar" -> iv_brand?.setImageResource(R.drawable.polestar)
            "porsche" -> iv_brand?.setImageResource(R.drawable.porsche)
            "renault" -> iv_brand?.setImageResource(R.drawable.renault)
            "rolls-royce" -> iv_brand?.setImageResource(R.drawable.rolls_royce)
            "saab" -> iv_brand?.setImageResource(R.drawable.saab)
            "santana" -> iv_brand?.setImageResource(R.drawable.santana)
            "seat" -> iv_brand?.setImageResource(R.drawable.seat)
            "skoda" -> iv_brand?.setImageResource(R.drawable.skoda)
            "smart" -> iv_brand?.setImageResource(R.drawable.smart)
            "volkswagen" -> iv_brand?.setImageResource(R.drawable.volkswagen)
            "volvo" -> iv_brand?.setImageResource(R.drawable.volvo)
            "audi" -> iv_brand?.setImageResource(R.drawable.audi)

            else -> iv_brand?.setImageResource(R.drawable.top) // если не прокатит, то покажет стрелку верх
        }
        brand_name_for_getWeb = intent.getStringExtra("brand_name")!!
        init()
    }

    // Функция для запуска второстепенного потока
    private fun init() {
        runnable = Runnable { getWeb() }
        secThread = Thread(runnable)
        secThread!!.start()
    }

    private fun getWeb() {
        doc = when (brand_name_for_getWeb) {
            "alfa_romeo" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/alfa-romeo")
                .get()
            "audi" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/audi").get()
            "bentley" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/bentley")
                .get()
            "borgward" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/borgward")
                .get()
            "bugatti" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/bugatti")
                .get()
            "citroen" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/citroen")
                .get()
            "dacia" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/dacia").get()
            "ds" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/ds").get()
            "lamborghini" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/lamborghini")
                .get()
            "ferrari" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/ferrari")
                .get()
            "jaguar" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/jaguar").get()
            "land-rover" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/land-rover")
                .get()
            "lotus" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/lotus").get()
            "mercedes" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/mercedes")
                .get()
            "mini" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/mini").get()
            "opel" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/opel").get()
            "peugeot" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/peugeot")
                .get()
            "polestar" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/polestar")
                .get()
            "porsche" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/porsche")
                .get()
            "renault" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/renault")
                .get()
            "rolls-royce" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/rolls-royce")
                .get()
            "saab" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/saab").get()
            "santana" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/santana")
                .get()
            "seat" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/seat").get()
            "skoda" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/skoda").get()
            "smart" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/smart").get()
            "volkswagen" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/vw-volkswagen")
                .get()
            "volvo" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/volvo").get()
            "bmw" -> Jsoup.connect("https://auto.ironhorse.ru/category/europe/bmw").get()
            else -> Jsoup.connect("https://auto.ironhorse.ru/category/usa/tesla-motors").get()
        }
        val data: Elements = doc!!.getElementsByTag("p")
        val data1: org.jsoup.nodes.Element? = data.get(0)
        runOnUiThread {
            tv_about_brand?.text = data1?.text()
        }
    }
}