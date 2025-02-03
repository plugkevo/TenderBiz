package com.example.tenderbiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tenderbiz.databinding.ActivityTendersBinding
import com.google.firebase.firestore.FirebaseFirestore

class TendersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTendersBinding
    private lateinit var adapter: UpdateTendersAdapter
    private val tenderList = mutableListOf<Tender>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTendersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.rvTenders.layoutManager = LinearLayoutManager(this)
        adapter = UpdateTendersAdapter(tenderList)
        binding.rvTenders.adapter = adapter

        // Fetch tenders from Firestore
        fetchTenders()

        // Floating action button to add tenders
        binding.fab.setOnClickListener {
            val intent = Intent(this, addTenders::class.java)
            startActivity(intent)
        }
    }

    private fun fetchTenders() {
        db.collection("tenders")
            .get()
            .addOnSuccessListener { result ->
                tenderList.clear()
                for (document in result) {
                    val tender = document.toObject(Tender::class.java).copy(id = document.id)
                    tenderList.add(tender)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("TendersActivity", "Error fetching tenders", exception)
            }
    }
}
