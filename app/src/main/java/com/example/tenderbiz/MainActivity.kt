package com.example.tenderbiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenderbiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter=ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(FirstFragment(),"First")
        adapter.addFragment(SecondFragment(),"Second")
        adapter.addFragment(ThirdFragment(),"Third")

        binding.viewPager.adapter=adapter
        binding.tbLayout.setupWithViewPager(binding.viewPager)

        binding.moreBtn.setOnClickListener{
            val intent = Intent(this,MoreActivity::class.java)
            startActivity(intent)
        }

    }
}