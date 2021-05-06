package com.example.whattosee.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.whattosee.databinding.LoadingLayoutBinding

open class BaseFragment : Fragment() {

    fun loadingLayoutShow(binding: LoadingLayoutBinding) {
        binding.root.visibility = View.VISIBLE
    }

    fun loadingLayoutHide(binding: LoadingLayoutBinding) {
        binding.root.visibility = View.GONE
    }
}