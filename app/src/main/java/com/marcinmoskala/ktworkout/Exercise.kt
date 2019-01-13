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
    val prepareTime: Int = 8,
    val switchSidesTime: Int = 5
) : Parcelable

val warmup = listOf(
    Exercise("jumping_jacks", R.drawable.jumping_jacks, "Jumping jacks", prepareTime = 5),
    Exercise("chest_expanders", R.drawable.chest_expander, "Chest expanders", prepareTime = 5),
    Exercise("forward_bend", R.drawable.forward_bend, "Dynamic forward bend", prepareTime = 5),
    Exercise("wrist", R.drawable.wrist_warm_up, "Wrist warm up", prepareTime = 5),
    Exercise("wall_push_up", R.drawable.wall_push_ups, "Wall push ups")
)

val exercises = listOf(
    Exercise("leg_rise", R.drawable.leg_rise, "Leg rise", time = 35),
    Exercise("push_ups", R.drawable.push_ups, "Push ups"),
    Exercise("squats", R.drawable.squat, "Squats", time = 40),
    Exercise("handstand", R.drawable.handstand, "Handstand"),
    Exercise("superman_hold", R.drawable.superman, "Superman hold", prepareTime = 10),
    Exercise("bridge", R.drawable.bridge, "Bridge"),
//    Exercise("bridge_full", R.drawable.bridge, "Full bridge", prepareTime = 5),
    Exercise("plank", R.drawable.plank, "Plank")
)

val stretch = listOf(
    Exercise("chest_stretch", R.drawable.chest_stretch, "Chest Stretch", sides = listOf("right", "left"), prepareTime = 5),
    Exercise("shoulder_streatch", R.drawable.shoulder_stretch, "Shoulder Stretch", sides = listOf("right", "left"), prepareTime = 5),
    Exercise("triceps_stretch", R.drawable.triceps_stretch, "Triceps Stretch", sides = listOf("right", "left"), prepareTime = 5),
    Exercise("biceps_and_wrist_stretch", R.drawable.biceps_stretch, "Biceps and Wrist Stretch"),
    Exercise("leg_standing_stretch", R.drawable.legstretch, "Leg stretch while standing", sides = listOf("right", "left")),
    Exercise("other_site_wrist_stretch", R.drawable.wrist_stretch_2, "Other site Wrist Stretch", prepareTime = 5),
    Exercise("upper_trap_stretch", R.drawable.upper_trap_stretch, "Upper Trap Stretch", sides = listOf("right", "left"), prepareTime = 5),
    Exercise("back_stretch", R.drawable.back_stretch, "Back Stretch", sides = listOf("front", "right", "left")),
    Exercise("lower_back", R.drawable.lowerback, "Lower Back Stretch", sides = listOf("right", "left")),
    Exercise("windshield_wipers", R.drawable.windshield_wipers, "Windshield Wipers", sides = listOf("right", "left"))
)