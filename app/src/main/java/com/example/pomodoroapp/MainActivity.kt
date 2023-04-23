package com.example.pomodoroapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.pomodoroapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings_item_time -> {
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_LONG).show()
                // Start the SettingsActivity
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun startingTimer(timing : Long) {
        timer = object : CountDownTimer(timing, 1000) {
            override fun onTick(p0: Long) {
                TimeDatas.nowTime = (p0/1000)*1000
            }

            override fun onFinish() {

            }

        }
    }

    private fun updateTimer(seconds : Long){
        var second = seconds/1000
        var minutes = second/60

        second %= 60

        binding.tvPeriodTime
    }

}