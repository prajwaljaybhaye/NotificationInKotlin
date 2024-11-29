package com.codewithandro.shoppingappapi

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.channels.Channel

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //5 -> create pandding intent -> the notification click the open intent
        val openActIntent = Intent(this,massageActivity::class.java)
        //pending intent
        val pandIntent = PendingIntent.getActivity(this,0,openActIntent,PendingIntent.FLAG_MUTABLE)

        //PendingIntent pass the .setContentIntent(PendingIntent)


        //1-> step create function -> createNotificationChannel
        createNotificationChannel() //calling

        //2-> create notification -> NotificationCompat() pass the context,ChannelId
        val notification = NotificationCompat.Builder(this, "channelId")
            //setThe Notification details
            .setContentTitle("Day Of 27")
            .setContentText("Alert , Join the Class Lecture are Starting in the 28min later fast join the platform " +
                    "Alert , Join the Class Lecture are Starting in the 28min later fast join the platform ")
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            //set the Intent the click the notification action perform
            .setContentIntent(pandIntent)//pass the panding intent
            .build()

        //3-> create notificationManagerCompat
        val notificationManager = NotificationManagerCompat.from(this)

        //featch button xml
        val notifyBtn = findViewById<Button>(R.id.notifyBtn)
        //4 -> button click then fire Notification
        notifyBtn.setOnClickListener{
            notificationManager.notify(0,notification)
            /*
            //optinol
            //check the permission
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                //show the toast and dilog box
            }else{
                //notificationManager.notify(0,notification)
            }
            */
        }


    }




    //createNotificationChannel -> Oreo version > permission take
    private fun createNotificationChannel(){
        //define var 1-> channelId 2-> channelName
        val CHANNEL_ID = "channelId"
        val CHANNEL_NAME = "channelName"

        //check the mobile version > Oreo-O
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //create channel
            val channel = NotificationChannel(CHANNEL_ID , CHANNEL_NAME ,NotificationManager.IMPORTANCE_DEFAULT).apply {
                //set discription and swith btn color
                description ="This IS My Channel "
                //setColor
                lightColor = Color.BLUE
                enableLights(true)
            }

            //create manager the add the channel -> type changed as NotificationManager
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)//pass the channel
        }
    }
    //complete the function -> createNotificationChannel
}