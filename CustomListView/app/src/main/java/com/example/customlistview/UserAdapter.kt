package com.example.customlistview

import android.content.Context
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class UserAdapter(context : Context, users : ArrayList<User>) : ArrayAdapter<User>(context, 0, users) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val currentUser = getItem(position)

        val nameTextView = listItemView!!.findViewById<TextView>(R.id.tvUserName)
        val ageTextView = listItemView.findViewById<TextView>(R.id.tvUserAge)
        val genderTextView = listItemView.findViewById<TextView>(R.id.tvUserGender)

        nameTextView.text = "Họ và tên: ${currentUser!!.name}"
        ageTextView.text = "Tuổi: ${currentUser.age.toString()}"
        genderTextView.text =  "Giới tính: ${currentUser.gender}"

        return listItemView
    }
}