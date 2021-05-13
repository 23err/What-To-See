package com.example.whattosee

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import java.io.IOException
import java.net.URL

class ImageLoaderTask(
    val imageView:AppCompatImageView
) : AsyncTask<String, Void, Bitmap?>() {
    companion object{
        private const val TAG = "DownloadImageTask";
    }

    override fun doInBackground(vararg p0: String?): Bitmap? {
        val imageUrl = p0[0]
        var image: Bitmap? = null
//        try {
            val ins = URL(imageUrl).openStream()
            image = BitmapFactory.decodeStream(ins)


        return image
    }

    override fun onPostExecute(result: Bitmap?) {
        result?.let {
            imageView.setImageBitmap(it)
        }
    }
}