package com.nads.advancedapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nads.advancedapp.roomdb.AdvancedAppEntity
import com.nads.advancedapp.roomdb.AdvancedViewModel
import com.nads.advancedapp.roomdb.AdvancedViewModelFactory

class MainActivity : AppCompatActivity() {
    private val advancedViewModel: AdvancedViewModel by viewModels {
        AdvancedViewModelFactory((application as AdvancedAppApplication).repository)
    }
    private val newWordActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<EditText>(R.id.editTextTextPersonName)

        val address = findViewById<EditText>(R.id.editTextTextPersonName2)

        val bio = findViewById<EditText>(R.id.editTextTextPersonName3)

        val submit = findViewById<Button>(R.id.button)

        submit.setOnClickListener(View.OnClickListener {
            val advancedAppEntity = AdvancedAppEntity(6,name.text.toString()
                    ,address.text.toString()
                    ,bio.text.toString())
            advancedViewModel.insert(advancedAppEntity)
            val intent = Intent(this,ShowData::class.java)
            startActivity(intent)
        })


        advancedViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.

        })


    }





}