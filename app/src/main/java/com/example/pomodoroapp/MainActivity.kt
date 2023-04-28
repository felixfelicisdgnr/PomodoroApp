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

    private var restTimer: CountDownTimer? = null
    private var studyTimer: CountDownTimer? = null
    private var breakTimer: CountDownTimer? = null

    private var isStudy = true

    private var isStop = false

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        updateTimer((TimeDatas.studyTime)!!.toLong())
        updateTimer((TimeDatas.breakTime)!!.toLong())
        updateTimer((TimeDatas.roundCount)!!.toLong())

        // Set Rounds Text
        val roundText = StringBuilder()
        roundText.append(TimeDatas.mRound)
        roundText.append("/")
        roundText.append(TimeDatas.roundCount)

        binding.tvRound.text = roundText.toString()
        //Start Timer
        setRestTimer()

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

    // Calculate Current Timer
    private fun updateTimer(seconds : Long) : String {
        var second = seconds/1000
        val minutes = second/60
        second %= 60

        val formattedTime = "%02d:%02d".format(minutes, seconds)

        binding.tvTimer.text = formattedTime
        return
    }

    // Set Rest Timer
    private fun setRestTimer(){
        binding.tvStatus.text = "Get Ready"
        binding.progressBar.progress = 0
        binding.progressBar.max = 10

        restTimer = object : CountDownTimer(10500,1000){
            override fun onTick(p0: Long) {
                binding.progressBar.progress = (p0 / 1000).toInt()
                binding.tvTimer.text = (p0 / 1000).toString()
            }

            override fun onFinish() {
                if (isStudy){

                } else {

                }
            }

        }.start()
    }

    // Set Study Timer
    private fun setStudyTimer(){

        studyTimer = object : CountDownTimer(TimeDatas.studyTime!!.toLong()+500,1000){
            override fun onTick(p0: Long) {
                binding.progressBar.progress = (p0 /1000).toInt()
                //binding.tvTimer.text = updateTimer(p0 / 1000).toInt())
            }

            override fun onFinish() {

            }

        }
    }

    // Set Break Timer

    // Prepare Screen for Study Timer

    // Rest Whole Attributes in MainActivity

    // Convert Received Numbers to Minutes and Seconds

    // For Reset or Restart Pomodoro

    // Clear Everything When App Destroyed
    override fun onDestroy() {
        super.onDestroy()
        restTimer?.cancel()
        studyTimer?.cancel()
        breakTimer?.cancel()

    }



}