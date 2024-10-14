package com.example.submissionfundamentalpertama.AllComponent.CustomBottomBar

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.submissionfundamentalpertama.Adapter.DataBottomBar

@Composable
fun BottomBar(navController: NavController, getTopBar: (String) -> Unit) {
    val navBackEntry by navController.currentBackStackEntryAsState()
    val getBottomBar = listOf(
        DataBottomBar.Home,
        DataBottomBar.UpComing,
        DataBottomBar.Finished
    )

    NavigationBar(
        modifier = Modifier.shadow(10.dp, ambientColor = Color(0xFF00B0FF), spotColor = Color(0xFF00B0FF)),
        content = {
            getBottomBar.forEach(
                action = { navItem ->
                    val isSelected =
                        navBackEntry?.destination?.hierarchy?.any { it.route == navItem.route } == true
                    NavigationBarItem(
                        modifier = Modifier.wrapContentHeight(),
                        selected = isSelected,
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "icon")
                        },
                        onClick = {
                            getTopBar(navItem.route)
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            navItem.title?.let { Text(text = it) }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFEE299B),
                            selectedTextColor = Color(0xFFEE299B),
                            unselectedIconColor = Color(0xFF000823),
                            unselectedTextColor = Color(0xFF000823),
                            indicatorColor = if (isSelected) Color.White else Color(0xFF1E2022)
                        )
                    )
                }
            )
        },
        containerColor = Color.White
    )
}

@Preview
@Composable
private fun BottomBarPrev() {
    BottomBar(rememberNavController()) {}
}