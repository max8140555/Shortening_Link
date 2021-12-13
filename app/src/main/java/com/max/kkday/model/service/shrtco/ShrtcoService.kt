package com.max.kkday.model.service.shrtco

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShrtcoService {

    @GET("shorten")
    suspend fun getShorten(
        @Query("url") url: String
    ): Response<ResponseShorten>
}