package com.max.kkday.view.history

interface HistoryOnViewHolderListener {
    fun deleteHistory(id: Int)

    fun clickItem()

    fun copyText(shortLink: String)
}