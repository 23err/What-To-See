package com.example.whattosee.view

import RVCategoryAdapter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.R
import com.example.whattosee.databinding.MainFragmentBinding
import com.example.whattosee.hide
import com.example.whattosee.model.Category
import com.example.whattosee.model.Film
import com.example.whattosee.show
import com.example.whattosee.showSnackBar
import com.example.whattosee.view.adapters.RVFilmAdapter
import com.example.whattosee.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var adapter: RVCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() = with(binding) {
        adapter = RVCategoryAdapter(
            requireContext(),
            onCategoryClick = object : RVCategoryAdapter.OnCategoryClick {
                override fun onClick(category: Category) {
                    CategoryFragment.newInstance(category.id).let {
                        Navigation.setFragment(requireFragmentManager(), it)
                    }
                }
            },
            onFilmClickListener = object : RVFilmAdapter.OnFilmClickListener {
                override fun click(film: Film) {
                    FilmFragment.newInstance(film.id).let {
                        Navigation.setFragment(requireFragmentManager(), it)
                    }
                }
            }
        ). also {
            rvCategory.adapter = it
        }
        viewModel.apply {
            liveDataToObserve.observe(viewLifecycleOwner, { renderData(it) } )
            getCategories()
        }
    }

    private fun renderData(state: CategoriesDataState) = with(binding) {
        when (state) {
            is CategoriesDataState.Success -> {
                loadingLayout.root.hide()
                adapter.apply {
                    categoryList = state.categories
                    notifyDataSetChanged()
                }
            }
            is CategoriesDataState.Error -> {
                loadingLayout.root.hide()
                var message = state.error.message ?: getString(R.string.unknown_error)
                rvCategory.showSnackBar(message, {viewModel.getCategories()}, getString(R.string.reload))
            }
            is CategoriesDataState.Loading -> loadingLayout.root.show()
        }
    }
}