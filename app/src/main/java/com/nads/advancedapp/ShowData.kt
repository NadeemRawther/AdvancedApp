package com.nads.advancedapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nads.advancedapp.roomdb.AdvancedAppEntity
import com.nads.advancedapp.roomdb.AdvancedDAO
import com.nads.advancedapp.roomdb.AdvancedViewModel
import com.nads.advancedapp.roomdb.AdvancedViewModelFactory
import kotlinx.coroutines.flow.Flow

class ShowData : AppCompatActivity() {

    private val advancedViewModel: AdvancedViewModel by viewModels {
        AdvancedViewModelFactory((application as AdvancedAppApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        val text = findViewById<TextView>(R.id.showdata)
        val data = AdvancedAppEntity(7,"DSAF","DSAF","dsafa")
        advancedViewModel.insert(data)
        advancedViewModel.allWords.observe(this, Observer { it ->
            // Update the cached copy of the words in the adapter.
            text.text = it.toString()
        })
    }

}