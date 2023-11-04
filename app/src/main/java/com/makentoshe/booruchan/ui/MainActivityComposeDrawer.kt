package com.makentoshe.booruchan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.makentoshe.booruchan.library.navigation.DrawerScreenNavigator
import com.makentoshe.booruchan.library.navigation.SourceScreenNavigator
import com.makentoshe.booruchan.screen.drawer.DrawerScreen
import com.makentoshe.library.uikit.foundation.PrimaryText
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun rememberDrawerState(navController: NavController) = rememberDrawerState(
    initialValue = DrawerValue.Closed,
    confirmStateChange = { confirmDrawerStateChange(it, navController) }
)

private fun confirmDrawerStateChange(value: DrawerValue, navController: NavController): Boolean {
//    return navController.currentDestination?.route != Screen.Splash.route
    return true
}

@Composable
internal fun MainActivityDrawerContent(
    navHostController: NavHostController,
) = Box(
    modifier = Modifier.fillMaxSize().background(BooruchanTheme.colors.background),
) {
    val navigator = DrawerScreenNavigator(
        sas = {},
        asa = {},
    )

    DrawerScreen(navigator = navigator)
}