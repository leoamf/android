package com.example.lmachadodefreitas.primeirokotlin.DTO

/**
 * Created by l.machado.de.freitas on 06/01/2018.
 */



class Films {

    var results: List<Film>? = null


    inner class Film(val title: String,
                     val opening_crawl: String)
    {
        override fun toString(): String {
            return title
        }
    }
}
