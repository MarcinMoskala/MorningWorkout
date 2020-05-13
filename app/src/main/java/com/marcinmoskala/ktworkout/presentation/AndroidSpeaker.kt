package com.marcinmoskala.ktworkout.presentation

import android.app.*
import android.media.*
import com.mapzen.speakerbox.*
import com.marcinmoskala.ktworkout.R
import java.lang.IllegalStateException

class AndroidSpeaker(val context: Application) : Speaker {
    private val players = mutableListOf<MediaPlayer>()
    private var speakerbox: Speakerbox = Speakerbox(context)

    override fun speak(text: String) {
        try {
            speakerbox.play(text)
        } catch (e: IllegalStateException) {
            speakerbox = Speakerbox(context)
            speakerbox.play(text)
        }
    }

    override fun playWhistle() {
        val whistle = MediaPlayer.create(context, R.raw.whistle_blow_cc0).apply {
            setVolume(0.2f, 0.2f)
        }
        whistle.start()
        players += whistle
    }

    override fun playEndSound() {
        val endSound by lazy { MediaPlayer.create(context, R.raw.victory) }
        endSound.start()
        players += endSound
    }

    override fun release() {
        players.forEach { it.release() }
    }
}