package com.maltmannapp.demoapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

val MESSAGE_KEY = "message_key"

class CommunicateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicate)

        val textView: TextView = findViewById(R.id.textviewCommunicate)
        textView.text = intent.getStringExtra(MESSAGE_KEY)

        TODO("1:30:00")
    }
}