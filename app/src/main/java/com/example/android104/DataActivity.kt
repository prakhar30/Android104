package com.example.android104

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android104.Database.DatabaseManager
import kotlinx.android.synthetic.main.activity_data.*
import java.util.ArrayList

class DataActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private var userList: ArrayList<UserData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        linearLayoutManager = LinearLayoutManager(this)
        my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapter(userList)
        my_recycler_view.adapter = adapter

        button2.setOnClickListener {
            if (userList.size > 0) {
                DatabaseManager.getInstance(applicationContext)!!.userRecordDao().clear()
                userList.clear()
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val list = DatabaseManager.getInstance(applicationContext)!!.userRecordDao().getAll()

        for (record in list) {
            val user = UserData(record.name!!, record.phoneNumber!!, record.email!!)
            userList.add(user)
        }
    }
}
