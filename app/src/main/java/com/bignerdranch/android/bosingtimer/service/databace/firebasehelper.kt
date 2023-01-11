package com.bignerdranch.android.bosingtimer.service.databace

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

lateinit var REF_DATABASE_ROOT: DatabaseReference

const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_USER_NAME = "user_name"

fun initFirebase()
{
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference

}