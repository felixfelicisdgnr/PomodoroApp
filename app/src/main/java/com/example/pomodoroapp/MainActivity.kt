package com.example.pomodoroapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.pomodoroapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //declare the animation
        val animTvWelcome = AnimationUtils.loadAnimation(this, R.anim.translation_anim_1000)
        binding.tvWelcomeApp.startAnimation(animTvWelcome)

        val animAllTextButton = AnimationUtils.loadAnimation(this, R.anim.scale_anim_one)
        binding.linearLayoutAllTextInput.startAnimation(animAllTextButton)

        val animStartBttn = AnimationUtils.loadAnimation(this,R.anim.translation_anim_1000)
        binding.btnStart.startAnimation(animStartBttn)

        val animWhatPomodoroBttn = AnimationUtils.loadAnimation(this,R.anim.translation_anim_1200)
        binding.btnWhatIsPomodoro.startAnimation(animWhatPomodoroBttn)

        val animGoGithubBttn = AnimationUtils.loadAnimation(this, R.anim.translation_anim_1400)
        binding.btnGoGithub.startAnimation(animGoGithubBttn)

        binding.btnGoGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/felixfelicisdgnr/PomodoroApp")
            startActivity(intent)
        }

        binding.btnWhatIsPomodoro.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://tr.wikipedia.org/wiki/Pomodoro_Tekniği")
            startActivity(intent)
        }

        binding.btnStart.setOnClickListener {
            val studyTime = binding.etStudyTime.text.toString()
            val breakTime = binding.etBreakTime.text.toString()
            val roundCount = binding.etStartRoundTime.text.toString()

            if (studyTime.isNotEmpty() && breakTime.isNotEmpty() && roundCount.isNotEmpty()){

                val intent = Intent(this, SettingsTimeActivity::class.java)

                intent.putExtra("study",studyTime.toInt())
                intent.putExtra("break",breakTime.toInt())
                intent.putExtra("round",roundCount.toInt())

                startActivity(intent)

            }else{
                Toast.makeText(this,"Fill fields above",Toast.LENGTH_SHORT).show()
            }

        }
    }



}
