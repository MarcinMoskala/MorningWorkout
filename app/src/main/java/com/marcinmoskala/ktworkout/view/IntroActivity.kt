package com.marcinmoskala.ktworkout.view

import android.os.Bundle
import android.preference.PreferenceManager

import com.chyrta.onboarder.OnboarderActivity
import com.chyrta.onboarder.OnboarderPage

class IntroActivity : OnboarderActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActiveIndicatorColor(android.R.color.white)
        setInactiveIndicatorColor(android.R.color.darker_gray)
        shouldUseFloatingActionButton(true)
//        setOnboardPagesReady(listOf(
//            OnboarderPage(getString(R.string.intro1_title), getString(R.string.intro1_text), R.drawable.icon_intro).apply {
//                setTitleColor(R.color.colorAccent)
//                setBackgroundColor(R.color.colorPrimaryDark)
//            },
//            OnboarderPage(getString(R.string.intro2_title), getString(R.string.intro2_text), R.drawable.s1).apply {
//                setTitleColor(R.color.colorAccent)
//                setBackgroundColor(R.color.colorPrimaryDark)
//            },
//            OnboarderPage(getString(R.string.intro3_title), getString(R.string.intro3_text), R.drawable.s2).apply {
//                setTitleColor(R.color.colorAccent)
//                setBackgroundColor(R.color.colorPrimaryDark)
//            }
//        ))
    }

    public override fun onSkipButtonPressed() {
        super.onSkipButtonPressed()
        finish()
    }

    override fun onFinishButtonPressed() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPref.edit().putBoolean("intro_notShow", false).apply()
        finish()
    }
}
