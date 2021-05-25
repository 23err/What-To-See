package com.example.whattosee.view

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.whattosee.ImageLoaderTask
import com.example.whattosee.R
import com.example.whattosee.databinding.FilmFragmentBinding
import com.example.whattosee.hide
import com.example.whattosee.model.Film
import com.example.whattosee.model.datastate.FilmDataState
import com.example.whattosee.room.CommentEntity
import com.example.whattosee.room.WatchHistoryEntity
import com.example.whattosee.show
import com.example.whattosee.view.adapters.RVCommentAdapter
import com.example.whattosee.viewmodel.FilmViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.film_fragment.*
import java.sql.Date
import java.time.LocalDateTime

class FilmFragment : BaseFragment() {
    private var _binding: FilmFragmentBinding? = null
    private val binding get() = _binding!!
    private var filmId = 0
    private val viewModel: FilmViewModel by lazy {
        ViewModelProvider(this).get(FilmViewModel::class.java)
    }
    private val adapter by lazy { RVCommentAdapter(requireContext()) }

    companion object {
        private const val ID_FILM = "idFilm"
        fun newInstance(idFilm: Int) = FilmFragment().apply {
            arguments = Bundle().apply {
                putInt(ID_FILM, idFilm)
            }
        }
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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            liveDataToObserve.observe(viewLifecycleOwner) { renderData(it) }
            getFilm(filmId)
            saveWatchHistory(WatchHistoryEntity(0, filmId.toLong()))
        }
        initClickListener()
        initAdapter()
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

    private fun initClickListener() = with(binding) {
        btnSend.setOnClickListener{
            val commentText = etComment.text.toString().trim()
            if (commentText.length > 0) {
                viewModel.saveComment(CommentEntity(0, commentText, filmId.toLong()))
                etComment.text = null
                getComments(true)
            }
        }
    }

    private fun initAdapter() = with(binding){
        rvComments.adapter = adapter
        getComments()
    }

    private fun getComments(insertItem:Boolean = false) {
        viewModel.getComments(filmId) {
            adapter.list = it
            if (insertItem){
                adapter.notifyItemInserted(it.size - 1)
            } else {
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setData(film: Film) = with(binding){
        main_toolbar.title = film.title
        rating.text = film.rating.toString()
        budget.text = film.budget.toString()
        description.text = film.description
        Picasso.get()
            .load(film.image)
            .into(posterIV)

    }
}