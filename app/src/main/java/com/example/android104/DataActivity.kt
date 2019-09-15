package com.example.android104

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android104.Database.DatabaseManager
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.row_number_dialog.view.*
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

        button3.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.row_number_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Delete Row with Number")
            val  mAlertDialog = mBuilder.show()

            mDialogView.dialogDeleteBtn.setOnClickListener {
                mAlertDialog.dismiss()
                val name = mDialogView.editText4.text.toString()
                Log.d("App", name)
            }

            mDialogView.dialogCancelBtn.setOnClickListener {
                mAlertDialog.dismiss()
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
