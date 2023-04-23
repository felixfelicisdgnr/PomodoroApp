package com.example.pomodoroapp

import android.os.CountDownTimer

object TimeDatas {

     var defaultTime : Long = 1500 * 1000

     var nowTime : Long = defaultTime
     var isStart : Int = 0

     var defaultWork:Long = 1500 * 1000
     var defaultShort:Long = 300 * 1000
     var defaultLong:Long = 900 * 1000


     var timeSelected : Int = 0
     var timeCountDown : CountDownTimer? = null
     var timeProgress = 0
     var pauseOffSet : Long = 0



}