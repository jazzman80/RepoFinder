package ru.craftapps.repofinder.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.craftapps.repofinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}