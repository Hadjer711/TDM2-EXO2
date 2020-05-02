package com.example.tdm2exo2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.util.Log

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_crud_intervention_.InputDate
import kotlinx.android.synthetic.main.content_database.*
import java.util.*


class MainActivity : AppCompatActivity() , InterventionAdapter.OnIntervListener{
    var array = arrayOf<Intervention>()
    var interventionList : MutableList<Intervention> ?= null

     var layoutManager: LinearLayoutManager ?=  null


    val c = Calendar.getInstance()
    val annee = c.get(Calendar.YEAR)
    val mois = c.get(Calendar.MONTH)
    val jour = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext, "data.json")
        Log.i("data", jsonFileString)

        val gson = Gson()
        val listPersonType = object : TypeToken<MutableList<Intervention>>() {}.type

        interventionList = gson.fromJson(jsonFileString, listPersonType)
        Log.d("TAG", interventionList.toString())
        interventionList?.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }

        initRecyclerView()
        val activity = this
            val  fab =findViewById(R.id.fab) as View
            fab.setOnClickListener {
                val intent = Intent(activity, InterventionCrud_Activity::class.java)
                intent.putExtra("mode","ajout")
                startActivity(intent)

            }

        searchDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, annee, mois, jour ->
                InputDate.setText("$jour-${mois+1}-$annee")
                c.set(annee, mois,jour, 0, 0)
                print("dateee seaarch"+c.toString())


                initRecyclerView()

            }, annee, mois, jour)

            datePickerDialog.show()
        }




    }

    override fun OnItemClick(item: Intervention, position: Int) {
        Toast.makeText(this, item.nom , Toast.LENGTH_SHORT).show()
        val intent = Intent(this, InterventionCrud_Activity::class.java)
        intent.putExtra("mode","modif")
        intent.putExtra("pos", position)

        startActivity(intent)
    }
    fun initRecyclerView(){
        interventionList?.addAll(array)
        val recyclerView =findViewById(R.id.recyclerView) as RecyclerView
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter =InterventionAdapter(this, this)
        recyclerView.adapter = adapter

    }




    }








