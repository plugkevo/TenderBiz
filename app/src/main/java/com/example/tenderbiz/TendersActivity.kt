package com.example.tenderbiz

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tenderbiz.databinding.ActivityTendersBinding

class TendersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTendersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTendersBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}