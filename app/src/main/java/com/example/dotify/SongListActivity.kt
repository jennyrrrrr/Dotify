package com.example.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.ActivityB.Companion.SONG_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private var allSongs = SongDataProvider.getAllSongs().toMutableList()
    private val songAdapter = SongListAdapter(allSongs)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        songAdapter.onSongClickListener = { song: Song -> setMiniPlayer(song) }
        songAdapter.onSongLongClickListener = { song: Song -> removeSong(song) }
        btnShuffle.setOnClickListener { shuffleSongs() }
        rvSongs.adapter = songAdapter
    }

    private fun setMiniPlayer(song: Song) {
        songInfo.text = getString(R.string.song_info).format(song.title, song.artist)

        songInfo.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            intent.putExtra(SONG_KEY, song)
            startActivity(intent)
        }
    }

    private fun removeSong(song: Song) {
        val newSongs = allSongs.toMutableList().apply{ allSongs.remove(song) }
        songAdapter.change(newSongs)
        Toast.makeText(this, getString(R.string.delete_song).format(song.title), Toast.LENGTH_SHORT).show()
    }

    private fun shuffleSongs() {
        val newSongs = allSongs.toMutableList().apply{shuffle()}
        songAdapter.change(newSongs)
    }
}
