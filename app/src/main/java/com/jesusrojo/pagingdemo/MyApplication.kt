package com.jesusrojo.pagingdemo

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            setupTimber()
            setupLeakCanary()
        }
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    //LEAK CANARY
    private var mRefWatcher: RefWatcher? = null

    private fun setupLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            mRefWatcher = LeakCanary.install(this)
        }
    }

    fun mustDieLeakCanary(`object`: Any?) {
        mRefWatcher?.watch(`object`)
    }
}
