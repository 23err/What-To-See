package com.example.whattosee.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattosee.R
import com.example.whattosee.model.Film

class RVFilmAdapter(val context: Context) : RecyclerView.Adapter<RVFilmAdapter.ViewHolder>() {
    var films: List<Film> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVFilmAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.rv_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVFilmAdapter.ViewHolder, position: Int) {
        val film = films.get(position)
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return films.size
    }

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: AppCompatImageView
        val titleView: TextView
        val ratingView: TextView

        init {
            imageView = itemView.findViewById(R.id.image)
            titleView = itemView.findViewById(R.id.title)
            ratingView = itemView.findViewById(R.id.rating)
        }

        fun bind(film: Film) {
            titleView.text = film.title
            ratingView.text = film.rating.toString()
        }
    }
}