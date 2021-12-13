package com.max.kkday.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.max.kkday.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val historyOnViewHolderListener: HistoryOnViewHolderListener,
    itemDiffCallback: DiffUtil.ItemCallback<HistoryItem> = HistoryItemDiffUtilCallback()
) : ListAdapter<HistoryItem, HistoryViewHolder>(itemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HistoryViewHolder(binding, historyOnViewHolderListener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}