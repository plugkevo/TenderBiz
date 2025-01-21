package com.example.tenderbiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tenderbiz.databinding.ActivitySignInBinding
import com.example.tenderbiz.databinding.ActivitySignUpBinding

class activity_sign_up : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toSignIn.setOnClickListener{
                val intent = Intent(this, activity_sign_in::class.java)
                startActivity(intent)
            }




    }
}