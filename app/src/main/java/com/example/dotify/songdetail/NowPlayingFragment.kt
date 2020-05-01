package com.example.dotify.songdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.example.dotify.R
import kotlinx.android.synthetic.main.activity_ultimate_main.*
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {
    private var randomNumber = Random.nextInt(1000000, 10000000)
    private var currentCount = randomNumber

    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val SONG_KEY = "song_key"
        const val OUT_COUNT = "out_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                currentCount = getInt(OUT_COUNT)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(OUT_COUNT, currentCount)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val song = args.getParcelable<Song>(SONG_KEY)
            if (song != null) {
                songName.text = song.title
                artistName.text = song.artist
                coverImage.setImageResource(song.largeImageID)
                playCounts.text = getString(R.string.plays_count_format).format(currentCount)
            }
        }

        btnNext.setOnClickListener {skipSong(getString(R.string.skip_next))}
        btnPrevious.setOnClickListener {skipSong(getString(R.string.skip_previous))}
        btnPlay.setOnClickListener {
            btnPlay.setOnClickListener {playSong()}
        }
    }

    private fun skipSong(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun playSong() {
        currentCount++
        playCounts.text = getString(R.string.plays_count_format).format(currentCount)
    }

    // public method takes in a Song object as a param and update the media player views to reflect
    // the song’s info (title, image, etc)
    fun updateSong(song: Song) {
        songName.text = song.title
        artistName.text = song.artist
        coverImage.setImageResource(song.largeImageID)
    }

}
