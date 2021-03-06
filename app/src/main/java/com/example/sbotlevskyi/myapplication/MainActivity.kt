package com.example.sbotlevskyi.myapplication

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.ViewModelProviders
import android.text.Editable


//tutorial
//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
class MainActivity : AppCompatActivity() {
    lateinit var linkAdapter: DeepLinkAdapter
    lateinit var viewModel: LinkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createAdapter()
        viewModel = ViewModelProviders.of(this).get(LinkViewModel::class.java)
        viewModel.getAllWords().observe(this, Observer { list ->
            list?.let { linkAdapter.updateData(it) }
        })
        send_button.setOnClickListener{
            goOpen()
            val model = LinkModel(text_uri.text.toString(), text_title.text.toString())
            viewModel.insert(model)
        }

    }

    private fun createAdapter() {
        linkAdapter = DeepLinkAdapter {
            text_uri.text = Editable.Factory.getInstance().newEditable(it.urlDeepLink)
        }
        linksRecyclerView.layoutManager = LinearLayoutManager(this)
        linksRecyclerView.adapter = linkAdapter
    }


    private fun goOpen() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(text_uri.text.toString()))

        if (browserIntent.resolveActivity(this@MainActivity.packageManager) != null) {
            startActivity(browserIntent)
        } else {
            Toast.makeText(this@MainActivity, "Wrong uri", Toast.LENGTH_LONG).show()
        }
    }

}
