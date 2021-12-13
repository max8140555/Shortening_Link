package com.max.kkday.model.repository

import com.max.kkday.model.CalendarUtil.getTaiwanTime
import com.max.kkday.model.cache.CacheDatabase
import com.max.kkday.model.cache.shorten.ShortenCache
import com.max.kkday.model.service.shrtco.ShrtcoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShortenRepositoryImpl(
    private val shrtcoService: ShrtcoService,
    private val cacheDatabase: CacheDatabase
) : ShortenRepository {

    companion object {
        private const val ERROR_MESSAGE_URL_INVALID = "提交網址無效"
        private const val ERROR_MESSAGE_UN_KNOW = "未知錯誤，請稍後在試"
    }

    override suspend fun convertLink(originLink: String): RepositoryShorten =
        withContext(Dispatchers.IO) {
            val response = shrtcoService.getShorten(originLink).body()

            if (response == null) {
                RepositoryShorten(success = false, errorMessage = ERROR_MESSAGE_UN_KNOW)
            } else {

                if (!response.success) {

                    val errorMessage = when (response.errorCode) {
                        1, 2 -> ERROR_MESSAGE_URL_INVALID
                        else -> ERROR_MESSAGE_UN_KNOW
                    }

                    RepositoryShorten(success = false, errorMessage = errorMessage)
                } else {
                    val data = ShortenCache(
                        originLink = originLink,
                        shortLink = response.result.fullShortLink,
                        timeInMillis = getTaiwanTime().timeInMillis
                    )

                    cacheDatabase.ShortenCacheDao().insert(data)

                    RepositoryShorten(success = true)
                }
            }
        }
}
