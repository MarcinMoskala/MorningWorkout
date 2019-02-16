package com.marcinmoskala.ktworkout

import android.os.*
import kotlinx.android.parcel.*

@Parcelize
data class Exercise(
    val key: String,
    val imgId: Int,
    val nameText: String,
    val sides: List<String>? = null,
    val time: Int = 30,
    val prepareTime: Int = 5,
    val switchSidesTime: Int = 5
) : Parcelable

val warmup = listOf(
    Exercise("jumping_jacks", R.drawable.jumping_jacks, "Jumping jacks"),
    Exercise("chest_expanders", R.drawable.chest_expander, "Chest expanders"),
    Exercise("rotating_toe_touches", R.drawable.rotating_toe_touches, "Rotating Toe Touches"),
    Exercise("hips_rotation", R.drawable.hips_rotation, "Hips rotation", time = 20, sides = listOf("clockwise", "counter clockwise")),
    Exercise("forward_bend", R.drawable.forward_bend, "Dynamic forward bend"),
    Exercise("wrist", R.drawable.wrist_warm_up, "Wrist warm up"),
    Exercise("wall_push_up", R.drawable.wall_push_ups, "Wall push ups", prepareTime = 8)
)

val exercises = listOf(
    Exercise("leg_rise", R.drawable.leg_rise, "Leg rise", time = 35, prepareTime = 8),
    Exercise("superman_hold", R.drawable.superman, "Superman hold", prepareTime = 8),
    Exercise("squats", R.drawable.squat, "Squats", time = 50, prepareTime = 8),
    Exercise("push_ups", R.drawable.push_ups, "Push ups", time = 35, prepareTime = 8),
    Exercise("handstand", R.drawable.handstand, "Handstand", prepareTime = 8),
    Exercise("bridge", R.drawable.bridge, "Bridge", prepareTime = 10),
//    Exercise("bridge_full", R.drawable.bridge, "Full bridge", prepareTime = 5),
    Exercise("plank", R.drawable.plank, "Plank")
)

val randomStretch = listOf(
    Exercise("chest_stretch", R.drawable.chest_stretch, "Chest Stretch", sides = listOf("right", "left")),
    Exercise("shoulder_streatch", R.drawable.shoulder_stretch, "Shoulder Stretch", sides = listOf("right", "left")),
    Exercise("triceps_stretch", R.drawable.triceps_stretch, "Triceps Stretch", sides = listOf("right", "left")),
    Exercise("leg_standing_stretch", R.drawable.legstretch, "Leg stretch while standing", sides = listOf("right", "left"), prepareTime = 8)
).shuffled().take(2).toTypedArray()

val stretch = listOf(
    Exercise("forward_bend", R.drawable.forward_bend, "Forward bend"),
    Exercise("downward_dog", R.drawable.downward_dog, "Downward-Facing Dog"),
    Exercise("biceps_and_wrist_stretch", R.drawable.biceps_stretch, "Biceps and Wrist Stretch", prepareTime = 8),
    Exercise("other_site_wrist_stretch", R.drawable.wrist_stretch_2, "Other site Wrist Stretch"),
    *randomStretch,
    Exercise("upper_trap_stretch", R.drawable.upper_trap_stretch, "Upper Trap Stretch", sides = listOf("right", "left")),
    Exercise("back_stretch", R.drawable.back_stretch, "Back Stretch", sides = listOf("front", "right", "left"))
//    Exercise("lower_back", R.drawable.lowerback, "Lower Back Stretch", sides = listOf("right", "left")),
//    Exercise("windshield_wipers", R.drawable.windshield_wipers, "Windshield Wipers", sides = listOf("right", "left"))
)

