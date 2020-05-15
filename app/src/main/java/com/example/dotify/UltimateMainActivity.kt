package com.example.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.songdetail.NowPlayingFragment
import com.example.dotify.songlist.SongListFragment
import com.example.dotify.songlist.OnSongClickListener
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

        title = "All Songs"
        val songListFragment = SongListFragment()

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
                .add(R.id.fragContainer, songListFragment, SongListFragment.TAG)
                .commit()
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            miniPlayer.visibility = View.GONE
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            miniPlayer.visibility = View.VISIBLE
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
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

        btnProfile.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }
    }

    // set the text on the mini-player when a song has being clicked
    override fun onSongClicked(song: Song?) {
        songInfo.text = getString(R.string.song_info).format(song?.title, song?.artist)
        currentSong = song
    }

    // set the on click listener on the mini player
    private fun setMiniPlayer() {
        val song = currentSong
        if (song != null) {
            val nowPlayingFragmentRef = getNowPlayingFragment()
            if (nowPlayingFragmentRef == null) {
                val nowPlayingFragment = NowPlayingFragment.getInstance(song)

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                    .addToBackStack(NowPlayingFragment.TAG)
                    .commit()
                miniPlayer.visibility = View.GONE
            } else {
                nowPlayingFragmentRef.updateSong(song)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(CLICKED_SONG, currentSong)
    }
}
