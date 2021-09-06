package com.pexsistols.moviereviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timer().schedule(timerTask {
            changeScreen()
        }, 1500)
    }


    private fun changeScreen(){
        val intent = Intent(this, HomePageActivity::class.java)
        startActivity(intent)
        finish()
    }

}