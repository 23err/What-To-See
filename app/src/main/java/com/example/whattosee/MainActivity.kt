package com.example.whattosee

import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.whattosee.databinding.MainFragmentBinding
import com.example.whattosee.view.MainFragment
import com.example.whattosee.view.Navigation
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private val TAG = "Main Activity"
    private lateinit var fragmentContainer:FrameLayout
    private val receiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        registerReceiver(receiver, IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    private fun initFragment() {
        val fragment = MainFragment()
        setFragment(fragment)
    }

    private fun setFragment(fragment: Fragment) {
        Navigation.setFragment(supportFragmentManager, fragment, false)
    }
}