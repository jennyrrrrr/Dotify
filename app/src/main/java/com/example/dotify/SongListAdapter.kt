package com.example.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongListAdapter(allSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    private var allSongs: List<Song> = allSongs.toList()

    var onSongClickListener: ((song: Song) -> Unit)? = null
    var onSongLongClickListener: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount() = allSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = allSongs[position]
        holder.bind(song, position)
    }

    fun change (newSongs: List<Song>){
        val callBack = SongDiffCallBack(allSongs, newSongs)
        val diffResult = DiffUtil.calculateDiff(callBack)
        diffResult.dispatchUpdatesTo(this)

        allSongs = newSongs
    }

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val songName =  itemView.findViewById<TextView>(R.id.rvSongName)
        private val artistName =  itemView.findViewById<TextView>(R.id.rvArtistName)
        private val smSongImage =  itemView.findViewById<ImageView>(R.id.rvSongImage)

        fun bind(song: Song, position: Int) {
            songName.text = song.title
            artistName.text = song.artist
            smSongImage.setImageResource(song.smallImageID)

            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }

            itemView.setOnLongClickListener {
                onSongLongClickListener?.invoke(song)
                true
            }
        }
    }
}