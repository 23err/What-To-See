package com.example.whattosee.view

import android.content.res.Resources
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.whattosee.MainActivity
import com.example.whattosee.R
import com.example.whattosee.databinding.FilmFragmentBinding
import com.example.whattosee.model.Film
import com.example.whattosee.model.datastate.FilmDataState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.film_fragment.*

class FilmFragment : BaseFragment() {

    companion object {
        private const val ID_FILM = "idFilm"

        fun newInstance(idFilm: Int): FilmFragment {
            val filmFragment = FilmFragment()
            val bundle = Bundle().apply {
                putInt(ID_FILM, idFilm)
            }
            filmFragment.arguments = bundle
            return filmFragment
        }
    }

    private lateinit var viewModel: FilmViewModel
    private var _binding: FilmFragmentBinding? = null
    private val binding get() = _binding!!
    private var filmId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.apply {
            filmId = getInt(ID_FILM)
        }
        _binding = FilmFragmentBinding.inflate(inflater)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        observe()
        viewModel.getFilm(filmId)
    }

    private fun observe() = with(binding) {
        viewModel.liveDataToObserve.observe(viewLifecycleOwner) {
            when (it) {
                is FilmDataState.Success -> {
                    loadingLayoutHide(loadingLayout)
                    val film = it.film
                    setData(film)
                }
                is FilmDataState.Error -> {
                    loadingLayoutHide(loadingLayout)
                    var message = it.error.message
                    if (message == null) {
                        message = getString(R.string.unknown_error)
                    }
                    Snackbar.make(root, message, Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.reload)) { viewModel.getFilm(filmId) }
                        .show()
                }
                is FilmDataState.Loading -> {
                    loadingLayoutShow(loadingLayout)
                }
            }
        }
    }

    private fun FilmFragmentBinding.setData(film: Film) {
        main_toolbar.title = film.title
        rating.text = film.rating.toString()
        madeIn.text = film.madeIn
        budget.text = film.budget.toString()
        description.text = film.description
    }
}