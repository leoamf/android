package com.example.lmachadodefreitas.primeirokotlin.config

import com.example.lmachadodefreitas.primeirokotlin.service.FilmService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by l.machado.de.freitas on 06/01/2018.
 */
class RetrofitInitializer {


    private val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun filmService() = retrofit.create(FilmService::class.java)

}