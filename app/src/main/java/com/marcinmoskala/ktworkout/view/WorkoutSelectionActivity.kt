package com.marcinmoskala.ktworkout.view

import android.content.pm.*
import android.os.*
import android.preference.*
import android.support.v7.app.*
import android.view.*
import com.marcinmoskala.ktworkout.*
import kotlinx.android.synthetic.main.activity_main.*

class WorkoutSelectionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        exercisesButton.setOnClickListener { startExercises(warmup + exercises + stretch) }
    }

    private fun startExercises(exercises: List<Exercise>) {
        WorkoutActivity.start(this, exercises)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
