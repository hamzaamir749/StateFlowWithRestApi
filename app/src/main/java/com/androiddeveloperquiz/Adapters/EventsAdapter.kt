package com.androiddeveloperquiz.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androiddeveloperquiz.Models.EventsModel
import com.androiddeveloperquiz.Utils.DiffUtils.EventDiffUtil
import com.androiddeveloperquiz.databinding.ItemEventsLayoutBinding
import com.squareup.picasso.Picasso

class EventsAdapter() :
    ListAdapter<EventsModel, EventsAdapter.ModelViewHolder>(EventDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        return ModelViewHolder(
            ItemEventsLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        with(holder) {
            with(currentList[position].actor) {
                Picasso.get().load(this.avatar_url).fit().into(binding.imgAvatar)
                binding.tvID.text = "ID: ${this.id}"
                binding.tvName.text = "Name: ${this.display_login}"
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ModelViewHolder(val binding: ItemEventsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}