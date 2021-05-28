package com.example.whattosee.view.adapters

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattosee.databinding.RvContactItemBinding
import com.example.whattosee.model.Contact
import com.example.whattosee.view.ContactFragment


class RVContactAdapter(
    val context: Context,
    ): RecyclerView.Adapter<RVContactAdapter.ViewHolder>() {

    private var _binding:RvContactItemBinding? = null;
    private val binding get() = _binding!!
    var list:List<Contact>? = null
    var onClickListener : ((contact:Contact)->Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = RvContactItemBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {

        return list?.size ?: 0
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(contact: Contact) = with(binding){
            contactName.text = contact.name
            phoneNumber.text = contact.phoneNumber
            view.setOnClickListener{
                contact.phoneNumber?.let {
                    onClickListener?.let { it(contact) }
                }
            }
        }
    }
}