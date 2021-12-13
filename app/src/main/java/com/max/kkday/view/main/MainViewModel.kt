package com.max.kkday.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.max.kkday.model.service.util.LoadApiStatus
import com.max.kkday.model.usecase.ShortenUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val shortenUseCase: ShortenUseCase) : ViewModel() {

    private val _loadApiStatus = MutableLiveData<LoadApiStatus>()
    val loadApiStatus: LiveData<LoadApiStatus> get() = _loadApiStatus

    private var job: Job? = null

    fun conventLink(originLink: String) {
        job?.cancel()
        job = viewModelScope.launch {

            _loadApiStatus.value = LoadApiStatus.LOADING

            val result = shortenUseCase.convertLink(originLink)

            if (result.success) {
                _loadApiStatus.value = LoadApiStatus.DONE
            } else {
                _loadApiStatus.value = LoadApiStatus.ERROR(result.errorMessage)
            }
        }
    }
}