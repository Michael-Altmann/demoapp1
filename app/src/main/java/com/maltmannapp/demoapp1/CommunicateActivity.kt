package com.maltmannapp.demoapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

val MESSAGE_KEY = "message_key"
val RESULT_OK = "result_ok"
val ACTIVITY_RESULT_KEY = "100"
val ACTIVITY_INPUT_KEY = "200"

class CommunicateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicate)

        val textView: TextView = findViewById(R.id.textviewCommunicate)
        textView.text = intent.getStringExtra(MESSAGE_KEY)

        TODO("1:39:00")
    }
}