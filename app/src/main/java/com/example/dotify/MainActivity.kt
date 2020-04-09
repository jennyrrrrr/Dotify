package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val randomNumber = Random.nextInt(1000000, 10000000)
    private var num = randomNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playCounts = findViewById<TextView>(R.id.playCounts)
        val countsText = "${randomNumber.toString()} plays"
        playCounts.text = countsText
    }

    fun changeClicked(view: View) {
        val etNameInput = findViewById<EditText>(R.id.etNameInput)
        val userName = findViewById<TextView>(R.id.userName)
        val btnApplyUser = findViewById<Button>(R.id.btnApplyUser)
        val btnChangeUser = findViewById<Button>(R.id.btnChangeUser)
        btnChangeUser.visibility = View.GONE
        userName.visibility = View.GONE
        etNameInput.visibility = View.VISIBLE
        btnApplyUser.visibility = View.VISIBLE
    }

    fun applyClicked(view: View) {
        if (TextUtils.isEmpty(etNameInput.text)) {
            Toast.makeText(this, "You did not enter a username!", Toast.LENGTH_SHORT).show()
        } else {
            userName.text = etNameInput.text
            btnChangeUser.visibility = View.VISIBLE
            userName.visibility = View.VISIBLE
            etNameInput.visibility = View.GONE
            btnApplyUser.visibility = View.GONE
        }
    }

    fun playClicked(view: View) {
        val newCounts = "${num++} plays"
        playCounts.text = newCounts
    }

    fun previousClicked(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun nextClicked(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }
}