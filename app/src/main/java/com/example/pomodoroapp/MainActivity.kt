package com.example.pomodoroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.pomodoroapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val settingsInflater = menuInflater
        settingsInflater.inflate(R.menu.settings_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }


}