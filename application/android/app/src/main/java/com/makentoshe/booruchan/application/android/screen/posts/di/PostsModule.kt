package com.makentoshe.booruchan.application.android.screen.posts.di

import androidx.lifecycle.ViewModelProviders
import com.makentoshe.booruchan.application.android.di.ApplicationScope
import com.makentoshe.booruchan.application.android.screen.booru.navigation.BooruNavigation
import com.makentoshe.booruchan.application.android.screen.posts.view.PostsFragment
import com.makentoshe.booruchan.application.android.screen.posts.viewmodel.PostsFragmentViewModel
import context.BooruContext
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.delegate.inject

class PostsModule(fragment: PostsFragment) : Module() {

    private val booruContexts by inject<List<BooruContext>>()

    init {
        Toothpick.openScope(ApplicationScope::class).inject(this)

        val booruContext = booruContexts.first { it.title == fragment.arguments.booruContextTitle }
        bind<BooruContext>().toInstance(booruContext)

        val navigation = BooruNavigation(fragment.childFragmentManager, booruContext)
        bind<BooruNavigation>().toInstance(navigation)

        val postsFragmentViewModelFactory = PostsFragmentViewModel.Factory()
        val postsFragmentViewModelProvider = ViewModelProviders.of(fragment, postsFragmentViewModelFactory)
        val postsFragmentViewModel = postsFragmentViewModelProvider[PostsFragmentViewModel::class.java]
        bind<PostsFragmentViewModel>().toInstance(postsFragmentViewModel)

    }
}