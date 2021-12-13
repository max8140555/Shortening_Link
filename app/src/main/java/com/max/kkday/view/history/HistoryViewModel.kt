package com.max.kkday.view.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.max.kkday.model.cache.CacheDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HistoryViewModel(private val cacheDatabase: CacheDatabase) : ViewModel() {

    val items = cacheDatabase.ShortenCacheDao().getAllHistoryShortenCacheFlow()
        .map { caches ->
            caches.sortedByDescending { it.timeInMillis }.map { cache ->
                HistoryItem(
                    cache.id,
                    cache.originLink,
                    cache.shortLink
                )
            }
        }
        .flowOn(Dispatchers.Default)
        .asLiveData(viewModelScope.coroutineContext)

    fun deleteHistory(id: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            cacheDatabase.ShortenCacheDao().deleteTargetHistory(id)
        }
    }
}