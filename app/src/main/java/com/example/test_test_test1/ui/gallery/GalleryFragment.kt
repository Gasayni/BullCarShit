package com.example.test_test_test1.ui.gallery

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test_test_test1.MyDBHelper
import com.example.test_test_test1.MyDBManager
import com.example.test_test_test1.MyDBNameClass
import com.example.test_test_test1.R
import com.example.test_test_test1.databinding.FragmentGalleryBinding
import java.util.ArrayList

private lateinit var galleryViewModel: GalleryViewModel
private var _binding: FragmentGalleryBinding? = null
private val binding get() = _binding!!
var brand_container = ArrayList<String?>()
var model_container = ArrayList<String?>()
var year_container = ArrayList<String?>()

class GalleryFragment() : Fragment() {
    var thiscontext: Context? = null

//    val myDBManager = activity?.let { MyDBManager(it.applicationContext) }


    var brandActv: AutoCompleteTextView? = null
    var modelActv: AutoCompleteTextView? = null
    var yearActv: AutoCompleteTextView? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        brandActv = root.findViewById<View>(R.id.brandActv) as AutoCompleteTextView?
        modelActv = root.findViewById<View>(R.id.modelActv) as AutoCompleteTextView?
        yearActv = root.findViewById<View>(R.id.yearActv) as AutoCompleteTextView?

        thiscontext = container?.getContext();
        val myDBManager = thiscontext?.let { MyDBManager(it) }
        myDBManager?.openDB()
        val brandList = myDBManager!!.readDBDataBrand() // эта ф-я возвращает нам массив
        val modelList = myDBManager!!.readDBDataModel() // эта ф-я возвращает нам массив
        val yearList = myDBManager!!.readDBDataYear() // эта ф-я возвращает нам массив
        for (item in brandList) {
            // Если остальные Actv пустые
            if (modelActv?.text!!.isEmpty() && yearActv?.text!!.isEmpty()) {
                if (!brand_container.contains(item)) {
                    brand_container.add(item)
                }
            } else if (!yearActv?.text!!.isEmpty()) {  // Иначе, если год Actv уже выбран
                MyDBNameClass.data = yearActv?.text.toString() // записываем выбранную дату

            }

        }
        for (item in modelList) {
            if (!model_container.contains(item)) {
                model_container.add(item)
            }
        }
        for (item in yearList) {
            if (!year_container.contains(item)) {
                year_container.add(item)
            }
        }

        actv_constructor_brand()
        actv_constructor_model()
        actv_constructor_year()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun actv_constructor_brand() {
        var adapter =
            activity?.let {
                ArrayAdapter(it, android.R.layout.simple_list_item_1, brand_container)
            }
        brandActv!!.threshold = 1 //will start working from first character
        brandActv!!.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
        if (!brand_container.isEmpty()) {
            Log.d("MyLog", ", b.size() = " + brandActv!!.textSize)
        } else Log.d("MyLog", "brand_container пустой!")
    }

    fun actv_constructor_model() {
        var adapter =
            activity?.let {
                ArrayAdapter(it, android.R.layout.simple_list_item_1, model_container)
            }
        modelActv!!.threshold = 1 //will start working from first character
        modelActv!!.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
        if (!model_container.isEmpty()) {
            Log.d("MyLog", ", b.size() = " + modelActv!!.textSize)
        } else Log.d("MyLog", "brand_container пустой!")
    }

    fun actv_constructor_year() {
        var adapter =
            activity?.let {
                ArrayAdapter(it, android.R.layout.simple_list_item_1, year_container)
            }
        yearActv!!.threshold = 1 //will start working from first character
        yearActv!!.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
        if (!year_container.isEmpty()) {
            Log.d("MyLog", ", b.size() = " + yearActv!!.textSize)
        } else Log.d("MyLog", "brand_container пустой!")
    }

}

// У нас есть 3 списка: бренд, год и модель
// можно начать с любой, тогда все просто, будет показываться весь список
// Если один ACTV выбран, то второй должен операться на него, например
// Выбран бренд Тайота, тогда годы выпуска или модель должны подходить только под этот бренд
// или Выбран год, аналогично, Если у брендов имеется модели с таким годом выпуска, то показываются

// Мы работаем с таблицей в БД. Т.о. нужно сравнивать все подходящие из модели с брендом