package com.example.whattosee.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.Fragment
import com.example.whattosee.R
import com.example.whattosee.databinding.SettingFragmentBinding

class SettingFragment : BaseFragment() {

    companion object {
        fun getInstance(): SettingFragment {
            return SettingFragment()
        }
    }

    private var _binding: SettingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = SettingFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwitch()
        setChangeSwitcher()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initSwitch() = with(binding) {
        swAdultContent.isChecked = getSPAdultMode()
    }

    private fun setChangeSwitcher() = with(binding) {
        swAdultContent.setOnCheckedChangeListener { compoundButton: CompoundButton, checked: Boolean ->
            setSPAdultMode(checked)
        }

    }


}