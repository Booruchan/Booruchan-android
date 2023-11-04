package com.makentoshe.booruchan.screen.drawer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makentoshe.booruchan.feature.usecase.FlowSearchSnapshotUseCase
import com.makentoshe.booruchan.library.feature.CoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultCoroutineDelegate
import com.makentoshe.booruchan.library.feature.DefaultEventDelegate
import com.makentoshe.booruchan.library.feature.DefaultNavigationDelegate
import com.makentoshe.booruchan.library.feature.DefaultStateDelegate
import com.makentoshe.booruchan.library.feature.EventDelegate
import com.makentoshe.booruchan.library.feature.NavigationDelegate
import com.makentoshe.booruchan.library.feature.StateDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class DrawerScreenViewModel @Inject constructor(
    private val flowSearchSnapshotUseCase: FlowSearchSnapshotUseCase,
) : ViewModel(), CoroutineDelegate by DefaultCoroutineDelegate(),
    StateDelegate<DrawerScreenState> by DefaultStateDelegate(DrawerScreenState.InitialState),
    EventDelegate<DrawerScreenEvent> by DefaultEventDelegate(),
    NavigationDelegate<DrawerScreenDestination> by DefaultNavigationDelegate() {

    init {
        viewModelScope.iolaunch {
            flowSearchSnapshotUseCase().collectLatest { snapshots ->
                updateState { copy(snapshots = snapshots) }
            }
        }
    }

    override fun handleEvent(event: DrawerScreenEvent) = when (event) {
        else -> Unit
    }
}
