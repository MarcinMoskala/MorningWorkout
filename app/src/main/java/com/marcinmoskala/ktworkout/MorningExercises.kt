package com.marcinmoskala.ktworkout

import android.os.*
import com.marcinmoskala.ktworkout.presentation.*
import kotlinx.android.parcel.*
import kotlin.random.*

@Parcelize
data class Exercise(
    val key: String,
    val imgId: Int,
    val nameText: String,
    val sides: List<String>? = null,
    val time: Int = 30,
    val prepareTime: Int = 5,
    val switchSidesTime: Int = 5,
    val p: Double = 1.0
) : Parcelable

val warmup = listOf(
    Exercise("jumping_jacks", R.drawable.jumping_jacks, "Jumping jacks"),
    Exercise("chest_expanders", R.drawable.chest_expander, "Chest expanders"),
    Exercise("forward_bend", R.drawable.forward_bend, "Dynamic forward bend", time = 20),
    Exercise("rotating_toe_touches", R.drawable.rotating_toe_touches, "Rotating Toe Touches", time = 20),
    Exercise("wrist", R.drawable.wrist_warm_up, "Wrist warm up"),
    Exercise("prone_thoracic_mobilization", R.drawable.prone_thoracic_mobilization, "Prone Thoracic Mobilization", time = 15, sides = listOf("right", "left"), p = 0.5)
)

val exercises = listOf(
    Exercise("pull_ups", R.drawable.pull_ups, "Wide Grip Pull Ups", time = 10), // H
    Exercise("hips_rotation", R.drawable.hips_rotation, "Hips rotation", time = 20, switchSidesTime = 0, sides = listOf("clockwise", "counter clockwise")),
    Exercise("leg_rise", R.drawable.leg_rise, "Leg rise", time = 45, prepareTime = 8), // H
    Exercise("superman_hold", R.drawable.superman, "Superman hold", time = 35, prepareTime = 8),
    Exercise("squats", R.drawable.squat, "Squats", time = 50, prepareTime = 8), // M
    Exercise("push_ups", R.drawable.push_ups, "Push ups", time = 35, prepareTime = 8), // H
    Exercise("cat_cow", R.drawable.cat_cow, "Cat-cow pose", time = 35, prepareTime = 8),
    Exercise("handstand", R.drawable.handstand, "Handstand", prepareTime = 10, p = 0.9), // H
    Exercise("belly_breaths", R.drawable.belly_breaths, "Belly Breaths", time = 10, prepareTime = 8),
    Exercise("stomach_vacuum", R.drawable.stomach_vacuum, "Stomach Vacuum", time = 10, sides = listOf("one", "two", "three")),
    Exercise("bridge", R.drawable.bridge, "Bridge", prepareTime = 12),
    Exercise("bridge_full", R.drawable.bridge, "Full bridge", prepareTime = 5, time = 10), // H
    Exercise("plank", R.drawable.plank, "Plank", prepareTime = 8),
    Exercise("pull_ups", R.drawable.pull_ups, "Close Grip Pull Ups", time = 10) // H
)

val stretch = listOf(
    Exercise("mountain", R.drawable.mountain, "Mountain", time = 20),
    Exercise("forward_bend", R.drawable.forward_bend, "Forward bend"),
    Exercise("downward_dog", R.drawable.downward_dog, "Downward-Facing Dog"),
    Exercise("cobra", R.drawable.cobra_pose, "Cobra Pose"),
    Exercise("back_stretch", R.drawable.back_stretch, "Back Stretch front"),
    Exercise("biceps_and_wrist_stretch", R.drawable.biceps_stretch, "Biceps and Wrist Stretch", prepareTime = 8),
    Exercise("other_site_wrist_stretch", R.drawable.wrist_stretch_2, "Other site Wrist Stretch"),
    Exercise("chest_stretch", R.drawable.chest_stretch, "Chest Stretch", sides = listOf("right", "left"), p = 0.5),
    Exercise("shoulder_streatch", R.drawable.shoulder_stretch, "Shoulder Stretch", sides = listOf("right", "left"), p = 0.6),
    Exercise("triceps_stretch", R.drawable.triceps_stretch, "Triceps Stretch", sides = listOf("right", "left"), p = 0.5),
    Exercise("bear_hug", R.drawable.bear_hug, "Bear Hug", p = 0.4),
    Exercise("leg_standing_stretch", R.drawable.legstretch, "Leg stretch while standing", sides = listOf("right", "left"), prepareTime = 8, p = 0.5),
    Exercise("obliques_stretches", R.drawable.obliques_stretches, "Obliques stretches", sides = listOf("right", "left"), prepareTime = 3, p = 0.5),
    Exercise("lower_back", R.drawable.lowerback, "Lower Back Stretch", sides = listOf("right", "left"), p = 0.5),
    Exercise("knee_to_chest", R.drawable.knee_to_chest, "Knee to chest", p = 0.5),
    Exercise("long_adductor_stretch", R.drawable.long_adductor_stretch, "Long Adductor Stretch", p = 0.5),
    Exercise("standing_hamstring_stretch", R.drawable.standing_hamstring_stretch, "Standing Hamstring Stretch", sides = listOf("right", "left"), p = 0.5),
    Exercise("windshield_wipers", R.drawable.windshield_wipers, "Windshield Wipers", sides = listOf("right", "left"), p = 0.6),
    Exercise("upper_trap_stretch", R.drawable.upper_trap_stretch, "Upper Trap Stretch", sides = listOf("right", "left")),
    Exercise("upper_trap_stretch_front", R.drawable.upper_trap_stretch, "Upper Trap Front Stretch", sides = listOf("right", "left"), time = 15, switchSidesTime = 0, p = 0.6),
    Exercise("upper_trap_stretch_back", R.drawable.upper_trap_stretch, "Upper Trap Back Stretch", sides = listOf("right", "left"), time = 15, switchSidesTime = 0, p = 0.6)
)

val allExercises = warmup + exercises + stretch

val randomExercises = allExercises
    .filter { it.p > Random.nextDouble(0.0, 1.0) }

val planMinimumExercises = allExercises
    .filter { it.p >= 1.0 }

val coldShowerPlan = listOf(
    Exercise("warm_shower1", R.drawable.shower, "Warm shower", time = 10, prepareTime = 0),
    Exercise("cold_shower1", R.drawable.shower, "Cold shower", time = 10, prepareTime = 0),
    Exercise("warm_shower2", R.drawable.shower, "Warm shower", time = 10, prepareTime = 0),
    Exercise("cold_shower2", R.drawable.shower, "Cold shower", time = 10, prepareTime = 0),
    Exercise("warm_shower3", R.drawable.shower, "Warm shower", time = 10, prepareTime = 0),
    Exercise("cold_shower3", R.drawable.shower, "Cold shower", time = 10, prepareTime = 0)
)

fun main() {
    println("Plan minimum should take " + planMinimumExercises.caculateAverageTime())
    println("All should take on average " + allExercises.caculateAverageTime())
    println("Warmup should take on average " + warmup.caculateAverageTime())
    println("Exercises should take on average " + exercises.caculateAverageTime())
    println("Stretch should take on average " + stretch.caculateAverageTime())
}

private fun List<Exercise>.caculateAverageTime() = sumByDouble {
    ((it.time * (it.sides?.size ?: 1)) + it.prepareTime + (it.switchSidesTime * ((it.sides?.size
        ?: 1) - 1))) * it.p
}
    .toInt().displayTime()
