package com.example.whattosee.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.whattosee.broadcastreceiver.*
import com.example.whattosee.model.Category
import com.example.whattosee.model.PageDTO
import com.example.whattosee.model.UrlLoader
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.toFilms
import com.google.gson.Gson

const val LOAD_SERVICE_STRING_EXTRA = "LoadServiceExtra"

class LoadService(val name: String = "LoadService") : IntentService(name) {
    companion object{
        private const val FILMS_URL = "https://api.themoviedb.org/3/movie/popular?language=ru&page=1"
        private const val TAG = "LoadService"
    }

    override fun onHandleIntent(intentIn: Intent?) {
        Log.d(TAG, "LoadService start work with intent")
        val intent = Intent(FILMS_BROADCAST_INTENT)
        try {
            val body = UrlLoader().load(FILMS_URL)
            val pageDTO = Gson().fromJson(body, PageDTO::class.java)
            Log.d(TAG, "LoadService end work with intent")
            val categories = createCategories(pageDTO)
            intent.putParcelableArrayListExtra(RESULT_EXTRA, categories)
            intent.putExtra(DETAILS_LOAD_RESULT_EXTRA, DETAILS_RESPONSE_SUCCESS_EXTRA)
        } catch (e: Throwable) {
            Log.e(TAG, e.message.toString())
            intent.putExtra(RESULT_EXTRA, e)
            intent.putExtra(DETAILS_LOAD_RESULT_EXTRA, DETAILS_REQUEST_ERROR_MESSAGE_EXTRA)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        Log.d(TAG, "Broadcast send")
    }

    private fun createCategories(pageDTO: PageDTO): ArrayList<Category>{
        val films = pageDTO.toFilms()
        return arrayListOf(
            Category(1, "Все", films),
            Category(2, "По рейтингу", films),
            Category(3, "Смотреть позже", films),
            Category(4, "Оцененные мной", films),
            Category(5, "Просмотренные", films),
        )
    }
}