package com.example.dotify.songdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.example.dotify.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class ActivityB : AppCompatActivity() {
    private var randomNumber = Random.nextInt(1000000, 10000000)
    private var currentCount = randomNumber

    companion object {
        const val SONG_KEY = "SONG_KEY"
        const val OUT_COUNT = "out_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "ALL SONGS"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                currentCount = getInt(OUT_COUNT)
            }
        }

        init()
        btnPlay.setOnClickListener {playSong()}
        btnNext.setOnClickListener {skipSong(getString(R.string.skip_next))}
        btnPrevious.setOnClickListener {skipSong(getString(R.string.skip_previous))}
//        btnChangeUser.setOnClickListener {changeUser()}
//        btnApplyUser.setOnClickListener {applyUser()}
        coverImage.setOnLongClickListener {changeAllTextColor(); true}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(OUT_COUNT, currentCount)
        super.onSaveInstanceState(outState)
    }

    // load the song info
    private fun init() {
        val song = intent.getParcelableExtra<Song>(SONG_KEY)
        songName.text = song?.title
        artistName.text = song?.artist
        coverImage.setImageResource(song.largeImageID)
        playCounts.text = getString(R.string.plays_count_format).format(randomNumber)
    }

    // change the color of all the texts on the page
    private fun changeAllTextColor() {
//        changeTextColor(userName)
        changeTextColor(songName)
        changeTextColor(artistName)
        changeTextColor(playCounts)
    }

    // change the text color to purple
    private fun changeTextColor(selectedText: TextView) {
        selectedText.setTextColor(getColor(R.color.purple))
    }

    // skip the current song
    private fun skipSong(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // play the current song, increase the number of played time
    private fun playSong() {
        playCounts.text = getString(R.string.plays_count_format).format(randomNumber++)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

//
//    //  close the key board when the focus is not on the text entry box;
//    private fun closeKeyboard() {
//        val view = this.currentFocus
//        if (view != null) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
//        }
//    }

//    // hide change button and user name, show user name entry box and apply button;
//    private fun changeUser() {
//        btnChangeUser.visibility = View.GONE
//        userName.visibility = View.GONE
//        etNameInput.visibility = View.VISIBLE
//        btnApplyUser.visibility = View.VISIBLE
//    }
//
//    // toast an alert when the input name is empty;
//    // change the user name to new input name, hide input box and apply button, show new user name
//    // and change button;
//    private fun applyUser() {
//        if (TextUtils.isEmpty(etNameInput.text)) {
//            Toast.makeText(this, getString(R.string.username_alert), Toast.LENGTH_SHORT).show()
//        } else {
//            userName.text = etNameInput.text
//            closeKeyboard()
//            etNameInput.visibility = View.GONE
//            btnApplyUser.visibility = View.GONE
//            btnChangeUser.visibility = View.VISIBLE
//            userName.visibility = View.VISIBLE
//        }
//    }

//    // set the back button
//    override fun onSupportNavigateUp(): Boolean {
//        val intent = Intent(this, SongListActivity::class.java)
//        startActivity(intent)
//        finish()
//        return true
//    }
}
