package com.example.sbotlevskyi.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


//tutorial
//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
class MainActivity : AppCompatActivity() {
    lateinit var linkAdapter: DeepLinkAdapter
//    lateinit var viewModel: LinkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createAdapter()
//        viewModel = ViewModelProviders.of(this).get(LinkViewModel::class.java)
//        viewModel.getAllWords().observe(this, Observer { list ->
//            list?.let { linkAdapter.updateData(it) }
//        })
        send_button.setOnClickListener {
            goOpen()
//            val model = LinkModel(text_uri.text.toString(), text_title.text.toString())
//            viewModel.insert(model)
        }

    }

    private fun createAdapter() {
        linkAdapter = DeepLinkAdapter {
            text_uri.text = Editable.Factory.getInstance().newEditable(it.urlDeepLink)
            text_title.text = Editable.Factory.getInstance().newEditable(it.nameLink)
            goOpen()
        }
        linksRecyclerView.layoutManager = LinearLayoutManager(this)
        linksRecyclerView.adapter = linkAdapter
        linkAdapter.updateData(getlist())
    }


    private fun goOpen() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(text_uri.text.toString()))

        if (browserIntent.resolveActivity(this@MainActivity.packageManager) != null) {
            startActivity(browserIntent)
        } else {
            Toast.makeText(this@MainActivity, "Wrong uri", Toast.LENGTH_LONG).show()
        }
    }

    fun getlist(): List<LinkModel> {
        return listOf(
                LinkModel("crma://whatson/details/restaurant/72e142c9-aee1-47e8-ac7e-2c194b02aa46","buffet"),
                LinkModel("crma://whatson/details/livetheatreschedule/428f86d3-467d-46b0-921d-1bc0f41fde98","livetheatreschedule"),
                LinkModel("crma://whatson/details/detail/e7a4ca1c-f038-4984-a57e-0b7e575e", "bad"),
                LinkModel("crma://search/category/1cbcb8d3-3027-4add-a713-b013e9352cd4", "restaurant"),
                LinkModel("crma://rewards/privilege/MELBRIVERSIDE","privilege"),
                LinkModel("crma://rewards/reward/PARKING","reward"),
                LinkModel("crma://rewards/member/dd993c37-5f27-4528-a125-4ddf5f8f4b37", "member"),
                LinkModel("crma://rewards/reward_sfdsdf/ssdfsdf", "reward bad"),
                LinkModel("crma://whatson/details/venue/b27e31e1-41cf-465d-8c7f-f7a8b9a570cd", "What’s on venue Crown Metropol"),
                LinkModel("crma://whatson/details/offer/49d22fe4-01d0-4fbb-8ca7-9fc48a4d3ae3", "What’s on offer Cocktails and dreams"),
                LinkModel("crma://whatson/details/moviesession/656611", "Movie session Dragon Ball Super melbourne"),
                LinkModel("crma://whatson/details/casinogame/2aecc557-4bda-4f5c-898a-7ae5cd958125", "UAT Gaming African Spirit"),
                LinkModel("crma://whatson/details/casinogame/249b29e2-86bf-4726-ad0c-7e0f1468270e", "Production Gaming"),
                LinkModel("crma://search/category/9620e164-1dfd-427e-9486-3beea5f285cc", "Search category Bars"),
                LinkModel("crma://search/category/c847085a-91e0-42d5-9b73-0594c5f7aa81", "Search category Live Sport"),
                LinkModel("crma://search/categor", "category Live Sport broken")
        )
    }
}
