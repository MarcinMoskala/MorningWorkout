package com.marcinmoskala.ktworkout.view

import android.databinding.BindingAdapter
import android.view.*
import android.widget.*

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("android:imageResource")
fun setImageResource(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}