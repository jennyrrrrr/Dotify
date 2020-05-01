package com.example.dotify.songlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.songdetail.ActivityB
import com.example.dotify.songdetail.ActivityB.Companion.SONG_KEY
import com.example.dotify.R
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {
    private var allSongs = SongDataProvider.getAllSongs().toMutableList()
    private val songAdapter = SongListAdapter(allSongs)

    private var currentSong: Song? = null

    companion object {
        const val OUT_SONG = "out_song"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                currentSong = getParcelable<Song?>(OUT_SONG)
                setMiniPlayer(currentSong)
            }
        }

        songAdapter.onSongClickListener = { song: Song -> setMiniPlayer(song) }
        songAdapter.onSongLongClickListener = { song: Song -> removeSong(song) }
        btnShuffle.setOnClickListener { shuffleSongs() }
        rvSongs.adapter = songAdapter
    }

    // set the song info on the mini play
    // set on click listener on the mini player
    private fun setMiniPlayer(song: Song?) {
        songInfo.text = getString(R.string.song_info).format(song?.title, song?.artist)

        songInfo.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            intent.putExtra(SONG_KEY, song)
            startActivity(intent)
        }
    }

    // remove the song on the list when ling clicked on the song
    private fun removeSong(song: Song) {
        val newSongs = allSongs.toMutableList().apply{ allSongs.remove(song) }
        songAdapter.change(newSongs)
        Toast.makeText(this, getString(R.string.delete_song).format(song.title), Toast.LENGTH_SHORT).show()
    }

    // shuffle the order of the songs in the list
    private fun shuffleSongs() {
        val newSongs = allSongs.toMutableList().apply{ shuffle() }
        songAdapter.change(newSongs)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(OUT_SONG, currentSong)
        super.onSaveInstanceState(outState)
    }
}
