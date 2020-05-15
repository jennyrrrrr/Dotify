package com.example.dotify

import android.content.Context
import android.util.Log

class MusicManager(context: Context) {
    var songPlayCount = 0;

    fun play() {
        songPlayCount ++
    }

//    fun getCurrentSong(): Song {}
//    fun nextSong() {}
//    fun prevSong() {}
//    fun pause() {}
//    fun isPlaying(): Boolean {}
}