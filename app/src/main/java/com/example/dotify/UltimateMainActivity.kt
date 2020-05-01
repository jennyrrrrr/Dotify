package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.songdetail.NowPlayingFragment
import com.example.dotify.songlist.OnSongClickListener
import com.example.dotify.songlist.SongListFragment
import kotlinx.android.synthetic.main.activity_ultimate_main.*

// class UltimateMainActivity subclass AppCompatActivity implement OnSongClickListener
class UltimateMainActivity : AppCompatActivity(), OnSongClickListener {
    private var currentSong: Song? = null
    private val allSong = SongDataProvider.getAllSongs() // get the single source of truth for list of songs

    companion object {
        const val CLICKED_SONG = "clicked_song" // get the previous clicked song
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultimate_main)

        val songListFragment = SongListFragment()

        // reload the clicked song on mini-player
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                currentSong = getParcelable(CLICKED_SONG)
                songInfo.text = getString(R.string.song_info).format(currentSong?.title, currentSong?.artist)
            }
        } else {
            val songListBundle = Bundle().apply {
                putParcelableArrayList(SongListFragment.ARG_SONG, allSong as ArrayList<Song>)
            }
            songListFragment.arguments = songListBundle
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, songListFragment)
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (!hasBackStack) {
               supportActionBar?.setDisplayHomeAsUpEnabled(true)
               miniPlayer.visibility = View.GONE
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                miniPlayer.visibility = View.VISIBLE
            }
        }

        btnShuffle.setOnClickListener {
            getSongListFragment()?.shuffleSongs()
        }

        songInfo.setOnClickListener {
            setMiniPlayer()
        }
    }

    // set the text on the mini-player when a song has being clicked
    override fun onSongClicked (song: Song?) {
        songInfo.text = getString(R.string.song_info).format(song?.title, song?.artist)
        currentSong = song
    }

    //     set the on click listener on the mini player
    private fun setMiniPlayer() {
        val song = currentSong
        if (song != null) {
            val songDetailFragment = NowPlayingFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(NowPlayingFragment.SONG_KEY, song)
            }
            songDetailFragment.arguments = argumentBundle
            val nowPlayingFragment = getNowPlayingFragment()
            if (nowPlayingFragment == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, songDetailFragment)
                    .addToBackStack(NowPlayingFragment.TAG)
                    .commit()
            } else {
                nowPlayingFragment.updateSong(song)
            }

        }
    }

    private fun getSongListFragment() =
        supportFragmentManager.findFragmentById(R.id.fragContainer) as? SongListFragment

    private fun getNowPlayingFragment() =
        supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

}
