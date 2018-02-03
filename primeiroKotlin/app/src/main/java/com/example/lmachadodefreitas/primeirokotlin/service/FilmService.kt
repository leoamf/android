package com.example.lmachadodefreitas.primeirokotlin.service

import retrofit2.http.GET
import com.example.lmachadodefreitas.primeirokotlin.DTO.Films
import retrofit2.Call



/**
 * Created by l.machado.de.freitas on 06/01/2018.
 */
interface FilmService {
    @GET("films")
    fun list():   Call<Films>
}
