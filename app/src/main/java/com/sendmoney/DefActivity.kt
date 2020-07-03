package com.sendmoney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class DefActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_def)

        supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)?.let { fragment ->
            findNavController(R.id.main_nav_host_fragment).apply {
                graph = navInflater.inflate(R.navigation.main_graph)
                navigate(R.id.to_sendmoney)
            }
        }
    }
}