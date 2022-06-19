package com.example.buyauto_app.presentation.favorites_screen.di

import com.example.buyauto_app.presentation.favorites_screen.FavoritesFragment
import com.example.buyauto_app.presentation.favorites_screen.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesScreenModule = module {
    scope<FavoritesFragment> {
        viewModel {
            FavoritesViewModel()
        }
    }
}