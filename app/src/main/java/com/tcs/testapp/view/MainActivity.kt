package com.tcs.testapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tcs.testapp.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

    }

}