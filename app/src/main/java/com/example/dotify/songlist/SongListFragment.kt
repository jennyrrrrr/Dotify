package com.example.dotify.songlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import com.example.dotify.R
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListFragment: Fragment() {
    private lateinit var songAdapter : SongListAdapter
    private var onSongClickListener: OnSongClickListener? = null

    private lateinit var songs: MutableList<Song>

    companion object {
        val TAG: String = SongListFragment::class.java.simpleName
        const val SHUFFLED_SONG = "shuffled_song"  // get the previously clicked song
        const val ARG_SONG = "arg_song"   // get the song list
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // check if the OnSongClickListener is there when created
        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) { // reload the songs
            with(savedInstanceState) {
                songs = getParcelableArrayList<Song>(SHUFFLED_SONG) as MutableList<Song>
            }
        } else { // get song list from activity
            arguments?.let { args ->
                songs = args.getParcelableArrayList<Song>(ARG_SONG) as MutableList<Song>
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(SHUFFLED_SONG, songs as ArrayList<Song>)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongListAdapter(songs)
        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song: Song ->
            onSongClickListener?.onSongClicked(song)
        }

        // long click on the song to remove it
//      songAdapter.onSongLongClickListener = { song: Song -> removeSong(song) }
    }

    // public method shuffleList() that will shuffle the list & update the adapter
     fun shuffleSongs() {
        val newSongs = songs.toMutableList().apply{ shuffle() }
        songAdapter.change(newSongs)
    }

}

interface OnSongClickListener {
    fun onSongClicked (song: Song?)
}
