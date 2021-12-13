package com.max.kkday.view.history

import androidx.recyclerview.widget.RecyclerView
import com.max.kkday.databinding.ItemHistoryBinding

class HistoryViewHolder(
    private val binding: ItemHistoryBinding,
    private val onViewHolderListener: HistoryOnViewHolderListener
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: HistoryItem) {
        binding.textViewOriginLink.text = item.originLink
        binding.textViewShortLink.text = item.shortLink

        binding.imageViewDelete.setOnClickListener {
            onViewHolderListener.deleteHistory(item.id)
        }

        binding.buttonCopyText.setOnClickListener {
            onViewHolderListener.copyText(item.shortLink)
        }

        itemView.setOnClickListener {
            onViewHolderListener.clickItem()
        }
    }
}