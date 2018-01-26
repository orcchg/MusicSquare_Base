package com.orcchg.musicsquare

import android.support.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

class MusicSquareApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        Timber.plant(Timber.DebugTree())
    }
}
