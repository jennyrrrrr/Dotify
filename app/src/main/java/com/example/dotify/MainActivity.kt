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
        val counts = randomNumber.toString()
        val countsText = "$counts plays"
        playCounts.text = countsText
    }

    fun changeClicked(view: View) {
        val etNameInput = findViewById<EditText>(R.id.etNameInput)
        val userName = findViewById<TextView>(R.id.userName)
        val btnApplyUser = findViewById<Button>(R.id.btnApplyUser)
        val btnChangeUser = findViewById<Button>(R.id.btnChangeUser)

        btnChangeUser.setVisibility(View.GONE)
        userName.setVisibility(View.GONE)
        etNameInput.setVisibility(View.VISIBLE)
        btnApplyUser.setVisibility(View.VISIBLE)
    }

    fun applyClicked(view: View) {
        val userInputtedName = etNameInput.text
        userName.setText(userInputtedName)

        if (TextUtils.isEmpty(userInputtedName)) {
            Toast.makeText(this, "You did not enter a username!", Toast.LENGTH_SHORT).show()
            Log.i("aa", "clicked")
        } else {
            btnChangeUser.setVisibility(View.VISIBLE)
            userName.setVisibility(View.VISIBLE)
            etNameInput.setVisibility(View.GONE)
            btnApplyUser.setVisibility(View.GONE)
        }
    }

    fun playClicked(view: View) {
        num ++;
        val countsText = "$num plays"
        playCounts.text = countsText
    }

    fun previousClicked(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun nextClicked(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }
}