package com.example.tenderbiz

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenderbiz.databinding.ActivityAddTendersBinding
import com.google.firebase.firestore.FirebaseFirestore

class addTenders : AppCompatActivity() {

    private lateinit var binding: ActivityAddTendersBinding
    private val db = FirebaseFirestore.getInstance() // Initialize Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTendersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle submit button click
        binding.submitButton.setOnClickListener {
            submitTender()
        }
    }

    private fun submitTender() {
        // Get values from input fields
        val title = binding.tenderTitleInput.text.toString().trim()
        val category = binding.categoryInput.text.toString().trim()
        val description = binding.descriptionInput.text.toString().trim()
        val openingDate = binding.openingDateInput.text.toString().trim()
        val closingDate = binding.closingDateInput.text.toString().trim()

        // Validate input
        if (title.isEmpty() || category.isEmpty() || description.isEmpty() || openingDate.isEmpty() || closingDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a tender object
        val tender = hashMapOf(
            "title" to title,
            "category" to category,
            "description" to description,
            "openingDate" to openingDate,
            "closingDate" to closingDate
        )

        // Save to Firestore
        db.collection("tenders")
            .add(tender)
            .addOnSuccessListener {
                Toast.makeText(this, "Tender submitted successfully!", Toast.LENGTH_SHORT).show()
                clearInputs()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to submit tender!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearInputs() {
        binding.tenderTitleInput.text.clear()
        binding.categoryInput.text.clear()
        binding.descriptionInput.text.clear()
        binding.openingDateInput.text.clear()
        binding.closingDateInput.text.clear()
    }
}
