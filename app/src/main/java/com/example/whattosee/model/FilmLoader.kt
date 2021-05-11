package com.example.whattosee.model

import android.util.Log
import com.example.whattosee.BuildConfig
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class FilmLoader() {
    companion object{
        private const val READ_TIME_OUT = 10000
        private const val TAG = "FilmLoader"
    }


    fun loadFilms():FilmsDTO{
        val url = "https://api.kinopoisk.cloud/movies/all/page/1/token/${BuildConfig.FILMS_API_KEY}"
        Log.d(TAG, url)
        val uri = URL(url)
        val urlConnection = uri.openConnection() as HttpsURLConnection
        urlConnection.apply {
            requestMethod = "GET"
//            urlConnection.addRequestProperty()
            urlConnection.readTimeout = READ_TIME_OUT
        }
        val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
        //ответ от сервера - десериализация в объект
        val body = getLines(bufferedReader)
        Log.d("bodygoogle", body)
        val filmsDTO = Gson().fromJson(body, FilmsDTO::class.java)
        return filmsDTO
    }

    fun getLines(reader: BufferedReader):String{
        return reader.lines().collect(Collectors.joining("\n"))
    }
}