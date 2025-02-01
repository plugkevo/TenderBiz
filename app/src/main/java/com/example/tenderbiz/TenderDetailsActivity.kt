package com.example.tenderbiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenderbiz.databinding.ActivityTenderDetailsBinding

class TenderDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from Intent
        val title = intent.getStringExtra("TITLE")
        val category = intent.getStringExtra("CATEGORY")
        val description = intent.getStringExtra("DESCRIPTION")
        val openingDate = intent.getStringExtra("OPENING_DATE")
        val closingDate = intent.getStringExtra("CLOSING_DATE")

        // Set data to views
        binding.tvTenderTitle.text = title
        binding.tvTenderCategory.text = "Category: $category"
        binding.tvTenderDescription.text = "Description: $description"
        binding.tvTenderOpeningDate.text = "Opening Date: $openingDate"
        binding.tvTenderClosingDate.text = "Closing Date: $closingDate"
    }
}
