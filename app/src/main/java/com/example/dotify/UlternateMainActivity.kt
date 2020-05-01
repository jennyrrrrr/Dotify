package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.songdetail.NowPlayingFragment
import com.example.dotify.songlist.SongListFragment

class UlternateMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulternate_main)

//        val songDetailFragment = NowPlayingFragment()
        val songListFragment = SongListFragment()

//        val randomSong: Song = SongDataProvider.createRandomSong()
//
//        val argumentBundle = Bundle().apply { putParcelable(NowPlayingFragment.ARG_SONG, randomSong) }
//
//        songDetailFragment.arguments = argumentBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, songListFragment)
            .commit()
    }

}
