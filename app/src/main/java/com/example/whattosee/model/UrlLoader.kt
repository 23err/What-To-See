package com.example.whattosee.model

import com.example.whattosee.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class UrlLoader {
    companion object{
        private const val READ_TIME_OUT = 10000
    }
    fun load(urlString:String): String{
        val url = urlString + "&api_key=${BuildConfig.FILMS_API_KEY}"
        val uri = URL(url)
        val urlConnection = uri.openConnection() as HttpsURLConnection
        urlConnection.apply {
            requestMethod = "GET"
            urlConnection.readTimeout = READ_TIME_OUT
        }
        val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
        return getLines(bufferedReader)
    }

    fun getLines(reader: BufferedReader):String{
        return reader.lines().collect(Collectors.joining("\n"))
    }
}