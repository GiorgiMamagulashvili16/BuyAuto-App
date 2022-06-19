package com.example.buyauto_app.presentation.dashboard_screen.bottom_nav

import androidx.navigation.NavDirections

data class BottomNavModel(
    val icon: Int,
    val action: NavDirections,
    val fragmentId: Int
)
