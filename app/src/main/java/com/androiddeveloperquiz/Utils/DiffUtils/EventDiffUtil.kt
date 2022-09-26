package com.androiddeveloperquiz.Utils.DiffUtils

import androidx.recyclerview.widget.DiffUtil
import com.androiddeveloperquiz.Models.EventsModel

object EventDiffUtil : DiffUtil.ItemCallback<EventsModel>() {
    override fun areItemsTheSame(
        oldItem: EventsModel,
        newItem: EventsModel
    ): Boolean {
        return oldItem.actor.id == newItem.actor.id
    }

    override fun areContentsTheSame(
        oldItem: EventsModel,
        newItem: EventsModel
    ): Boolean {
        return oldItem.actor == newItem.actor
    }
}