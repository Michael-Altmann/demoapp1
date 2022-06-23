package com.maltmannapp.demoapp1

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.time.LocalTime

val TAG = "MainActivity"

fun myLog(info: Any?) {
    if (info != null)
        Log.d(TAG, info.toString())
}

fun showToast(context: Context, info: String) {
    Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
}