package com.maltmannapp.demoapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

val TWO_ACTIVITY_INPUT_KEY = "300"
val TWO_ACTIVITY_RESULT_KEY = "400"

class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
    }
}

