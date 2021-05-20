package com.example.whattosee.view

import RVCategoryAdapter
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.whattosee.*
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.broadcastreceiver.FILMS_BROADCAST_INTENT
import com.example.whattosee.broadcastreceiver.FilmsLoadBroadcastReceiver
import com.example.whattosee.databinding.MainFragmentBinding
import com.example.whattosee.model.Category
import com.example.whattosee.model.Film
import com.example.whattosee.service.LoadService
import com.example.whattosee.view.adapters.RVFilmAdapter
import com.example.whattosee.viewmodel.MainViewModel

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var adapter: RVCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        observeDataModel()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeDataModel() {
        viewModel.liveDataToObserve.observe(
            viewLifecycleOwner,
            { renderData(it) }
        )
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
    }

    fun renderData(state: CategoriesDataState) = with(binding) {
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
                rvCategory.showSnackBar(message, { initData() }, getString(R.string.reload))
            }
            is CategoriesDataState.Loading -> loadingLayout.root.show()
        }
    }

    private fun initData() {
        viewModel.getCategories()
    }
}