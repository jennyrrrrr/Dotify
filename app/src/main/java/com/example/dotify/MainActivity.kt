package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val randomNumber = Random.nextInt(1000000, 10000000)
    private var num = randomNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counts = "$randomNumber plays"
        playCounts.text = counts
        coverImage.setOnLongClickListener{
            userName.apply {
                setTextColor(getColor(R.color.purple))
            }
            songName.apply {
                setTextColor(getColor(R.color.purple))
            }
            artistName.apply {
                setTextColor(getColor(R.color.purple))
            }
            playCounts.apply {
                setTextColor(getColor(R.color.purple))
            }
            true
        }
    }

    fun changeClicked(view: View) {
        btnChangeUser.visibility = View.GONE
        userName.visibility = View.GONE
        etNameInput.visibility = View.VISIBLE
        btnApplyUser.visibility = View.VISIBLE
    }

    fun applyClicked(view: View) {
        if (TextUtils.isEmpty(etNameInput.text)) {
            Toast.makeText(this, "Username can not be empty!", Toast.LENGTH_SHORT).show()
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