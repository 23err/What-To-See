package com.example.whattosee.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.whattosee.R
import com.example.whattosee.databinding.MainFragmentBinding
import com.example.whattosee.model.Category
import com.example.whattosee.model.Film
import com.example.whattosee.view.adapters.RVCategoryAdapter
import com.example.whattosee.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RVCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() = with(binding) {
        adapter = RVCategoryAdapter(requireContext())
        rvCategory.adapter = adapter
        val observer = Observer<List<Category>> { renderData(it) }
        viewModel.liveDataToObserve.observe(viewLifecycleOwner, observer)
        viewModel.getCategories()
    }

    private fun renderData(categories: List<Category>) {
        adapter.categoryList = categories
        adapter.notifyDataSetChanged()
    }
}