package com.max.kkday.model.cache.shorten

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val APP_SHORTEN_CACHE_TABLE_NAME = "app_Shorten_cache"

/**
 * 快取 - 歷史短網址
 *
 * @property id 編號
 * @property originLink 原始 Link
 * @property shortLink 短網址 Link
 * @property timeInMillis 時間
 */
@Entity(tableName = APP_SHORTEN_CACHE_TABLE_NAME)
data class ShortenCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "origin_link")
    val originLink: String,
    @ColumnInfo(name = "short_link")
    val shortLink: String,
    @ColumnInfo(name = "timeInMillis")
    val timeInMillis: Long
)