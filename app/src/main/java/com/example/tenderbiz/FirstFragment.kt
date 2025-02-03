package com.example.tenderbiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tenderbiz.databinding.FragmentFirstBinding
import com.google.firebase.firestore.FirebaseFirestore

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TendersAdapter
    private val tenderList = mutableListOf<Tender>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        adapter = TendersAdapter(tenderList)
        binding.rvTenders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTenders.adapter = adapter

        // Fetch tenders from Firestore
        fetchTenders()
    }

    private fun fetchTenders() {
        db.collection("tenders").get().addOnSuccessListener { documents ->
            tenderList.clear()
            for (document in documents) {
                val tender = document.toObject(Tender::class.java)
                tenderList.add(tender)
            }
            adapter.notifyDataSetChanged()
            binding.tvLoadingData.visibility = View.GONE
        }.addOnFailureListener {
            binding.tvLoadingData.text = "Failed to load data"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
