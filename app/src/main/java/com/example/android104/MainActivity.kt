package com.example.android104

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.android104.Database.DatabaseManager
import com.example.android104.Database.UserRecord
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val name = editText.text.toString()
            val email = editText2.text.toString()
            val phone = editText3.text.toString()

            val userRecord = UserRecord(name, phone, email)
            DatabaseManager.getInstance(applicationContext)!!.userRecordDao().insert(userRecord)

            Toast.makeText(applicationContext, "User Record Saved!", Toast.LENGTH_SHORT).show()

            editText.text.clear()
            editText2.text.clear()
            editText3.text.clear()

            focusFirstEditText()
        }
    }

    override fun onResume() {
        super.onResume()

        focusFirstEditText()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_data_option -> {
                navigateToDataPage()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToDataPage() {
        val intent = Intent(this, DataActivity::class.java)
        startActivity(intent)
    }

    private fun focusFirstEditText() {
        editText.requestFocus()
        val inputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
}
