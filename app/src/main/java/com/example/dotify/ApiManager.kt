package com.example.dotify

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

//    fun fetchSongs() {}
//    fun fetchArtists() {}
    fun fetchUserInfo(onUserReady: (User) -> Unit, onError: (() -> Unit)? = null) {
        val userUrl = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json"

        val stringRequest = StringRequest (
            Request.Method.GET, userUrl,
            { response ->
                val gson = Gson()
                val user = gson.fromJson(response, User::class.java)

                onUserReady(user)
            },
            {
                onError?.invoke()
            })

        queue.add(stringRequest)
    }

}