package com.androiddeveloperquiz.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddeveloperquiz.Models.EventsModel
import com.androiddeveloperquiz.databinding.ItemEventsLayoutBinding
import com.squareup.picasso.Picasso

class EventsAdapter(var list: ArrayList<EventsModel>) :
    RecyclerView.Adapter<EventsAdapter.modelViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): modelViewHolder {
        return modelViewHolder(
            ItemEventsLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: modelViewHolder, position: Int) {
        with(holder) {
            with(list[position].actor) {
                Picasso.get().load(this.avatar_url).fit().into(binding.imgAvatar)
                binding.tvID.text = "ID: ${this.id}"
                binding.tvName.text = "Name: ${this.display_login}"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class modelViewHolder(val binding: ItemEventsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun addItems(listNew: ArrayList<EventsModel>) {
        list.clear()
        list.addAll(listNew)
    }
}