package com.example.whattosee

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private val TAG = "Main Activity"
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnInit()
        findViews()
        setTextView()

    }

    private fun setTextView() {
        val weather = Repository.getWeather(0)
        val changeTempWeather = Repository.getWeatherChangeCopy(0, 27)
        textView.setText("В городе ${weather.city} температура ${weather.temperature}")
        textView2.setText("В городе ${changeTempWeather.city} температура ${changeTempWeather.temperature}")
    }

    private fun findViews() {
        textView = findViewById<TextView>(R.id.textView)
        textView2 = findViewById<TextView>(R.id.textView2)
    }

    private fun btnInit() {
        val btnFirst = findViewById<MaterialButton>(R.id.btnFirst)
        btnFirst.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show()
        })
    }

    fun btnTwoOnClick(view: View) {
        Toast.makeText(applicationContext, "button two clicked", Toast.LENGTH_SHORT).show()
    }

}