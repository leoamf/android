package com.example.lmachadodefreitas.primeirokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lmachadodefreitas.primeirokotlin.DTO.Films
import com.example.lmachadodefreitas.primeirokotlin.config.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ArrayAdapter



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = RetrofitInitializer().filmService().list()
        call.enqueue(object : Callback<Films?> {
            override fun onResponse(call: Call<Films?>?,
                                    response: Response<Films?>?) {
                response?.body()?.let {
                    val notes: Films = it
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<Films?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }


    private fun configureList(filmes: Films) {

        val adapter = ArrayAdapter<Films.Film>(this,
                android.R.layout.simple_list_item_1,  filmes.results)

        listItens.adapter = adapter

    }
}
