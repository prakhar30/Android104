package com.example.android104

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {

    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        linearLayoutManager = LinearLayoutManager(this)
        my_recycler_view.layoutManager = linearLayoutManager
    }
}
