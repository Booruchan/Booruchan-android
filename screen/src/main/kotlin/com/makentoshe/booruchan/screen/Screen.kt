package com.makentoshe.booruchan.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.makentoshe.booruchan.screen.Screen.Source.sourceIdArgumentName

sealed interface Screen {
    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    object Splash : Screen {
        override val route: String = "SplashScreen"
    }

    object Home: Screen {
        override val route: String = "HomeScreen"
    }

    object Source: Screen {
        private val sourceIdArgumentName = "SourceId"
        val sourceIdArgument = navArgument(sourceIdArgumentName) {
            type = NavType.StringType
        }

        override val arguments: List<NamedNavArgument> = listOf(sourceIdArgument)

        override val route: String = "SourceScreen/{$sourceIdArgumentName}"

        fun route(sourceId: String) = "SourceScreen/$sourceId"
    }

    object Image: Screen {
        private val sourceIdArgumentName = "SourceId"
        val sourceIdArgument = navArgument(sourceIdArgumentName) {
            type = NavType.StringType
        }

        private val postIdArgumentName = "PostId"
        val postIdArgument = navArgument(postIdArgumentName) {
            type = NavType.StringType
        }

        override val arguments: List<NamedNavArgument> = listOf(sourceIdArgument, postIdArgument)

        override val route: String = "ImageScreen/{$sourceIdArgumentName}/{$postIdArgumentName}"

        fun route(sourceId: String, postId: String) = "ImageScreen/$sourceId/$postId"
    }
}
