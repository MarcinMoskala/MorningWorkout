package com.marcinmoskala.ktworkout.presentation

import android.arch.lifecycle.*
import android.databinding.*
import com.marcinmoskala.ktworkout.*
import com.marcinmoskala.ktworkout.R

class WorkoutViewModel(
    private val exercises: List<Exercise>,
    private val timer: Timer,
    private val speaker: Speaker
) : ViewModel() {

    val title = ObservableField("")
    val timeDisplay = ObservableField("")
    val exercisesCounterDisplay = ObservableField("")
    val minutesLeftDisplay = ObservableField("")
    val progressBarPercentage = ObservableInt(0)
    val progressVisible = ObservableBoolean()
    val image = ObservableInt()

    private val states: List<State> = exercises.withIndex().flatMap(::toSeries) + DoneState
    private var state: State = states.first()

    fun onStart() {
        showState()
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
        showState()
    }

    fun onPrevious() {
        val position = states.indexOf(state)
        if (position > 0) {
            state = states[position - 1]
        }
        showState()
    }

    private fun showState() {
        val titleText: String
        when (val state = state) {
            is PrepareState -> {
                speaker.playWhistle()
                titleText = "Prepare for " + state.exercise.nameText
                setExercisesCountDisplay(state.index)
                image.set(state.exercise.imgId)
            }
            is ExerciseState -> {
                speaker.playWhistle()
                titleText = state.exercise.nameText
                setExercisesCountDisplay(state.index)
                image.set(state.exercise.imgId)
            }
            is DoneState -> {
                speaker.playEndSound()
                titleText = "Done"
                setExercisesCountDisplay(null)
                image.set(R.drawable.done)
            }
        }
        title.set(titleText)
        speaker.speak(titleText)
        setUpTimer(state.time)
    }

    private fun setExercisesCountDisplay(index: Int?) {
        val text = if (index == null) "" else "${index + 1}/${exercises.size}"
        exercisesCounterDisplay.set(text)
    }

    private fun setUpTimer(durationSeconds: Int?) {
        progressVisible.set(durationSeconds == null)
        if (durationSeconds == null) {
            timeDisplay.set("")
            return
        }
        timeDisplay.set("$durationSeconds")
        progressBarPercentage.set(0)
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