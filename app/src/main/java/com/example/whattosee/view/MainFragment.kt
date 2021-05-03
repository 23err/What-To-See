package com.example.whattosee.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whattosee.R
import com.example.whattosee.databinding.MainFragmentBinding
import com.example.whattosee.model.Film
import com.example.whattosee.view.adapters.RVFilmAdapter
import com.example.whattosee.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() = with(binding){

        val films = listOf(
                Film("Harry Potter", 9.2F),
                Film("День Сурка", 9.3F),
                Film("Мстители", 6.7F),
                Film("Мстители", 6.7F),
                Film("FILM", 6.7F),
                Film("FILM", 6.7F),
                Film("FILM", 6.7F),
                Film("FILM", 6.7F),
        )
        val adapter = RVFilmAdapter(requireContext())
        adapter.films=films
        rvFilms.adapter=adapter
    }
}