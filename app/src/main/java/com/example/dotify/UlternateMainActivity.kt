package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class UlternateMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ulternate_main)

        val listSongFragment = SongListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragContainer, listSongFragment)
            .commit()
    }

}
