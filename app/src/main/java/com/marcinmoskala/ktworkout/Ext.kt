package com.marcinmoskala.ktworkout

import android.content.*
import android.databinding.*
import android.preference.*
import android.support.v4.app.*
import android.view.*

val Context.pref: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

val Fragment.pref: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(context)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun <T> ObservableField<T>.setOnChangedListener(onChanged: (T?)->Unit) {
    addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            onChanged(get())
        }
    })
}