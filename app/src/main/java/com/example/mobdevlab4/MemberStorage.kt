package com.example.mobdevlab4

import android.content.Context
import android.content.Context.MODE_PRIVATE

class MemberStorage(context: Context) {

    private val preferences = context.getSharedPreferences("members", MODE_PRIVATE)

    fun saveMembers(members: List<Member>) {
        with(preferences.edit()) {
            putInt("N", members.size)
            members.forEachIndexed { i, it -> putString("Person $i", it.name) }
            apply()
        }
    }

    fun loadMembers() = (0 until preferences.getInt("N", 0)).map {
        Member(preferences.getString("Person $it", "")!!)
    }
}