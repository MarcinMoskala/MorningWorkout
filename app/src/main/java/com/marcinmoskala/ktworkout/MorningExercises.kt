package com.marcinmoskala.ktworkout

import com.marcinmoskala.ktworkout.presentation.*
import kotlin.random.*

val warmup = listOf(
        Exercise("jumping_jacks", R.drawable.jumping_jacks, "Jumping jacks", prepareTime = 5),
        Exercise("chest_expanders", R.drawable.chest_expander, "Chest expanders"),
        Exercise("forward_bend", R.drawable.forward_bend, "Dynamic forward bend", time = 25),
        Exercise("rotating_toe_touches", R.drawable.rotating_toe_touches, "Rotating Toe Touches", time = 25),
        Exercise("wrist", R.drawable.wrist_warm_up, "Wrist warm up"),
        Exercise("scarecrow_rotation", R.drawable.scarecrow_rotation, "Scarecrow Rotation"),
        Exercise("torso_rotation", R.drawable.torso_rotation, "Torso Rotation"),
        Exercise("arm_rotation", R.drawable.arm_rotation, "Arm Rotation")
//    Exercise("prone_thoracic_mobilization", R.drawable.prone_thoracic_mobilization, "Prone Thoracic Mobilization", time = 15, sides = listOf("right", "left"), p = 0.4, prepareTime = 5)
)
// Dodać rotację

val exercises = listOf(
        Exercise("pull_ups_warmup", R.drawable.pull_ups, "Pull Up Warmup", time = 25),
        Exercise("pull_ups", R.drawable.pull_ups, "Wide Grip Pull Ups", time = 25), // H
        Exercise("hips_rotation", R.drawable.hips_rotation, "Hips rotation", time = 20, switchSidesTime = 0, sides = listOf("clockwise", "counter clockwise")),
//    Exercise("leg_rise", R.drawable.leg_rise, "Leg rise", time = 45, prepareTime = 8), // H
//    Exercise("superman_hold", R.drawable.superman, "Superman hold", time = 35, prepareTime = 8),
//    Exercise("squats", R.drawable.squat, "Squats", time = 50, prepareTime = 8), // M
        Exercise("neck_rotation", R.drawable.neck_rotation, "Neck Rotation", sides = listOf("left", "right"), switchSidesTime = 0, time = 15),
        Exercise("neck_rotation_2", R.drawable.neck_rotation_2, "Neck Rotation", time = 15),
        Exercise("push_ups_warmup", R.drawable.push_ups, "Push ups warmup", time = 25),
        Exercise("push_ups", R.drawable.push_ups, "Push ups", time = 50), // H
        Exercise("cat_cow", R.drawable.cat_cow, "Cat-cow pose", time = 35, prepareTime = 8),
//    Exercise("back_stretch", R.drawable.back_stretch, "Back Stretch front"),
//    Exercise("handstand", R.drawable.handstand, "Handstand", prepareTime = 10, p = 0.9), // H
        Exercise("belly_breaths", R.drawable.belly_breaths, "Belly Breaths", time = 10),
        Exercise("stomach_vacuum", R.drawable.stomach_vacuum, "Stomach Vacuum", time = 10, sides = listOf("one", "two", "three")),
        Exercise("bridge", R.drawable.bridge, "Bridge", prepareTime = 8),
//    Exercise("bridge_full", R.drawable.bridge, "Full bridge", prepareTime = 5, time = 15), // H
        Exercise("plank", R.drawable.plank, "Plank", prepareTime = 8)
//    Exercise("close_grip_pull_ups", R.drawable.pull_ups, "Close Grip Pull Ups", time = 25, prepareTime = 8) // H
)

