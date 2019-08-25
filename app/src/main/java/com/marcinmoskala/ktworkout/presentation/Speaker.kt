package com.marcinmoskala.ktworkout.presentation

interface Speaker {
    fun speak(text: String)
    fun playWhistle()
    fun playEndSound()
    fun release()
}