package com.maltmannapp.demoapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val info = intent.getStringExtra(ACTIVITY_INPUT_KEY)
        val textView: TextView = findViewById(R.id.textView2)
        textView.text = info

        val inputText: EditText = findViewById(R.id.inputText)

        val buttonOK: Button = findViewById(R.id.buttonOK)
        buttonOK.setOnClickListener {
            val data = Intent()
            if (inputText.text.toString().length == 0) {
                data.putExtra(ACTIVITY_RESULT_KEY, "Null input")
            } else {
                data.putExtra(ACTIVITY_RESULT_KEY, inputText.text.toString())
            }
            setResult(RESULT_OK, data)
            finish()
        }

        val buttonCancel: Button = findViewById(R.id.buttonCancel)
        buttonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

    }
}