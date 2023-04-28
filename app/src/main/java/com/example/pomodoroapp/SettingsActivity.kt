package com.example.pomodoroapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pomodoroapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.etStudyTime.setText((TimeDatas.studyTime).toString())
        //binding.etBreakTime.setText((TimeDatas.breakTime).toString())
        //binding.etStartRoundTime.setText((TimeDatas.roundCount).toString())

        binding.btnGoGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/felixfelicisdgnr/PomodoroApp")
            startActivity(intent)
        }

        binding.btnGoHomepage.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnStart.setOnClickListener {
            if (binding.etStudyTime.text!!.isNotEmpty() && binding.etBreakTime.text!!.isNotEmpty() && binding.etStartRoundTime.text!!.isNotEmpty()){
                TimeDatas.studyTime = binding.etStudyTime.text.toString().toInt()
                TimeDatas.breakTime = binding.etBreakTime.text.toString().toInt()
                TimeDatas.roundCount = binding.etStartRoundTime.text.toString().toInt()

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Fill fields above", Toast.LENGTH_SHORT).show()
            }
        }

    }

}