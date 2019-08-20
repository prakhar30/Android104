package com.example.android104

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_data.*
import java.util.ArrayList

class DataActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private var photosList: ArrayList<UserData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        linearLayoutManager = LinearLayoutManager(this)
        my_recycler_view.layoutManager = linearLayoutManager

        adapter = RecyclerAdapter(photosList)
        my_recycler_view.adapter = adapter

        button2.setOnClickListener {
            if (photosList.size > 0) {
                photosList.removeAt(photosList.size - 1)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (photosList.size == 0) {
            val user1 = UserData("Prakhar", "123", "123")
            val user2 = UserData("Prakhar 2", "123", "123")
            val user3 = UserData("Prakhar 3", "123", "123")
            
            photosList.add(user1)
            photosList.add(user2)
            photosList.add(user3)

            adapter.notifyDataSetChanged()
        }
    }
}
