package com.max.kkday.model.cache.shorten

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShortenCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(equityCache: ShortenCache)

    @Query(value = "SELECT * FROM $APP_SHORTEN_CACHE_TABLE_NAME")
    fun getAllHistoryShortenCacheFlow(): Flow<List<ShortenCache>>

    @Query("DELETE FROM $APP_SHORTEN_CACHE_TABLE_NAME WHERE id == :id")
    suspend fun deleteTargetHistory(id: Int)
}