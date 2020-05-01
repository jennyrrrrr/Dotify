package com.example.dotify.songlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.R
import com.example.dotify.songdetail.ActivityB
import com.example.dotify.songdetail.ActivityB.Companion.SONG_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListFragment: Fragment() {

    private lateinit var songAdapter : SongListAdapter
    private var allSongs = SongDataProvider.getAllSongs().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongListAdapter(allSongs)
        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song: Song -> setMiniPlayer(song) }

//        songAdapter.onSongClickListener = { song ->
//            val intent = Intent(context, ActivityB ::class.java).apply {
//                putExtra(ActivityB.SONG_KEY, song)
//            }
//            startActivity(intent)
//        }
////        songAdapter.onSongLongClickListener = { song: Song -> removeSong(song) }
//        btnShuffle.setOnClickListener { shuffleSongs() }
    }

//     set the song info on the mini play
//     set on click listener on the mini player
    private fun setMiniPlayer(song: Song?) {
        songInfo.text = getString(R.string.song_info).format(song?.title, song?.artist)

        songInfo.setOnClickListener {
            val intent = Intent(context, ActivityB::class.java)
            intent.putExtra(SONG_KEY, song)
            startActivity(intent)
        }
    }

//    private fun shuffleSongs() {
//        val newSongs = allSongs.toMutableList().apply{ shuffle() }
//        songAdapter.change(newSongs)
//    }

}