package com.example.whattosee.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattosee.R
import com.example.whattosee.model.Category

class RVCategoryAdapter(
    private val context: Context,
    private val onCategoryClick: OnCategoryClick? = null,
    private val onFilmClickListener: RVFilmAdapter.OnFilmClickListener? = null
    ) : RecyclerView.Adapter<RVCategoryAdapter.ViewHolder>() {

    var categoryList: List<Category> = listOf()
//    private var onCategoryClick: OnCategoryClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVCategoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.rv_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVCategoryAdapter.ViewHolder, position: Int) {
        val category = categoryList.get(position)
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val rvFilms: RecyclerView

        init {
            title = view.findViewById(R.id.title)
            rvFilms = view.findViewById(R.id.rvFilms)
        }

        fun bind(category: Category) {
            title.text = category.title
            val adapter = RVFilmAdapter(context, onFilmClickListener)
            adapter.films = category.films
            rvFilms.adapter = adapter
            title.setOnClickListener {
                onCategoryClick?.onClick(category)
            }
        }
    }

    interface OnCategoryClick {
        fun onClick(category: Category)
    }

}