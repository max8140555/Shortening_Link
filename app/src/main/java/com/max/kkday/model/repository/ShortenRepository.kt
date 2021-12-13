package com.max.kkday.model.repository

interface ShortenRepository {
    suspend fun convertLink(originLink: String): RepositoryShorten
}