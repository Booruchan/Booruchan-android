package com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android.model.BooruPostPagingDataAdapter
import com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android.view.SpacesItemDecoration
import com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android.view.SpannedGridLayoutManager
import com.makentoshe.booruchan.screen.boorucontent.ui.foundation.android.view.SpannedGridLayoutManagerLookup
import com.makentoshe.booruchan.screen.boorucontent.viewmodel.BoorucontentState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun RecyclerViewVerticalSpannedGrid(state: BoorucontentState) {
    val coroutineScope = rememberCoroutineScope()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = ::recyclerViewFactory,
        update = { recyclerView ->
            val adapter = recyclerView.adapter as BooruPostPagingDataAdapter

            coroutineScope.launch {
                state.pagerFlow.collectLatest { adapter.submitData(it) }
            }
        },
    )
}

private fun recyclerViewFactory(context: Context) = RecyclerView(context).apply {
    val adapter = BooruPostPagingDataAdapter()

    val orientation = SpannedGridLayoutManager.Orientation.VERTICAL
    this.layoutManager = SpannedGridLayoutManager(orientation, 3).apply {
        spanSizeLookup = SpannedGridLayoutManagerLookup(adapter)
    }

    addItemDecoration(SpacesItemDecoration(8f))

    this.adapter = adapter
}
