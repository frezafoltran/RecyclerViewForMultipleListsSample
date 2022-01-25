package com.foltran.recyclerviewforlistandgridsample.di

import com.foltran.recyclerviewforlistandgridsample.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {
    val instance = module {
        viewModel {
            MainViewModel()
        }
    }
}