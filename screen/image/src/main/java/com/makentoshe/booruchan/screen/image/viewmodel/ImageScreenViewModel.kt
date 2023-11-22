package com.makentoshe.booruchan.screen.image.viewmodel

import androidx.lifecycle.ViewModel
import com.makentoshe.booruchan.library.feature.CoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultCoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultEventDelegate
import com.makentoshe.booruchan.library.feature.DefaultNavigationDelegate
import com.makentoshe.booruchan.library.feature.DefaultStateDelegate
import com.makentoshe.booruchan.library.feature.EventDelegate
import com.makentoshe.booruchan.library.feature.NavigationDelegate
import com.makentoshe.booruchan.library.feature.StateDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageScreenViewModel @Inject constructor(

) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    StateDelegate<ImageScreenState> by DefaultStateDelegate(ImageScreenState.InitialState),
    EventDelegate<ImageScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<ImageScreenDestination> by DefaultNavigationDelegate() {

    override fun handleEvent(event: ImageScreenEvent) = when(event) {
        is ImageScreenEvent.Initialize -> initialize(event)
    }

    private fun initialize(event: ImageScreenEvent.Initialize) {

    }
}