package com.example.whattosee.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.whattosee.databinding.LoadingLayoutBinding

open class BaseFragment : Fragment() {

    companion object{
        private const val APP_SETTING = "app setting"
        private const val ADULT = "adult"
    }

    protected fun setSPAdultMode(adult: Boolean) {
        setSharedPreference(ADULT, adult)
    }

    protected fun getSPAdultMode():Boolean{
        return getSharedPreferenceBoolean(ADULT)
    }

    protected fun getSharedPreferenceBoolean(name:String) : Boolean{
        activity?.let {
            val sp = getSharedPreferencesApp(it)
            return sp.getBoolean(name, false)
        }
        return false
    }

    protected fun setSharedPreference(name:String, flag: Boolean) {
        activity?.let {
            val sp = getSharedPreferencesApp(it)
            val editor = sp.edit()
            editor.putBoolean(name, flag)
            editor.apply()
        }
    }
    
    private fun getSharedPreferencesApp(it: FragmentActivity) =
        it.getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE)
}