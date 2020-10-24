package com.makentoshe.booruchan.application.android.start.navigation

import androidx.fragment.app.Fragment
import com.makentoshe.booruchan.application.android.start.view.StartFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class StartScreen : SupportAppScreen() {
    override fun getFragment(): Fragment? {
        return StartFragment.build()
    }
}
