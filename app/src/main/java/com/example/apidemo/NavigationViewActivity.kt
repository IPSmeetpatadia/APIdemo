package com.example.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NavigationViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_view)

        supportActionBar?.hide()
    }
}