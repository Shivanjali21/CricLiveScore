package com.practice.criclivescore.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.practice.criclivescore.R
import com.practice.criclivescore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.mainRoot)

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainRoot)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val navHost = supportFragmentManager.findFragmentById(R.id.mainFCV) as NavHost
        navController = navHost.navController
    }
}