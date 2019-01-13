package com.marcinmoskala.ktworkout.presentation

import android.app.*
import android.arch.lifecycle.*
import android.databinding.*
import com.marcinmoskala.ktworkout.*
import com.marcinmoskala.ktworkout.R

class WorkoutViewModel(
    app: Application,
    val exercises: List<Exercise>,
    private val timer: Timer,
    private val speaker: Speaker
) : AndroidViewModel(app) {
    val title = ObservableField<String>("")
    val timeDisplay = ObservableField<String>("")
    val exercisesCounterDisplay = ObservableField<String>("")
    val minutesLeftDisplay = ObservableField<String>("")
    val progressBarPercentage = ObservableInt(0)
    val progressVisible = ObservableBoolean()
    val image = ObservableInt()

    private val states: List<State> = exercises.withIndex().flatMap(::toSeries) + DoneState
    private var state: State = states.first()

    fun onStart() {
        showState(state)
    }

    fun onResume() {
        timer.start()
    }

    fun onStop() {
        timer.stop()
    }

    fun onNext() {
        val position = states.indexOf(state)
        if (position < states.size) {
            state = states[position + 1]
        }
        showState(state)
    }

    fun onPrevious() {
        val position = states.indexOf(state)
        if (position > 0) {
            state = states[position - 1]
        }
        showState(state)
    }

    private fun showState(state: State) {
        setUpTimer(state.time)

        val titleText = when (state) {
            is DoneState -> "Done"
            is ExerciseState -> state.exercise.nameText
            is PrepareState -> "Prepare for " + state.exercise.nameText
        }
        title.set(titleText)
        speaker.speak(titleText)

        val exerciseIndex = when (state) {
            is DoneState -> null
            is ExerciseState -> state.index
            is PrepareState -> state.index
        }
        val exerciseCountText = exerciseIndex?.let { "${it + 1}/${exercises.size}" } ?: ""
        exercisesCounterDisplay.set(exerciseCountText)

        if (state !is DoneState) {
            speaker.playWhistle()
        } else {
            speaker.playEndSound()
        }

        val imageResource = when (state) {
            is DoneState -> R.drawable.done
            is ExerciseState -> state.exercise.imgId
            is PrepareState -> state.exercise.imgId
        }
        image.set(imageResource)

        progressVisible.set(state !is DoneState)
        progressBarPercentage.set(0)
    }

    private fun setUpTimer(durationSeconds: Int?) {
        if(durationSeconds == null) {
            timeDisplay.set("")
            return
        }
        timeDisplay.set("$durationSeconds")
        val otherTimeToEnd = calculateTimeLeftInOtherExercises()
        fun wholeTimeLeftInMinutes() = (otherTimeToEnd + durationSeconds) / 60
        minutesLeftDisplay.set("${wholeTimeLeftInMinutes()}")
        timer.start(durationSeconds,
            onTick = { secondsUntilFinished ->
                val seconds = durationSeconds - secondsUntilFinished
                progressBarPercentage.set(seconds * 100 / durationSeconds)
                timeDisplay.set("$secondsUntilFinished")
                minutesLeftDisplay.set("${wholeTimeLeftInMinutes()}")
            },
            onFinish = this::onNext
        )
    }

    private fun calculateTimeLeftInOtherExercises() = states
        .drop(states.indexOf(state) + 1) // Drop all prev and this state
        .sumBy {
            when (it) {
                is ExerciseState -> it.exercise.time
                is PrepareState -> it.exercise.prepareTime
                is DoneState -> 0
            }
        }

    private fun toSeries(indexedExercise: IndexedValue<Exercise>): List<State> = when (val sides = indexedExercise.value.sides) {
        null -> indexedExercise.toStates()
        else -> sides.withIndex().flatMap { (sideIndex, sideName) ->
            val (index, exercise) = indexedExercise
            val prepareTime = if (sideIndex == 0) exercise.prepareTime else exercise.switchSidesTime
            val nameText = "${exercise.nameText} $sideName"
            val exerciseFromSeries = exercise.copy(prepareTime = prepareTime, nameText = nameText, sides = null)
            toSeries(IndexedValue(index, exerciseFromSeries))
        }
    }

    private fun IndexedValue<Exercise>.toStates() =
        listOf(PrepareState(index, value), ExerciseState(index, value))
}

private sealed class State {
    abstract val time: Int?
}

private object DoneState : State() {
    override val time: Int? = null
}

private class ExerciseState(val index: Int, val exercise: Exercise) : State() {
    override val time: Int?
        get() = exercise.time
}

private class PrepareState(val index: Int, val exercise: Exercise) : State() {
    override val time: Int?
        get() = exercise.prepareTime
}