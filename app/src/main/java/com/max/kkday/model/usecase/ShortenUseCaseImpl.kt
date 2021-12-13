package com.max.kkday.model.usecase

import com.max.kkday.model.repository.ShortenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShortenUseCaseImpl(private val shortenRepository: ShortenRepository) : ShortenUseCase {

    override suspend fun convertLink(originLink: String): Shorten =
        withContext(Dispatchers.Default) {
            val repository = shortenRepository.convertLink(originLink)

            return@withContext Shorten(
                success = repository.success,
                errorMessage = repository.errorMessage
            )
        }
}