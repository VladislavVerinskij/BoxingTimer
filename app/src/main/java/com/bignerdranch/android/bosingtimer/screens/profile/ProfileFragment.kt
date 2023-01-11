package com.bignerdranch.android.bosingtimer.screens.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.bosingtimer.R
import com.bignerdranch.android.bosingtimer.databinding.FragmentProfileBinding
import com.bignerdranch.android.bosingtimer.service.databace.CHILD_ID
import com.bignerdranch.android.bosingtimer.service.databace.CHILD_USER_NAME
import com.bignerdranch.android.bosingtimer.service.databace.NODE_USERS
import com.bignerdranch.android.bosingtimer.service.databace.REF_DATABASE_ROOT
import com.google.android.gms.ads.AdRequest
import java.util.*


@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var pref: SharedPreferences

    var id = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = activity?.getSharedPreferences("TABLE",Context.MODE_PRIVATE)!!
        generateID()
        realTimeDataBase()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        reclameBaner()
    }

    override fun onResume() {
        super.onResume()
        binding.imageUser.setOnClickListener {

        }
    }

    private fun realTimeDataBase()
    {
        var dateMap:MutableMap<String,Any> = mutableMapOf<String,Any>()
        dateMap[CHILD_ID] = id
        dateMap[CHILD_USER_NAME] = "user"

        REF_DATABASE_ROOT.child(NODE_USERS).child(id).updateChildren(dateMap)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful)
                    println("ok!!! hello ")
                else
                    println("error database")
            }
    }

    private fun getID():String
    {
        var id = UUID.randomUUID().toString()
        return id
    }

    private fun saveID(id:String)
    {
        val editor = pref.edit()
        editor.putString("id", id)
        editor.apply()
    }

    private fun generateID():String
    {
        id = pref?.getString("id", getID())!!
        saveID(id)
        println(id)
        return id
    }

    private fun  reclameBaner(){
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        binding.adView.adListener
    }

}