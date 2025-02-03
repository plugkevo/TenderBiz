package com.example.tenderbiz

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenderbiz.databinding.ActivityUpdateTenderDetailsBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class UpdateTenderDetails : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateTenderDetailsBinding
    private val db = FirebaseFirestore.getInstance()
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTenderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from Intent
        val tenderId = intent.getStringExtra("TENDER_ID")
        val title = intent.getStringExtra("TITLE")
        val category = intent.getStringExtra("CATEGORY")
        val description = intent.getStringExtra("DESCRIPTION")
        val openingDate = intent.getStringExtra("OPENING_DATE")
        val closingDate = intent.getStringExtra("CLOSING_DATE")

        // Set data to EditTexts
        binding.tvTenderTitle.text = title
        binding.etTenderCategory.setText(category)
        binding.etTenderDescription.setText(description)
        binding.etTenderOpeningDate.setText(openingDate)
        binding.etTenderClosingDate.setText(closingDate)

        // Open Calendar when clicking the Opening Date EditText
        binding.etTenderOpeningDate.setOnClickListener {
            showDatePicker { selectedDate ->
                binding.etTenderOpeningDate.setText(selectedDate)
            }
        }

        binding.etTenderClosingDate.setOnClickListener {
            showDatePicker { selectedDate ->
                binding.etTenderClosingDate.setText(selectedDate)
            }
        }

        binding.btnUpdateTender.setOnClickListener {
            if (tenderId != null) {
                updateTender(tenderId)
            } else {
                Toast.makeText(this, "Error: Tender ID is missing", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnDeleteTender.setOnClickListener {
            if (tenderId != null) {
                deleteTender(tenderId)
            } else {
                Toast.makeText(this, "Error: Tender ID is missing", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to show DatePickerDialog
    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    .format(Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }.time)
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }


    private fun updateTender(tenderId: String) {
        val updatedTender = mapOf(
            "title" to binding.tvTenderTitle.text.toString(),
            "category" to binding.etTenderCategory.text.toString(),
            "description" to binding.etTenderDescription.text.toString(),
            "openingDate" to binding.etTenderOpeningDate.text.toString(),
            "closingDate" to binding.etTenderClosingDate.text.toString()
        )

        db.collection("tenders").document(tenderId)
            .update(updatedTender)
            .addOnSuccessListener {
                Toast.makeText(this, "Tender updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Update failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteTender(tenderId: String) {
        db.collection("tenders").document(tenderId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Tender deleted successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Delete failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
