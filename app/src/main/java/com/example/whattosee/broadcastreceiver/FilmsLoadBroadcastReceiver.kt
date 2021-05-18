package com.example.whattosee.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.whattosee.R
import com.example.whattosee.model.Category
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.view.MainFragment

const val FILMS_BROADCAST_INTENT = "FILMS_BROADCAST_INTENT"
const val RESULT_EXTRA = "FILMS_EXTRA"
const val DETAILS_LOAD_RESULT_EXTRA = "DETAILS_LOAD_RESULT_EXTRA"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "DETAILS_RESPONSE_SUCCESS_EXTRA"
const val DETAILS_REQUEST_ERROR_MESSAGE_EXTRA = "DETAILS_REQUEST_ERROR_MESSAGE_EXTRA "

class FilmsLoadBroadcastReceiver(val mainFragment: MainFragment) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (it.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                DETAILS_RESPONSE_SUCCESS_EXTRA -> {
                    val categories = it.getParcelableArrayListExtra<Category>(RESULT_EXTRA)
                    categories?.let {
                        mainFragment.renderData(CategoriesDataState.Success(it))
                    }
                }
                DETAILS_REQUEST_ERROR_MESSAGE_EXTRA -> {
                    val errorMessage = it.getStringExtra(RESULT_EXTRA)
                    errorMessage?.let {
                        mainFragment.renderData(
                            CategoriesDataState.Error(
                                Throwable(
                                    it
                                )
                            )
                        )
                    }
                }

                else -> {
                    mainFragment.renderData(
                        CategoriesDataState.Error(
                            Throwable(
                                mainFragment.resources.getString(R.string.unknown_error)
                            )
                        )
                    )
                }

            }
        }
    }
}