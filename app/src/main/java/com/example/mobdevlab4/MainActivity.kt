package com.example.mobdevlab4

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private val appBarConfiguration by lazy { AppBarConfiguration(controller.graph) }
    private val controller by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(controller, appBarConfiguration)
        val actionBar = getSupportActionBar()
        val colorDrawable = ColorDrawable(getColor(R.color.blue))
        actionBar?.setBackgroundDrawable(colorDrawable)
    }

    override fun onSupportNavigateUp() =
        controller.navigateUp(appBarConfiguration) || super.onNavigateUp()
}