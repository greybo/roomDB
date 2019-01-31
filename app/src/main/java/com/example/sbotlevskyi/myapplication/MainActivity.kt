package com.example.sbotlevskyi.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
//tutorial
//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
class MainActivity : AppCompatActivity() {
    lateinit var linkAdapter: DeepLinkAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createAdapter() {
        linkAdapter = DeepLinkAdapter {

        }
        linksRecyclerView.layoutManager = LinearLayoutManager(this)
        linksRecyclerView.adapter = linkAdapter
        linkAdapter.updateData()
    }

    fun onClickOpen(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(text_uri.text.toString()))

        if (browserIntent.resolveActivity(this@MainActivity.packageManager) != null) {
            startActivity(browserIntent)
        } else {
            Toast.makeText(this@MainActivity, "Wrong uri", Toast.LENGTH_LONG).show()
        }
    }

}
