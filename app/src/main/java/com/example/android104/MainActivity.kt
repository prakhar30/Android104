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
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val email = emailEditText.text.toString()

            val userRecord = UserRecord(name, phone, email)
            DatabaseManager.getInstance(applicationContext)!!.userRecordDao().insert(userRecord)

            Toast.makeText(applicationContext, "User Record Saved!", Toast.LENGTH_SHORT).show()

            nameEditText.text.clear()
            phoneEditText.text.clear()
            emailEditText.text.clear()

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
        nameEditText.requestFocus()
        val inputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(nameEditText, InputMethodManager.SHOW_IMPLICIT)
    }
}
