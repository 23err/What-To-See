package com.example.whattosee.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whattosee.databinding.RvCommentItemBinding
import com.example.whattosee.format
import com.example.whattosee.room.CommentEntity

class RVCommentAdapter(val context: Context) : RecyclerView.Adapter<RVCommentAdapter.Holder>() {

    var list : List<CommentEntity>? = null
    var _binding : RvCommentItemBinding? = null
    val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        _binding = RvCommentItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding.root)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        list?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(commentEntity: CommentEntity) = with(binding){
            tvMessage.text = commentEntity.message
            tvDate.text = commentEntity.date.format()
        }
    }
}