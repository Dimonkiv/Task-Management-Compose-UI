package com.dimonkiv.taskmanagment.ui.theme.model

import com.dimonkiv.taskmanagment.R
import com.dimonkiv.taskmanagment.ScreenNamesConstant


sealed class NavigationItem(val route: String, val icon: Int) {
    data object Main: NavigationItem(ScreenNamesConstant.MAIN, R.drawable.ic_home)

    data object Message: NavigationItem(ScreenNamesConstant.MESSAGE, R.drawable.ic_sent)

    data object Calendar: NavigationItem(ScreenNamesConstant.CALENDAR, R.drawable.ic_calendar)

    data object Account: NavigationItem(ScreenNamesConstant.ACCOUNT, R.drawable.ic_account)
}
