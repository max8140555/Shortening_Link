package com.max.kkday.di

import com.max.kkday.view.history.HistoryViewModel
import com.max.kkday.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        HistoryViewModel(get())
    }
}