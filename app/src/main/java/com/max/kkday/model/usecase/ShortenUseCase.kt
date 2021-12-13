package com.max.kkday.model.usecase

interface ShortenUseCase {
    suspend fun convertLink(originLink: String): Shorten
}