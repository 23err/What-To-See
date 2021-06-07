package com.example.whattosee

import android.content.IntentFilter
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.whattosee.broadcastreceiver.MainBroadcastReceiver
import com.example.whattosee.view.MainFragment
import com.example.whattosee.view.Navigation

class MainActivity : AppCompatActivity() {
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