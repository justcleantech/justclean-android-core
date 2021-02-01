package com.justclean.samples.di

import com.justclean.samples.network.LanguagesViewModel
import com.justclean.samples.ui.viewmodels.SampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @name Hosam Almowaqee
 * Copyrights (c) 6/3/20 Created By Justclean Company
 */

val viewModelModule = module {
    viewModel { SampleViewModel() }
    viewModel { LanguagesViewModel() }
}