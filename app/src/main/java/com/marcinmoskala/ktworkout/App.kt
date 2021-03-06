package com.marcinmoskala.ktworkout

import android.app.*
import com.marcinmoskala.ktworkout.presentation.*
import org.koin.android.ext.android.*
import org.koin.android.viewmodel.ext.koin.*
import org.koin.core.parameter.*
import org.koin.dsl.module.*
import kotlin.collections.get

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val androidModule = module {
            single<Timer> { AndroidTimer() }
            single<Speaker> { AndroidSpeaker(get()) }
            viewModel { WorkoutViewModel(it[0], get(), get()) }
        }
        startKoin(this, listOf(androidModule))
    }
}