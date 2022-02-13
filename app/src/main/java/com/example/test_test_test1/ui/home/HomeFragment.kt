package com.example.test_test_test1.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test_test_test1.R
import com.example.test_test_test1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var alfa_romeo: ImageView? = null
    var audi: ImageView? = null
    var bentley: ImageView? = null
    var borgward: ImageView? = null
    var bugatti: ImageView? = null
    var citroen: ImageView? = null
    var dacia: ImageView? = null
    var ds: ImageView? = null
    var lamborghini: ImageView? = null
    var ferrari: ImageView? = null
    var jaguar: ImageView? = null
    var land_rover: ImageView? = null
    var lotus: ImageView? = null
    var mercedes_benz: ImageView? = null
    var mini: ImageView? = null
    var opel: ImageView? = null
    var peugeot: ImageView? = null
    var polestar: ImageView? = null
    var porsche: ImageView? = null
    var renault: ImageView? = null
    var rolls_royce: ImageView? = null
    var saab: ImageView? = null
    var santana: ImageView? = null
    var seat: ImageView? = null
    var skoda: ImageView? = null
    var smart: ImageView? = null
    var volkswagen: ImageView? = null
    var volvo: ImageView? = null
    var bmw: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        alfa_romeo = root.findViewById(R.id.alfa_romeo)
        audi = root.findViewById(R.id.audi)
        bentley = root.findViewById(R.id.bentley)
        bmw = root.findViewById(R.id.bmw)
        borgward = root.findViewById(R.id.borgward)
        bugatti = root.findViewById(R.id.bugatti)
        citroen = root.findViewById(R.id.citroen)
        dacia = root.findViewById(R.id.dacia)
        ds = root.findViewById(R.id.ds)
        lamborghini = root.findViewById(R.id.lamborghini)
        ferrari = root.findViewById(R.id.ferrari)
        jaguar = root.findViewById(R.id.jaguar)
        land_rover = root.findViewById(R.id.land_rover)
        lotus = root.findViewById(R.id.lotus)
        mercedes_benz = root.findViewById(R.id.mercedes_benz)
        mini = root.findViewById(R.id.mini)
        opel = root.findViewById(R.id.opel)
        peugeot = root.findViewById(R.id.peugeot)
        polestar = root.findViewById(R.id.polestar)
        porsche = root.findViewById(R.id.porsche)
        renault = root.findViewById(R.id.renault)
        rolls_royce = root.findViewById(R.id.rolls_royce)
        saab = root.findViewById(R.id.saab)
        santana = root.findViewById(R.id.santana)
        seat = root.findViewById(R.id.seat)
        skoda = root.findViewById(R.id.skoda)
        smart = root.findViewById(R.id.smart)
        volkswagen = root.findViewById(R.id.volkswagen)
        volvo = root.findViewById(R.id.volvo)


        // Открываем Activity_brand_open через  -> action
        val intent = Intent("brand_open")
        alfa_romeo?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "alfa_romeo") // Ключ и значение
            startActivity(intent)
        }
        audi?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "audi") // Ключ и значение
            startActivity(intent)
        }
        bentley?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "bentley") // Ключ и значение
            startActivity(intent)
        }
        borgward?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "borgward") // Ключ и значение
            startActivity(intent)
        }
        bugatti?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "bugatti") // Ключ и значение
            startActivity(intent)
        }
        citroen?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "citroen") // Ключ и значение
            startActivity(intent)
        }
        dacia?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "dacia") // Ключ и значение
            startActivity(intent)
        }
        ds?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "ds") // Ключ и значение
            startActivity(intent)
        }
        lamborghini?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "lamborghini") // Ключ и значение
            startActivity(intent)
        }
        ferrari?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "ferrari") // Ключ и значение
            startActivity(intent)
        }
        jaguar?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "jaguar") // Ключ и значение
            startActivity(intent)
        }
        land_rover?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "land-rover") // Ключ и значение
            startActivity(intent)
        }
        lotus?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "lotus") // Ключ и значение
            startActivity(intent)
        }
        mercedes_benz?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "mercedes") // Ключ и значение
            startActivity(intent)
        }
        mini?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "mini") // Ключ и значение
            startActivity(intent)
        }
        opel?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "opel") // Ключ и значение
            startActivity(intent)
        }
        peugeot?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "peugeot") // Ключ и значение
            startActivity(intent)
        }
        polestar?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "polestar") // Ключ и значение
            startActivity(intent)
        }
        porsche?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "porsche") // Ключ и значение
            startActivity(intent)
        }
        renault?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "renault") // Ключ и значение
            startActivity(intent)
        }
        rolls_royce?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "rolls-royce") // Ключ и значение
            startActivity(intent)
        }
        saab?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "saab") // Ключ и значение
            startActivity(intent)
        }
        santana?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "santana") // Ключ и значение
            startActivity(intent)
        }
        skoda?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "skoda") // Ключ и значение
            startActivity(intent)
        }
        seat?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "seat") // Ключ и значение
            startActivity(intent)
        }
        smart?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "smart") // Ключ и значение
            startActivity(intent)
        }
        volkswagen?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "volkswagen") // Ключ и значение
            startActivity(intent)
        }
        volvo?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "volvo") // Ключ и значение
            startActivity(intent)
        }
        bmw?.setOnClickListener() {
            // Отправляем сообщение, что именно эта картинка была нажата
            intent.putExtra("brand_name", "bmw") // Ключ и значение
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}