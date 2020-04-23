package com.example.dotify

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class ActivityB : AppCompatActivity() {
    private var randomNumber = Random.nextInt(1000000, 10000000)

    companion object {
        const val SONG_KEY = "SONG_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
        btnPlay.setOnClickListener {playSong()}
        btnNext.setOnClickListener {skipSong(getString(R.string.skip_next))}
        btnPrevious.setOnClickListener {skipSong(getString(R.string.skip_previous))}
        btnChangeUser.setOnClickListener {changeUser()}
        btnApplyUser.setOnClickListener {applyUser()}
        coverImage.setOnLongClickListener {changeAllTextColor(); true}
    }

    private fun init() {
        val song = intent.getParcelableExtra<Song>(SONG_KEY)
        songName.text = song?.title
        artistName.text = song?.artist
        coverImage.setImageResource(song.largeImageID)
        playCounts.text = getString(R.string.plays_count_format).format(randomNumber)
    }

    private fun changeAllTextColor() {
        changeTextColor(userName)
        changeTextColor(songName)
        changeTextColor(artistName)
        changeTextColor(playCounts)
    }

    private fun changeTextColor(selectedText: TextView) {
        selectedText.setTextColor(getColor(R.color.purple))
    }

    private fun skipSong(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun playSong() {
        playCounts.text = getString(R.string.plays_count_format).format(randomNumber++)
    }

    private fun changeUser() {
        btnChangeUser.visibility = View.GONE
        userName.visibility = View.GONE
        etNameInput.visibility = View.VISIBLE
        btnApplyUser.visibility = View.VISIBLE
    }

    private fun applyUser() {
        if (TextUtils.isEmpty(etNameInput.text)) {
            Toast.makeText(this, getString(R.string.username_alert), Toast.LENGTH_SHORT).show()
        } else {
            userName.text = etNameInput.text
            btnChangeUser.visibility = View.VISIBLE
            userName.visibility = View.VISIBLE
            etNameInput.visibility = View.GONE
            btnApplyUser.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, SongListActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}