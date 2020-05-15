package com.example.dotify

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_info.*

class UserInfoActivity : AppCompatActivity() {
    private lateinit var apiManager: ApiManager
    private lateinit var musicManager: MusicManager
    private var playTimes = 1;

    companion object {
        const val PLAY_COUNT = "PLAY_COUNT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info)

        apiManager = (application as HttpApp).apiManager
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fetchUserWithVolley()

        musicManager = (application as HttpApp).musicManager
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                playTimes = getInt(PLAY_COUNT)
            }
        } else {
            playTimes = musicManager.songPlayCount
        }
        playTime.text = getString(R.string.play_time).format(playTimes)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAY_COUNT, playTimes)
    }

    private fun fetchUserWithVolley() {
        apiManager.fetchUserInfo ({ user ->
            username.text = getString(R.string.user_name).format(user.username)
            userFirstLast.text = getString(R.string.user_first_last).format(user.firstName, user.lastName)
            val imageView = ImageView(this)
            Picasso.get().load(getString(R.string.img_url).format(user.profilePicURL)).into(imageView);
            val layout = findViewById<LinearLayout>(R.id.imgLayout)
            layout?.addView(imageView)
        },{
            Toast.makeText(this, getString(R.string.fetch_error), Toast.LENGTH_SHORT).show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}