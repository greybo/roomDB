package com.example.sbotlevskyi.myapplication

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


//tutorial
//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
class MainActivity : AppCompatActivity() {
    lateinit var linkAdapter: DeepLinkAdapter
    //    lateinit var viewModel: LinkViewModel
    var deepLinkText = ""
    var titleText = ""

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
            //            text_uri.text = Editable.Factory.getInstance().newEditable(it.urlDeepLink)
            text_title.text = Editable.Factory.getInstance().newEditable(it.nameLink)
//            goOpen()
            deepLinkText = it.urlDeepLink
            titleText=it.nameLink
            createNotification()
        }
        linksRecyclerView.layoutManager = LinearLayoutManager(this)
        linksRecyclerView.adapter = linkAdapter
        linkAdapter.updateData(getlist())
    }


    private fun goOpen(): Intent {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkText))

        if (browserIntent.resolveActivity(this@MainActivity.packageManager) != null) {
            startActivity(browserIntent)
        } else {
            Toast.makeText(this@MainActivity, "Wrong uri", Toast.LENGTH_LONG).show()
        }
        return browserIntent
    }

    fun getlist(): List<LinkModel> {
        return listOf(
                LinkModel("crma://whatson/details/restaurant/72e142c9-aee1-47e8-ac7e-2c194b02aa46", "buffet"),
                LinkModel("crma://whatson/details/livetheatreschedule/428f86d3-467d-46b0-921d-1bc0f41fde98", "livetheatreschedule"),
                LinkModel("crma://whatson/details/detail/e7a4ca1c-f038-4984-a57e-0b7e575e", "detail bad"),
                LinkModel("crma://search/category/1cbcb8d3-3027-4add-a713-b013e9352cd4", "search restaurant"),
                LinkModel("crma://search/category/1cbcb8d3-3027-4add-a713", "search bad"),
                LinkModel("crma://rewards/privilege/MELBRIVERSIDE", "privilege"),
                LinkModel("crma://rewards/reward/PARKING", "reward"),
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

    companion object {

        const val CHANNEL_ID = "samples.notification.devdeeds.com.CHANNEL_ID"
        const val CHANNEL_NAME = "Sample Notification"
    }

    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000


    @SuppressLint("NewApi")
    private fun createChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library

            val context = this.applicationContext
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(deepLinkText, titleText, importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.parseColor("#e8334a")
            notificationChannel.description = getString(R.string.abc_action_bar_home_description)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    fun createNotification() {
        createChannel()

        val context = this.applicationContext
        val notifyIntent = Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkText))

        val title = titleText
        val message =deepLinkText

        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent = PendingIntent.getActivity(context,
                0,
                notifyIntent,
                PendingIntent.FLAG_ONE_SHOT)

        val res = this.resources
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            mNotification = Notification.Builder(this, CHANNEL_ID)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.abc_ic_star_black_16dp)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setContentText(message).build()
        } else {

            mNotification = Notification.Builder(this)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.abc_ic_star_half_black_36dp)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(text_title.text)
                    .setStyle(Notification.BigTextStyle()
                            .bigText(message))
                    .setSound(uri)
                    .setContentText(message).build()

        }

       val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // mNotificationId is a unique int for each notification that you must define
        notificationManager.notify(mNotificationId, mNotification)
    }
}
