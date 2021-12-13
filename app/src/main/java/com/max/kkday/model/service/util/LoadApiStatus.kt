package com.max.kkday.model.service.util

sealed class LoadApiStatus {
    object LOADING : LoadApiStatus()
    object DONE : LoadApiStatus()
    data class ERROR(val errorMessage: String = "") : LoadApiStatus()
}