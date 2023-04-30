package com.example.pomodoroapp

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.pomodoroapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //declare the animation
        val animTvWelcome = AnimationUtils.loadAnimation(this, R.anim.translation_anim_two)
        binding.tvWelcomeApp.startAnimation(animTvWelcome)

        val animAllTextButton = AnimationUtils.loadAnimation(this, R.anim.scale_anim_one)
        binding.linearLayoutAllTextInput.startAnimation(animAllTextButton)

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
