package com.example.pomodoroapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.pomodoroapp.databinding.ActivityTimeSettingsBinding


class SettingsTimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeSettingsBinding

    private var studyMinute: Int? = null
    private var breakMinute: Int? = null
    private var roundCount: Int? = null

    private var restTimer: CountDownTimer? = null
    private var studyTimer: CountDownTimer? = null
    private var breakTimer: CountDownTimer? = null

    private var manuelRound = 1

    private var isStudy = true

    private var isStop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive Extras
        studyMinute = intent.getIntExtra("study", 0) * 60 * 1000
        breakMinute = intent.getIntExtra("break", 0) * 60 * 1000
        roundCount = intent.getIntExtra("round", 0)

        // Set Rounds Text
        val roundText = StringBuilder()
        roundText.append(manuelRound)
        roundText.append("/")
        roundText.append(roundCount)

        //Start Timer
        setRestTimer()

        // Reset Button
        binding.ivStop.setOnClickListener {
            resetOrStart()
        }
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
                val intent = Intent(this, SettingsTimeActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Convert Received Numbers to Minutes and Seconds, Calculate Current Timer
    private fun updateTimer(time : Int): String {
        var timeLabel = ""
        val minutes = time / 60
        val seconds = time % 60

        if (minutes < 10) timeLabel += "0"
        timeLabel += "$minutes:"

        if (seconds < 10) timeLabel += "0"
        timeLabel += seconds

        return timeLabel
    }

    // Set Rest Timer
    private fun setRestTimer(){

        val roundCountText = String.format("%s / %s", manuelRound, roundCount)

        binding.tvRound.text = roundCountText
        binding.tvStatus.setText(R.string.get_ready)
        binding.progressBar.progress = 0
        binding.progressBar.max = 2

        restTimer = object : CountDownTimer(10500,1000) {
            override fun onTick(p0: Long) {
                binding.progressBar.progress = (p0 / 1000).toInt()
                binding.tvTimer.text = (p0 / 1000).toString()
            }
            override fun onFinish() {

                if (isStudy){
                    setupStudyView()
                }else{
                    setupBreakView()
                }
            }
        }.start()
    }

    // Set Study Timer
    private fun setStudyTimer(){

        studyTimer = object : CountDownTimer(studyMinute!!.toLong() + 500,1000) {
            override fun onTick(p0: Long) {
                binding.progressBar.progress = (p0 /1000).toInt()
                binding.tvTimer.text = updateTimer((p0 / 1000).toInt())
            }
            override fun onFinish() {
                if(manuelRound < roundCount!!){
                    isStudy = false
                    setRestTimer()
                    manuelRound++
                }else{
                    clearAttribute()
                    binding.tvStatus.setText(R.string.set_finish_rounds)
                }
            }
        }.start()
    }

    // Set Break Timer
    private fun setBreakTimer() {
        breakTimer = object : CountDownTimer(breakMinute!!.toLong()+500, 1000 ) {
            override fun onTick(p0: Long) {
                binding.progressBar.progress = (p0 / 1000).toInt()
                binding.tvTimer.text = updateTimer((p0 / 1000).toInt())
            }

            override fun onFinish() {
                isStudy = true
                setRestTimer()
            }

        }.start()
    }

    // Prepare Screen for Study Timer
    private fun setupStudyView() {

        val roundCountText = String.format("%s / %s", manuelRound, roundCount)

        binding.tvRound.text = roundCountText
        binding.tvStatus.setText(R.string.study_time)
        binding.progressBar.max = studyMinute!!/1000

        if (studyTimer != null)
            studyTimer = null

        setStudyTimer()
    }

    // Prepare Screen for Break Timer
    private fun setupBreakView() {

        binding.tvStatus.setText(R.string.break_time)
        binding.progressBar.max = breakMinute!!/1000

        if (breakTimer != null)
            breakTimer = null

        setBreakTimer()
    }

    // Rest Whole Attributes in FeedActivity
    private fun clearAttribute() {

        binding.tvStatus.setText(R.string.press_button)
        binding.ivStop.setImageResource(R.drawable.ic_play)
        binding.progressBar.progress = 0
        binding.tvTimer.text = "0"
        manuelRound = 1

        val roundCountText = String.format("%s / %s", manuelRound, roundCount)

        binding.tvRound.text = roundCountText

        restTimer?.cancel()
        studyTimer?.cancel()
        breakTimer?.cancel()

        isStop = true
    }

    // For Reset or Restart Pomodoro
    private fun resetOrStart() {
        if (isStop){
            binding.ivStop.setImageResource(R.drawable.ic_stop)
            setRestTimer()
            isStop = false
        }else
            clearAttribute()

    }
    // Clear Everything When App Destroyed
    override fun onDestroy() {
        super.onDestroy()
        restTimer?.cancel()
        studyTimer?.cancel()
        breakTimer?.cancel()

    }


}
