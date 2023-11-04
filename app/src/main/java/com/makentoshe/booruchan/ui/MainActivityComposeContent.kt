package com.makentoshe.booruchan.ui

import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.makentoshe.library.uikit.theme.BooruchanTheme

@Composable
internal fun MainActivityComposeContent() = BooruchanTheme {
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = rememberDrawerState(navController = navController),
        drawerContent = { MainActivityDrawerContent(navHostController = navController) },
        content = { MainActivityNavigationContent(navHostController = navController) },
    )
}