val stretch = listOf(
        Exercise("chin_tucks_exercise", R.drawable.chin_tucks_exercise, "Chin Tucks Exercise", time = 30, prepareTime = 5, p = 0.8),
        Exercise("neck_flexion", R.drawable.neck_flexion, "Neck Flexion (Suboccipital Stretch)", time = 30, p = 0.8),
        Exercise("doorway_stretch", R.drawable.doorway_stretch, "Doorway Stretch", time = 30, prepareTime = 5, p = 0.8),
        Exercise("shoulder_blade_squeeze", R.drawable.shoulder_blade_squeeze, "Shoulder Blade Squeeze", time = 30, prepareTime = 5, p = 0.5),
        Exercise("upper_trap_stretch", R.drawable.upper_trap_stretch, "Upper Trap Stretch", sides = listOf("right", "left"), prepareTime = 5, p = 0.8),
        Exercise("mountain", R.drawable.mountain, "Mountain", time = 20, prepareTime = 5, p = 0.8),
        Exercise("forward_bend", R.drawable.forward_bend, "Forward bend", time = 20, p = 0.8),
        Exercise("downward_dog", R.drawable.downward_dog, "Downward-Facing Dog", time = 25, p = 0.8),
        Exercise("cobra", R.drawable.cobra_pose, "Cobra Pose", time = 20, p = 0.5),
        Exercise("back_stretch", R.drawable.back_stretch, "Back Stretch front", p = 0.5),
        Exercise("biceps_and_wrist_stretch", R.drawable.biceps_stretch, "Biceps and Wrist Stretch", p = 0.8),
        Exercise("other_site_wrist_stretch", R.drawable.wrist_stretch_2, "Other site Wrist Stretch", p = 0.8),
        Exercise("chest_stretch", R.drawable.chest_stretch, "Chest Stretch", sides = listOf("right", "left"), p = 0.4, prepareTime = 3),
        Exercise("shoulder_streatch", R.drawable.shoulder_stretch, "Shoulder Stretch", sides = listOf("right", "left"), p = 0.5, prepareTime = 3),
        Exercise("triceps_stretch", R.drawable.triceps_stretch, "Triceps Stretch", sides = listOf("right", "left"), p = 0.4, prepareTime = 3),
        Exercise("bear_hug", R.drawable.bear_hug, "Bear Hug", p = 0.3, prepareTime = 3),
        Exercise("leg_standing_stretch", R.drawable.legstretch, "Leg stretch while standing", sides = listOf("right", "left"), prepareTime = 8, p = 0.3),
        Exercise("obliques_stretches", R.drawable.obliques_stretches, "Obliques stretches", sides = listOf("right", "left"), prepareTime = 3, p = 0.3),
        Exercise("lower_back", R.drawable.lowerback, "Lower Back Stretch", sides = listOf("right", "left"), p = 0.1),
        Exercise("knee_to_chest", R.drawable.knee_to_chest, "Knee to chest", p = 0.1),
        Exercise("long_adductor_stretch", R.drawable.long_adductor_stretch, "Long Adductor Stretch", p = 0.5, prepareTime = 5),
        Exercise("standing_hamstring_stretch", R.drawable.standing_hamstring_stretch, "Standing Hamstring Stretch", sides = listOf("right", "left"), p = 0.3),
        Exercise("windshield_wipers", R.drawable.windshield_wipers, "Windshield Wipers", sides = listOf("right", "left"), p = 0.4, prepareTime = 5),
        Exercise("upper_trap_stretch_front", R.drawable.upper_trap_stretch, "Upper Trap Front Stretch", sides = listOf("right", "left"), time = 15, switchSidesTime = 0, p = 0.3),
        Exercise("upper_trap_stretch_back", R.drawable.upper_trap_stretch, "Upper Trap Back Stretch", sides = listOf("right", "left"), time = 15, switchSidesTime = 0, p = 0.3)
)

val allExercises = warmup + exercises + stretch

val randomExercises = allExercises
        .filter { it.p > Random.nextDouble(0.0, 1.0) }

val planMinimumExercises = allExercises
        .filter { it.p >= 1.0 }

fun main() {
    println("Plan minimum should take " + planMinimumExercises.caculateAverageTime())
    println("All should take on average " + allExercises.caculateAverageTime())
    println("Warmup should take on average " + warmup.caculateAverageTime())
    println("Exercises should take on average " + exercises.caculateAverageTime())
    println("Stretch should take on average " + stretch.caculateAverageTime())
    println()
    println("Full workout should take " + fullWorkout.caculateAverageTime())

}

private fun List<Exercise>.caculateAverageTime() = sumByDouble {
    (
            (it.time * (it.sides?.size ?: 1)) +
            it.prepareTime +
            (it.switchSidesTime * ((it.sides?.size ?: 1) - 1))
    ) * it.p
}.toInt().displayTime()
