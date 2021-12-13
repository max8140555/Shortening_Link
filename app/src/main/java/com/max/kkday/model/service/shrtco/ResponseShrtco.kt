package com.max.kkday.model.service.shrtco

import com.google.gson.annotations.SerializedName

data class ResponseShorten(
    @SerializedName("ok")
    val success: Boolean,
    @SerializedName("result")
    val result: ShortenResult,
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error")
    val error: String?
)

data class ShortenResult(
    @SerializedName("code")
    val code: String,
    @SerializedName("short_link")
    val shortLink: String,
    @SerializedName("full_short_link")
    val fullShortLink: String,
    @SerializedName("short_link2")
    val shortLink2: String,
    @SerializedName("full_short_link2")
    val fullShortLink2: String,
    @SerializedName("share_link")
    val shareLink: String,
    @SerializedName("full_share_link")
    val fullShareLink: String,
    @SerializedName("original_link")
    val originalLink: String
)


