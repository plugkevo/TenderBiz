package com.example.tenderbiz

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TendersAdapter(private val tenderList: List<Tender>) :
    RecyclerView.Adapter<TendersAdapter.TenderViewHolder>() {

    class TenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tenderslistview, parent, false)
        return TenderViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenderViewHolder, position: Int) {
        val tender = tenderList[position]
        holder.tvTitle.text = tender.title
        holder.tvCategory.text = tender.category


        // Click listener to open details page
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, TenderDetailsActivity::class.java)
            intent.putExtra("TITLE", tender.title)
            intent.putExtra("CATEGORY", tender.category)
            intent.putExtra("DESCRIPTION", tender.description)
            intent.putExtra("OPENING_DATE", tender.openingDate)
            intent.putExtra("CLOSING_DATE", tender.closingDate)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return tenderList.size
    }
}
