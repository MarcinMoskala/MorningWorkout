package com.marcinmoskala.ktworkout

import android.app.*
import com.marcinmoskala.ktworkout.presentation.*

class App : Application() {
    val speaker by lazy { AndroidSpeaker(this) }

    override fun onCreate() {
        super.onCreate()
        speaker
    }
}