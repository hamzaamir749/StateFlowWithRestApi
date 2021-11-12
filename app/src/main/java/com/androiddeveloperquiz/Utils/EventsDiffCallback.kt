package com.androiddeveloperquiz.Utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.androiddeveloperquiz.Models.EventsModel


class EventsDiffCallback(var oldList: List<EventsModel>, var newList: List<EventsModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].actor.id == newList[newItemPosition].actor.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].actor.display_login == newList[newItemPosition].actor.display_login
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }


}