package com.example.whattosee

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.showSnackBar(
    message: String,
    action: View.OnClickListener? = null,
    actionText: String = "",
    duration: Int = Snackbar.LENGTH_INDEFINITE,
) {
    Snackbar.make(this, message, duration)
        .apply { action?.let { setAction(actionText, it) } }
        .show()
}

fun View.showSnackBar(
    messageResId: Int,
    action: View.OnClickListener? = null,
    actionText: String = "",
    duration: Int = Snackbar.LENGTH_INDEFINITE,
){
    this.showSnackBar(resources.getString(messageResId),action, actionText, duration)
}