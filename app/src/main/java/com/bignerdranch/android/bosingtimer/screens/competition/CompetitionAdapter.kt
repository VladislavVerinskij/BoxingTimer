package com.bignerdranch.android.bosingtimer.screens.competition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.bosingtimer.R

class CompetitionAdapter: RecyclerView.Adapter<CompetitionAdapter.MyViewHolder>() {

    private var listUsers = emptyList<String>()


    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rc_competition, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

}