package com.example.whattosee.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.whattosee.CategoryDataState
import com.example.whattosee.R
import com.example.whattosee.databinding.CategoryFragmentBinding
import com.example.whattosee.view.adapters.RVFilmAdapter
import com.example.whattosee.viewmodel.CategoryViewModel
import com.google.android.material.snackbar.Snackbar

class CategoryFragment : Fragment() {

    companion object {
        private val ID_CATEGORY = "id"

        fun newInstance(idCategory: Int): CategoryFragment {
            val categoryFragment = CategoryFragment()
            val bundle = Bundle()
            bundle.putInt(ID_CATEGORY, idCategory)
            categoryFragment.arguments = bundle
            return categoryFragment
        }
    }

    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!
    private var idCategory = 0
    private lateinit var adapter: RVFilmAdapter

    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val bundle = arguments
        bundle?.apply {
            idCategory = bundle.getInt(ID_CATEGORY)
        }
        _binding = CategoryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        setAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setAdapter() = with(binding) {
        adapter = RVFilmAdapter(requireContext())
        rvFilms.adapter = adapter
        rvFilms.layoutManager=GridLayoutManager(context, 2)
        viewModel.getFilms(idCategory)
    }

    private fun renderData(state: CategoryDataState) {
        when (state) {
            is CategoryDataState.Success -> {
                val category = state.category
                binding.title.text = state.category.title
                adapter.films = category.films
                adapter.notifyDataSetChanged()
            }
            is CategoryDataState.Error -> {
                var message = state.error.message
                if (message == null) {
                    message = getString(R.string.unknown_error)
                }
                Snackbar.make(binding.rvFilms, message, Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.reload)){viewModel.getFilms(idCategory)}
                        .show()
            }
        }
    }

}