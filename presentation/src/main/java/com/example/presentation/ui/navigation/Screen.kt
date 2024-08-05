package com.example.presentation.ui.navigation

import androidx.annotation.StringRes
import com.example.presentation.R
import com.example.presentation.utils.Constants.DETAILS
import com.example.presentation.utils.Constants.HOME

sealed class Screen(
    val route: String,
) {
    /*object Home: Screen(HOME, R.string.home_text)
    object Details: Screen(DETAILS, R.string.home_details){
        fun sendName(name: String) = "$name/Details"
    }*/

    object Home : Screen("Home")

    object Details : Screen("{slug}/detail_screen") {
        fun sendName(slug: String) = "$slug/detail_screen"
    }

}