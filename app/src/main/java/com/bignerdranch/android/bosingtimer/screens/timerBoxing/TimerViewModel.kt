package com.bignerdranch.android.bosingtimer.screens.timerBoxing

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {

    //val COUNTDOWN_TIMER = 18000L
    val ONE_SECOND = 1000L
    val DONE = 0L
    var roundState = false
    var millisUntilLeft = 0L
    var ROUND_COUNT:Int = 0
    var COUNTDOWN_TIMER_ROUND = 0L
    var COUNTDOWN_TIMER_REST = 0L
    var timerState ="STOPPED"

    lateinit var timer:CountDownTimer

    val currentTime = MutableLiveData<Long>()
    val roundTime = MutableLiveData<Long>()
    val restTime = MutableLiveData<Long>()
    val durationTime = MutableLiveData<Long>()
    val remainedDurationTime = MutableLiveData<Long>()
    val durationTimerMax = MutableLiveData<Long>()

    init {
        timerState = "STOPPED"
        roundTime.value = 0L
        restTime.value = 0L
    }



    private fun startTimer(roundTimer:Long, round:Int){
        timer = object:CountDownTimer(roundTimer,100L) {
            override fun onTick(millisUntilFinished: Long) {
                millisUntilLeft = millisUntilFinished
                currentTime.value = millisUntilFinished/ONE_SECOND
                durationTime.value = millisUntilFinished/100L
                if (roundState)
                    roundTime.value = (roundTime.value!! + ONE_SECOND)
                else
                    restTime.value = (restTime.value!! + ONE_SECOND)
            }

            override fun onFinish() {
                currentTime.value = DONE
                if (roundState){
                    roundState = false
                   // durationTime.value = COUNTDOWN_TIMER_REST
                    if(round != -1){
                        startTimer(COUNTDOWN_TIMER_REST,round-1)
                        durationTimerMax.value = COUNTDOWN_TIMER_REST/100L
                    }
                } else {
                    roundState = true
                    //durationTime.value = COUNTDOWN_TIMER_ROUND
                    if(round != -1){
                        startTimer(COUNTDOWN_TIMER_ROUND,round-1)
                        durationTimerMax.value = COUNTDOWN_TIMER_ROUND/100L
                    }
                }

            }
        }
        timer.start()
    }

    private fun pauseTimer(){
        timer.cancel()
        remainedDurationTime.value = millisUntilLeft
        //durationTimerMax.value = millisUntilLeft/100L
        //durationTime.value = 50
    }

    private fun resumeTimer() {
        remainedDurationTime.value = millisUntilLeft
        //durationTimerMax.value = millisUntilLeft/100L
        durationTime.value = millisUntilLeft/100L
        startTimer(millisUntilLeft,ROUND_COUNT)

    }

    private fun stopTimer(){
        //remainedDurationTime.value = millisUntilLeft
    }

    fun clickFiteButton(roundTime:Long, restTime:Long, countRound:Int){

        COUNTDOWN_TIMER_ROUND = roundTime
        COUNTDOWN_TIMER_REST = restTime
        ROUND_COUNT = countRound


        roundState = true
        durationTime.value = roundTime
        durationTimerMax.value = roundTime/100L
        startTimer(roundTime,countRound)
    }

    fun clickStopButton(){
        pauseTimer()
        stopTimer()
    }

    fun clickResumeTimer(){
        resumeTimer()
    }


}