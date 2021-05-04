package com.example.whattosee.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.whattosee.R

class Navigation {
    companion object{
        fun setFragment(fragmentManager:FragmentManager, fragment:Fragment){
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
        }
    }
}