package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.component.housememberlist.HouseMemberListScreen
import com.example.presentation.ui.component.housememberlistdetails.HouseMemberListDetails
import com.example.presentation.utils.Constants.DETAILS_ROUTE

@Composable
fun HouseMemberNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route) {
        composable(
            Screen.Home.route,
        ) {
            HouseMemberListScreen(
                navController,
                hiltViewModel()
            )
        }

        composable(
            Screen.Details.route,
        ) {
            val slug = it.arguments?.getString("slug") ?: "no_name"
            HouseMemberListDetails(
                navigation = navController,
                hiltViewModel(),
                slug
            )
        }

    }


}