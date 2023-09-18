package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val userList = ArrayList<User>()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewMain = findViewById<ListView>(R.id.listViewMain)
        val addBtn = findViewById<FloatingActionButton>(R.id.AddUserBtn)

        adapter = UserAdapter(this, userList)
        listViewMain.adapter = adapter

        addBtn.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_users, null)
        val userInput = dialogView.findViewById<TextView>(R.id.ET_name)
        val ageInput = dialogView.findViewById<TextView>(R.id.ET_age)
        val genderInput = dialogView.findViewById<RadioGroup>(R.id.GenderGroup)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Thêm người dùng")
            .setPositiveButton("Thêm") { _, _ ->
                val name = userInput.text.toString()
                val age = ageInput.text.toString().toIntOrNull()
                val genderId = genderInput.checkedRadioButtonId
                val gender = when (genderId) {
                    R.id.male -> "Nam"
                    R.id.female -> "Nữ"
                    else -> "Khác"
                }

                if (!name.isEmpty() && age != null && genderId != -1) {
                    val newUser = age.let { User(name, it, gender) }
                    userList.add(newUser)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Người dùng đã được thêm", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Hủy") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }
}