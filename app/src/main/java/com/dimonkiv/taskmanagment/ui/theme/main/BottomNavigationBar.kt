package com.dimonkiv.taskmanagment.ui.theme.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dimonkiv.taskmanagment.R
import com.dimonkiv.taskmanagment.ScreenNamesConstant
import com.dimonkiv.taskmanagment.ui.theme.Primary
import com.dimonkiv.taskmanagment.model.NavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController,
    barHeight: Dp = 60.dp,
    fabColor: Color = Primary,
    fabSize: Dp = 64.dp,
    fabIconSize: Dp = 32.dp,
    cardTopCornerSize: Dp = 24.dp,
    cardElevation: Dp = 8.dp,
    fabDrawableId: Int = R.drawable.ic_add,
    items: List<NavigationItem> = listOf(
        NavigationItem.Main,
        NavigationItem.Message,
        NavigationItem.Calendar,
        NavigationItem.Account
    ),
    onItemClick: (String) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight + fabSize / 2)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
            shape = RoundedCornerShape(topStart = cardTopCornerSize, topEnd = cardTopCornerSize)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val currentRoute = backStackEntry.value?.destination?.route

                items.forEachIndexed { pos: Int, item: NavigationItem ->
                    BottomBarItem(
                        item = item,
                        selected = item.route == currentRoute
                    ) {
                        onItemClick(item.route)
                    }

                    if (pos == 1) {
                        Spacer(modifier = Modifier.size(fabSize))
                    }
                }
            }

        }

        LargeFloatingActionButton(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopCenter),
            shape = CircleShape,
            containerColor = fabColor,
            elevation = FloatingActionButtonDefaults
                .bottomAppBarFabElevation(defaultElevation = 0.dp),
            onClick = {
                onItemClick(ScreenNamesConstant.ADD)
            }
        ) {
            Icon(
                painter = painterResource(id = fabDrawableId),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(fabIconSize)
            )
        }

    }

}

@Composable
fun BottomBarItem(
    item: NavigationItem,
    selected: Boolean = false,
    selectedColor: Color = Primary,
    unselectedColor: Color = Primary.copy(alpha = 0.7f),
    iconSize: Dp = 24.dp,
    onClick: (NavigationItem) -> Unit
) {
    IconButton(onClick = { onClick(item) }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            tint = if (selected) selectedColor else unselectedColor,
            modifier = Modifier.size(iconSize)
        )
    }
}