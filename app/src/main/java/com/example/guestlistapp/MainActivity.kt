package com.example.guestlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


const val LAST_GUEST_NAME_KEY = "lat-guest-name-bundle-key"


class MainActivity : AppCompatActivity() {


    private lateinit var addGuestButton: Button
    private lateinit var newGuestEditText: EditText
    private lateinit var guestList: TextView
    private lateinit var lastGuestAdded: TextView

    val guestNames = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addGuestButton = findViewById(R.id.addGuestButton)
        newGuestEditText = findViewById(R.id.newGuestInput)
        guestList = findViewById(R.id.listOfGuests)
        lastGuestAdded = findViewById(R.id.lastGuestAdded)

        addGuestButton.setOnClickListener {
            addnewGuestName()
        }

        val savedLastGuestMessage = savedInstanceState?.getString(LAST_GUEST_NAME_KEY)
        lastGuestAdded.text = savedLastGuestMessage
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_GUEST_NAME_KEY, lastGuestAdded.text.toString())
    }
    private fun addnewGuestName() {
        val newGuestName = newGuestEditText.text.toString()
        if (newGuestName.isNotBlank()) {
            guestNames.add(newGuestName)
            updateguestList()
            newGuestEditText.text.clear()
            lastGuestAdded.text = getString(R.string.last_guest_message, newGuestName)

        }

    }
        private fun updateguestList() {
            val guestDisplay = guestNames.sorted().joinToString (separator = "\n" )
            guestList.text = guestDisplay


        }



}