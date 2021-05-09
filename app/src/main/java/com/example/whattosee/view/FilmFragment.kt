package com.example.whattosee.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whattosee.R
import com.example.whattosee.databinding.FilmFragmentBinding
import com.example.whattosee.hide
import com.example.whattosee.model.Film
import com.example.whattosee.model.datastate.FilmDataState
import com.example.whattosee.show
import com.example.whattosee.viewmodel.FilmViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.film_fragment.*

class FilmFragment : BaseFragment() {
    private var _binding: FilmFragmentBinding? = null
    private val binding get() = _binding!!
    private var filmId = 0
    private val viewModel: FilmViewModel by lazy {
        ViewModelProvider(this).get(FilmViewModel::class.java)
    }

    companion object {
        private const val ID_FILM = "idFilm"
        fun newInstance(idFilm: Int) = FilmFragment().apply {
            arguments = Bundle().apply {
                putInt(ID_FILM, idFilm)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.run {
            filmId = getInt(ID_FILM)
        }
        _binding = FilmFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            viewModel.liveDataToObserve.observe(viewLifecycleOwner) { renderData(it) }
            getFilm(filmId)
        }
    }

    private fun renderData(it: FilmDataState) = with(binding) {
        when (it) {
            is FilmDataState.Success -> {
                loadingLayout.root.hide()
                setData(it.film)
            }
            is FilmDataState.Error -> {
                loadingLayout.root.hide()
                var message = it.error.message ?: getString(R.string.unknown_error)
                Snackbar.make(root, message, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.reload)) { viewModel.getFilm(filmId) }
                    .show()
            }
            is FilmDataState.Loading -> {
                loadingLayout.root.show()
            }
        }
    }

    private fun setData(film: Film) = with(binding){
        main_toolbar.title = film.title
        rating.text = film.rating.toString()
        madeIn.text = film.madeIn
        budget.text = film.budget.toString()
        description.text = film.description
    }
}