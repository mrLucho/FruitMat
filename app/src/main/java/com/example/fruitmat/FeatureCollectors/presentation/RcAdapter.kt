package com.example.fruitmat.FeatureCollectors.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitmat.R
import com.example.fruitmat.FeatureCollectors.Domain.Manager.CollectorsManagerImpl
import com.example.fruitmat.FeatureCollectors.Domain.UseCaseAddCollector.UseCaseAddCollectorImpl
import com.example.fruitmat.FeatureCollectors.Domain.UseCaseDisplayCollectorData.domain.UseCaseCollectorDataHelper
import com.example.fruitmat.FeatureCollectors.Domain.UseCaseDisplayCollectorData.presentation.UseCaseDisplayCollectorDataImpl

class RcAdapter(private val managerImpl: CollectorsManagerImpl,val context: Context?):RecyclerView.Adapter<RcAdapter.ReviewHolder>() {

    class ReviewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvname = itemView.findViewById<TextView>(R.id.tvHeading)
        val tvcages = itemView.findViewById<TextView>(R.id.tvCages)
        val tvKg = itemView.findViewById<TextView>(R.id.tvkg)
        val imgPay = itemView.findViewById<ImageView>(R.id.imgPay)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ReviewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            UseCaseDisplayCollectorDataImpl(managerImpl,context,holder).trigger(position)
        }
        val currentWorker = managerImpl.getCollector(position)
        holder.imgPay.visibility = UseCaseCollectorDataHelper().togglePaymentVisibility(currentWorker)
        holder.tvname.text = currentWorker.name
        holder.tvcages.text = currentWorker.cages.toString()
        holder.tvKg.text = currentWorker.kilograms.toString()
    }

    override fun getItemCount(): Int {
        return managerImpl.getCollectorsAsArrayList().size
    }
}