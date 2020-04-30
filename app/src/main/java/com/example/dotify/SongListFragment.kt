package com.example.dotify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListFragment: Fragment() {


    private lateinit var songAdapter: SongListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongListAdapter(allSongs)
        songAdapter.onSongClickListener = { song: Song -> setMiniPlayer(song) }
        songAdapter.onSongLongClickListener = { song: Song -> removeSong(song) }
        btnShuffle.setOnClickListener { shuffleSongs() }
        rvSongs.adapter = songAdapter



    }
    }
}