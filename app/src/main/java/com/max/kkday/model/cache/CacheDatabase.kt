package com.max.kkday.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.max.kkday.model.cache.shorten.ShortenCacheDao
import com.max.kkday.model.cache.shorten.ShortenCache

@Database(
    entities = [
        ShortenCache::class,
    ],
    version = 1
)

abstract class CacheDatabase : RoomDatabase() {
    abstract fun ShortenCacheDao(): ShortenCacheDao
}