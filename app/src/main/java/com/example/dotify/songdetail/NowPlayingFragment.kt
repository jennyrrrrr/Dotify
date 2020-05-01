package com.example.dotify.songdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ericchee.songdataprovider.Song
import com.example.dotify.R
import kotlinx.android.synthetic.main.fragment_now_playing.*

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    private var song : Song? = null

    companion object {
        const val ARG_SONG = "arg_song"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            val song = args.getParcelable<Song>(ARG_SONG)

            if(song != null) {
                this.song = song
            }
        }
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

        song?.let {
            songName.text = it.title
            artistName.text = it.artist
            coverImage.setImageResource(it.largeImageID)
        }
    }

}
