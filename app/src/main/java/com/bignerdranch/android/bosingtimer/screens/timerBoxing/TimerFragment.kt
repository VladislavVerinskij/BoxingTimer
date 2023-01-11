package com.bignerdranch.android.bosingtimer.screens.timerBoxing

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.bosingtimer.R
import com.bignerdranch.android.bosingtimer.databinding.FragmentTimerBinding


class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private lateinit var viewModel: TimerViewModel

    private  var duration: Long = 0
    private var remainedDuration:Long = 0
    private var tmp:Float = 0f

    private var timerStateRunning = false

    private enum class TimerState{STOPPED, RUNNING, PAUSED}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_timer, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        viewModel.currentTime.observe(viewLifecycleOwner, Observer {
            binding.progressTextView.text = DateUtils.formatElapsedTime(it)
            //binding.ProgressBar.progress = it.toFloat()
        })
        viewModel.roundTime.observe(viewLifecycleOwner, Observer {
            binding.buttonRoundTime.text ="Round time ${DateUtils.formatElapsedTime(it/10000L)}"
        })
        viewModel.restTime.observe(viewLifecycleOwner, Observer {
            binding.buttonRestTime.text ="Rest time ${DateUtils.formatElapsedTime(it/10000L)}"
        })
        viewModel.durationTime.observe(viewLifecycleOwner, Observer {
            //duration = it
            binding.ProgressBar.progress = it.toFloat()

        })

        viewModel.remainedDurationTime.observe(viewLifecycleOwner, Observer {
            remainedDuration = it
        })

        viewModel.durationTimerMax.observe(viewLifecycleOwner, Observer {
            binding.ProgressBar.progressMax = it.toFloat()
        })


    }

    private fun startProgressBar(){

    /*binding.ProgressBar.apply {
            progress = 0f
            progressMax = 100f
            setProgressWithAnimation(100f, duration)
            tmp = progress
        }
        tmp = binding.ProgressBar.progress
        binding.ProgressBar.progress = 0f*/
    }

    private fun stopProgressBar(){

    }

    override fun onResume() {
        super.onResume()

        binding.buttonFight.setOnClickListener(){
            if(viewModel.timerState == "STOPPED"){
                viewModel.timerState = "RUNNING"
                binding.buttonFight.text ="Pause"
                timerStateRunning = true
                viewModel.clickFiteButton(10000, 20000, 3)
                return@setOnClickListener
            }
            if(viewModel.timerState == "RUNNING") {
                viewModel.timerState = "PAUSED"
                binding.buttonFight.text ="Fight"
                timerStateRunning = false
                viewModel.clickStopButton()
                stopProgressBar()
                return@setOnClickListener
            }
            if(viewModel.timerState == "PAUSED") {
                binding.buttonFight.text ="Pause"
                viewModel.timerState = "RUNNING"
                viewModel.clickResumeTimer()
                return@setOnClickListener
            }

        }
         binding.buttonRoundTime.setOnClickListener(){
             viewModel.clickResumeTimer()
         }
    }

}