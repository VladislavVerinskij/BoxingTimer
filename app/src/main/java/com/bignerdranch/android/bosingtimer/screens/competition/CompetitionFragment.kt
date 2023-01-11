package com.bignerdranch.android.bosingtimer.screens.competition

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.bosingtimer.R
import com.bignerdranch.android.bosingtimer.databinding.FragmentCompetitionBinding
import com.bignerdranch.android.bosingtimer.databinding.FragmentTimerBinding


class CompetitionFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionBinding
    private lateinit var viewModel: CompetitionViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { CompetitionAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_competition, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[CompetitionViewModel::class.java]
        recyclerView = binding.recyclerviewCompetition
       // recyclerView.adapter = adapter

    }

}