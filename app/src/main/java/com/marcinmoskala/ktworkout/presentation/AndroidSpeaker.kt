package com.marcinmoskala.ktworkout.presentation

import android.app.*
import android.media.*
import com.mapzen.speakerbox.*
import com.marcinmoskala.ktworkout.R
import java.util.*

class AndroidSpeaker(val context: Application) : Speaker {
    private var endSound = createEndSound()
    private var whistle = createWhistle()

    private var speakerbox: Speakerbox = Speakerbox(context)

    init {
        speakerbox.textToSpeech.language = Locale.ENGLISH
    }

    override fun speak(text: String) {
        try {
            speakerbox.play(text)
        } catch (e: IllegalStateException) {
            speakerbox = Speakerbox(context)
            speakerbox.play(text)
        }
    }

    override fun playWhistle() {
        try {
            whistle.prepare()
            whistle.start()
        } catch (e: IllegalStateException) {
            whistle = createWhistle()
            whistle.start()
        }
    }

    override fun playEndSound() {
        try {
            endSound.prepare()
            endSound.start()
        } catch (e: IllegalStateException) {
            endSound = createEndSound()
            endSound.start()
        }
    }

    override fun release() {
        whistle.release()
        endSound.release()
    }

    private fun createWhistle() = MediaPlayer.create(context, R.raw.whistle_blow_cc0).apply {
        setVolume(0.2f, 0.2f)
    }

    private fun createEndSound() = MediaPlayer.create(context, R.raw.victory)
}