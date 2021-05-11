package com.example.whattosee.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattosee.R
import com.example.whattosee.databinding.RvMovieItemBinding
import com.example.whattosee.model.Film

class RVFilmAdapter(val context: Context, val onFilmClickListener: OnFilmClickListener? = null) :
    RecyclerView.Adapter<RVFilmAdapter.ViewHolder>() {
    var films: List<Film> = listOf()
    var _binding: RvMovieItemBinding? = null
    val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVFilmAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        _binding = RvMovieItemBinding.inflate(layoutInflater)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RVFilmAdapter.ViewHolder, position: Int) {
        holder.bind(films.get(position))
    }

    override fun getItemCount(): Int = films.size

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) = with(binding){
            title.text = film.title
            rating.text = film.rating
            filmLayout.setOnClickListener {
                onFilmClickListener?.click(film)
            }
        }
    }

    interface OnFilmClickListener {
        fun click(film: Film)
    }
}