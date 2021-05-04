package com.example.whattosee.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whattosee.R
import com.example.whattosee.model.Film
import com.example.whattosee.viewmodel.CategoryViewModel

class CategoryFragment : Fragment() {

    companion object {
        fun newInstance(films: ArrayList<Film>) {
            val categoryFragment = CategoryFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("films", films)
            return
        }
    }

    lateinit var films: ArrayList<Film>

    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}