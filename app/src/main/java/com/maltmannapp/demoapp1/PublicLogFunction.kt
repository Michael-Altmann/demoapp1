package com.maltmannapp.demoapp1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import java.time.LocalTime
import kotlin.contracts.contract

val TAG = "MainActivity"

fun myLog(info: Any?) {
    if (info != null)
        Log.d(TAG, info.toString())
}

fun showToast(context: Context, info: String) {
    Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
}

