package com.makentoshe.booruchan.application.android.screen.samples.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.makentoshe.booruchan.application.android.di.ApplicationScope
import com.makentoshe.booruchan.application.android.screen.posts.model.PostPreviewArenaStorage
import com.makentoshe.booruchan.application.android.screen.samples.SampleFragment
import com.makentoshe.booruchan.application.android.screen.samples.viewmodel.SampleFragmentViewModel
import com.makentoshe.booruchan.application.core.arena.post.PostImageArena
import com.makentoshe.booruchan.core.context.BooruContext
import io.ktor.client.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.delegate.inject
import java.io.File

class SampleModule(fragment: SampleFragment) : Module() {

    private val booruContexts by inject<List<BooruContext>>()
    private val client by inject<HttpClient>()

    init {
        Toothpick.openScope(ApplicationScope::class).inject(this)
        val booruContext = booruContexts.first { it.javaClass == fragment.arguments.booruclass }
        // Fragment composite disposable
        bind<CompositeDisposable>().toInstance(CompositeDisposable())

        val previewArena = getPreviewArena(booruContext, fragment)
        val factory = SampleFragmentViewModel.Factory(previewArena, fragment.arguments.post)
        val viewModel = ViewModelProviders.of(fragment, factory)[SampleFragmentViewModel::class.java]
        bind<SampleFragmentViewModel>().toInstance(viewModel)
    }

    private fun getPreviewArena(booruContext: BooruContext, fragment: Fragment): PostImageArena {
        val cacheDir = File(fragment.requireContext().cacheDir, booruContext.title)
        return PostImageArena(client, PostPreviewArenaStorage(cacheDir))
    }
}