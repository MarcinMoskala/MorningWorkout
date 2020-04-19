package com.marcinmoskala.ktworkout.presentation

import android.app.*
import android.media.*
import com.mapzen.speakerbox.*
import com.marcinmoskala.ktworkout.R

class AndroidSpeaker(context: Application) : Speaker {

    private val whistle by lazy {
        MediaPlayer.create(context, R.raw.whistle_blow_cc0).apply {
            setVolume(0.2f, 0.2f)
        }
    }
    private val endSound by lazy { MediaPlayer.create(context, R.raw.victory) }
    private var speakerbox: Speakerbox = Speakerbox(context)

    override fun speak(text: String) {
        speakerbox.play(text)
    }

    override fun playWhistle() {
        whistle.start()
    }

    override fun playEndSound() {
        endSound.start()
    }

    override fun release() {
        whistle.release()
        endSound.release()
    }
}