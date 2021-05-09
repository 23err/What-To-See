package com.example.whattosee.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.whattosee.model.datastate.CategoryDataState
import com.example.whattosee.R
import com.example.whattosee.databinding.CategoryFragmentBinding
import com.example.whattosee.hide
import com.example.whattosee.model.Film
import com.example.whattosee.show
import com.example.whattosee.view.adapters.RVFilmAdapter
import com.example.whattosee.viewmodel.CategoryViewModel
import com.google.android.material.snackbar.Snackbar

class CategoryFragment : BaseFragment() {
    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!
    private var idCategory = 0
    private lateinit var adapter: RVFilmAdapter

    private val viewModel: CategoryViewModel by lazy {
        ViewModelProvider(this).get(
            CategoryViewModel::class.java
        )
    }

    companion object {
        private const val ID_CATEGORY = "id"

        fun newInstance(idCategory: Int): CategoryFragment = CategoryFragment().apply {
            Bundle().apply {
                putInt(ID_CATEGORY, idCategory)
            }.also {
                arguments = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        arguments?.run {
            idCategory = getInt(ID_CATEGORY)
        }
        _binding = CategoryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapter() = with(binding) {
        adapter = RVFilmAdapter(
            requireContext(),
            object : RVFilmAdapter.OnFilmClickListener {
                override fun click(film: Film) {
                    val filmFragment = FilmFragment.newInstance(film.id)
                    Navigation.setFragment(requireFragmentManager(), filmFragment)
                }
            }
        )
        rvFilms.let {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(context, 2)
        }
        viewModel.apply {
            liveData.observe(
                viewLifecycleOwner,
                { renderData(it) })
            getFilms(idCategory)
        }
    }

    private fun renderData(state: CategoryDataState) = with(binding) {
        when (state) {
            is CategoryDataState.Success -> {
                loadingLayout.root.hide()
                title.text = state.category.title
                adapter.apply {
                    films = state.category.films
                    notifyDataSetChanged()
                }
            }
            is CategoryDataState.Error -> {
                loadingLayout.root.hide()
                var message = state.error.message ?: getString(R.string.unknown_error)
                Snackbar.make(rvFilms, message, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.reload)) { viewModel.getFilms(idCategory) }
                    .show()
            }
            is CategoryDataState.Loading -> loadingLayout.root.show()
        }
    }
}