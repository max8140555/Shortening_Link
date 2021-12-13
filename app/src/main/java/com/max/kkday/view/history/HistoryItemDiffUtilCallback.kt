package com.max.kkday.view.history

import androidx.recyclerview.widget.DiffUtil

class HistoryItemDiffUtilCallback : DiffUtil.ItemCallback<HistoryItem>() {

    override fun areItemsTheSame(
        oldItem: HistoryItem,
        newItem: HistoryItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: HistoryItem,
        newItem: HistoryItem
    ): Boolean {
        return oldItem == newItem
    }
}