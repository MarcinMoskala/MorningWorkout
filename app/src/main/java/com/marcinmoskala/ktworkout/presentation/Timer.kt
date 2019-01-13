package com.marcinmoskala.ktworkout.presentation

interface Timer {
    fun start(seconds: Int, onTick: (Int)->Unit, onFinish: ()->Unit)
    fun stop()
    fun start()
}