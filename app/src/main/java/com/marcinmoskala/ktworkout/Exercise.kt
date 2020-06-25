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
    val prepareTime: Int = 0,
    val switchSidesTime: Int = 5,
    val p: Double = 1.0
) : Parcelable