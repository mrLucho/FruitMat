package com.example.fruitmat.FeatureCollectors.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitmat.R
import com.example.fruitmat.FeatureCollectors.DomainAndData.CollectorDto
import com.example.fruitmat.FeatureCollectors.DomainAndData.Manager.CollectorsManagerImpl
import com.example.fruitmat.FeatureCollectors.UseCaseAddCollected.UseCaseAddCollectedImpl
import com.example.fruitmat.MainActivity

class RcAdapter(private val managerImpl: CollectorsManagerImpl,val context: Context?):RecyclerView.Adapter<RcAdapter.ReviewHolder>() {

    class ReviewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val tvname = itemview.findViewById<TextView>(R.id.tvHeading)
        val tvcages = itemview.findViewById<TextView>(R.id.tvCages)
        val tvKg = itemview.findViewById<TextView>(R.id.tvkg)
        val btnAdd = itemview.findViewById<Button>(R.id.btnAddCollected)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ReviewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.tvname.text = managerImpl.getCollector(position).name
        holder.tvcages.text = managerImpl.getCollector(position).cages.toString()
        holder.tvKg.text = managerImpl.getCollector(position).kilograms.toString()
        holder.btnAdd.setOnClickListener {
            val useCase = UseCaseAddCollectedImpl(managerImpl,context)
            useCase.trigger(position)
        }
    }

    override fun getItemCount(): Int {
        return managerImpl.getCollectorsAsArrayList().size
    }
}