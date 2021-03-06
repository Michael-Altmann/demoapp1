package com.maltmannapp.demoapp1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.net.URI
import java.time.LocalTime


class MainActivity : AppCompatActivity() {

    lateinit var launcher1: ActivityResultLauncher<Intent>
    lateinit var launcher2: ActivityResultLauncher<Int>
    lateinit var photoPicker: ActivityResultLauncher<Intent>

    private lateinit var textView: TextView
    private var counter = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView1)
        // Register a context menu for textview(Long press)
        registerForContextMenu(textView)

        val button = findViewById<Button>(R.id.button1)

        // Recover instance state even if device rotated
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("counter")
            textView.text = "$counter"
        }

        button.setOnClickListener {
            counter++
            textView.text = "Clicked $counter"
        }


        // Output a debug message OLD
        // Log.d("MainActivity", "Hi!")
        // More info in PublicLogFunction file
        myLog("Hello")

        // Show toast by click, more info in PLF file
        val buttonToast: Button = findViewById(R.id.button2)
        buttonToast.setOnClickListener {
            showToast(this, "Local time: ${LocalTime.now()}")
        }

        // Build a alert dialog with Builder call
        val alertButton: Button = findViewById(R.id.button3)
        alertButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("This is a Alert Dialog")
                .setPositiveButton("OK") { _, _ ->
                    textView.text = "OK"
                }
                .setNegativeButton("Cancel") { _, _ ->
                    textView.text = "Canceled"
                }
                .create()
                .show()
        }

        // Transfer "it" to button and change text
        val textChangeButton: Button = findViewById(R.id.button4)
        textChangeButton.setOnClickListener {
            counter++
            (it as Button).text = "$counter"
        }

        // Long press action
        val longClickButton: Button = findViewById(R.id.button5)
        longClickButton.setOnLongClickListener {
            showToast(this, "Long clicked.")
            true
        }

        // Tap to change text color
        val colorChangeButton: Button = findViewById(R.id.button6)
        var indicator = 0
        colorChangeButton.setOnClickListener {
            indicator = if (indicator == 1) {
                (it as Button).setTextColor(getColor(R.color.black))
                0
            } else {
                (it as Button).setTextColor(getColor(R.color.white))
                1
            }
        }

        // Tap to disable another button
        val enableButton1: Button = findViewById(R.id.button7)
        val enableButton2: Button = findViewById(R.id.button8)
        enableButton1.setOnClickListener {
            (it as Button).isEnabled = false
            enableButton2.isEnabled = true
        }
        enableButton2.setOnClickListener {
            (it as Button).isEnabled = false
            enableButton1.isEnabled = true
        }

        // Imageview
        val picView: ImageView = findViewById(R.id.imageView)
        val picChangeButton: Button = findViewById(R.id.button9)
        picChangeButton.setOnClickListener {
            picView.setImageResource(R.drawable._11mmxrlnys__ac_sl1500_)
        }

        // Edit text
        val callButton: Button = findViewById(R.id.callButton)
        val editPhone: EditText = findViewById(R.id.inputText)
        editPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val strLength = s.toString().length
                if (strLength == 11) {
                    textView.text = "$s"
                } else {
                    textView.text = "${11 - strLength} remaining"
                }
            }
        })

        // Call phone function and start activity with intent
        fun callPhone(phoneNumber: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            val data = Uri.parse("tel: $phoneNumber")
            intent.data = data
            startActivity(intent)
        }

        // Call button
        callButton.setOnClickListener {
            val phoneNumber = editPhone.text.toString().length
            if (phoneNumber != 11) {
                AlertDialog.Builder(this)
                    .setMessage("Invalid number")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create().show()
            } else {
                callPhone(editPhone.text.toString())
            }
        }

        // Radio button that change the picture's scale type
        val buttonCenterInside: RadioButton = findViewById(R.id.centerInside)
        val buttonFitStart: RadioButton = findViewById(R.id.fitsStart)
        val buttonFitEnd: RadioButton = findViewById(R.id.fitEnd)
        buttonCenterInside.setOnClickListener {
            picView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        buttonFitStart.setOnClickListener {
            picView.scaleType = ImageView.ScaleType.FIT_START
        }
        buttonFitEnd.setOnClickListener {
            picView.scaleType = ImageView.ScaleType.FIT_END
        }

        // Roll button
        val rollButton: Button = findViewById(R.id.buttonRoll)
        rollButton.setOnClickListener {
            textView.text = (1..6).random().toString()
        }

        // New activity button
        val newActivityButton: Button = findViewById(R.id.buttonActivity)
        newActivityButton.setOnClickListener {
            val intent = Intent(this, LayoutActivity::class.java)
            startActivity(intent)
        }

        // Send intent with bundle (ep4-1:21)
        val communicateButton: Button = findViewById(R.id.comunicateActiviteButton)
        communicateButton.setOnClickListener {
            val intent = Intent(this, CommunicateActivity::class.java)
            intent.putExtra(MESSAGE_KEY, textView.text.toString())
            startActivity(intent)
        }

        // Set a launcher
        launcher1 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                textView.text = it.data?.getStringExtra(ACTIVITY_RESULT_KEY) ?: "No data"
            } else {
                textView.text = "Canceled"
            }
        }

        // Set a button and launch
        val activityButton1: Button = findViewById(R.id.buttonLauncher1)
        activityButton1.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            intent.putExtra(ACTIVITY_INPUT_KEY, textView.text.toString())
            launcher1.launch(intent)
        }

        class MyResultContract : ActivityResultContract<Int, String>() {
            override fun createIntent(context: Context, input: Int?): Intent {
                val data = Intent(context, TwoActivity::class.java)
                data.putExtra(TWO_ACTIVITY_INPUT_KEY, input ?: 0)
                return data
            }

            override fun parseResult(resultCode: Int, intent: Intent?): String {
                val number = intent?.getIntExtra(TWO_ACTIVITY_RESULT_KEY, -1)
                return "$number"
            }
        }

        launcher2 = registerForActivityResult(MyResultContract()) {
            textView.text = it
        }

        val activityButton2: Button = findViewById(R.id.buttonLauncher2)
        activityButton2.setOnClickListener {
            launcher2.launch(100)
        }

        // Define how to decode a pic to bitmap.
        fun tryReadBitmap(data: Uri): Bitmap? {
            return try {
                val source = ImageDecoder.createSource(contentResolver, data)
                ImageDecoder.decodeBitmap(source)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }

        // Crash fixed! Build a photo picker.
        photoPicker = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val bitmap = tryReadBitmap(it.data?.data as Uri)
                bitmap.let {
                    picView.setImageBitmap(bitmap)
                }
            }
        }

        val buttonPhotoPicker: Button = findViewById(R.id.buttonPhotoPicker)
        buttonPhotoPicker.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            val picker = Intent.createChooser(intent, "Pick a photo")
            photoPicker.launch(picker)
        }
    }



    // Activity lifecycle on toast
    override fun onStart() {
        super.onStart()
        showToast(this, "onStart")
    }

    override fun onResume() {
        super.onResume()
        showToast(this, "onResume")
    }

    override fun onPause() {
        super.onPause()
        showToast(this, "onPause")
    }

    override fun onStop() {
        super.onStop()
        showToast(this, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast(this, "onDestroy")
    }

    // Create a menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Set items and actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuOpen -> textView.text = "OPEN"
            R.id.menuSave -> textView.text = "SAVE"
        }
        return true
    }

    // Create a context menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Inflate an item
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    // Create action for menu item
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.contextMenuAction1 -> textView.text = "ACTION1"
            R.id.contextMenuAction2 -> textView.text = "ACTION2"
        }
        return true
    }

    // Save instance state when device rotated
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }


}