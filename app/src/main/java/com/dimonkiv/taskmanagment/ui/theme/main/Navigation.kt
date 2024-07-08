package com.dimonkiv.taskmanagment.ui.theme.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.taskmanagment.ScreenNamesConstant
import com.dimonkiv.taskmanagment.ui.theme.calendar.CalendarScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenNamesConstant.MAIN) {
        composable(ScreenNamesConstant.MAIN) {
            MainScreen()
        }
        composable(ScreenNamesConstant.MESSAGE) {
            MessageScreen()
        }
        composable(ScreenNamesConstant.CALENDAR) {
            CalendarScreen()
        }
        composable(ScreenNamesConstant.ACCOUNT) {
            AccountScreen()
        }
    }

}