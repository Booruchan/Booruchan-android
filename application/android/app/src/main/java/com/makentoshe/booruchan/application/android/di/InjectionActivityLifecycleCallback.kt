package com.makentoshe.booruchan.application.android.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.makentoshe.booruchan.application.android.AppActivity
import com.makentoshe.booruchan.application.android.start.di.StartInjectingFragmentLifecycleCallback
import toothpick.Toothpick

class InjectionActivityLifecycleCallback: Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity !is AppActivity) return
        Toothpick.openScopes(ApplicationScope::class.java).inject(activity)

        startInjectionFragmentLifecycleCallback(activity)
    }

    private fun startInjectionFragmentLifecycleCallback(activity: AppActivity) {
        val callback = StartInjectingFragmentLifecycleCallback()
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(callback, true)
    }

    override fun onActivityStarted(activity: Activity?) = Unit
    override fun onActivityPaused(activity: Activity?) = Unit
    override fun onActivityResumed(activity: Activity?) = Unit
    override fun onActivityStopped(activity: Activity?) = Unit
    override fun onActivityDestroyed(activity: Activity?) = Unit
    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) = Unit
}
