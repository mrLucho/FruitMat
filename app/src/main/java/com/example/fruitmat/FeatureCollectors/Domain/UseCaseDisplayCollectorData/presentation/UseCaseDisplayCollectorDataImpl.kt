package com.example.fruitmat.FeatureCollectors.Domain.UseCaseDisplayCollectorData.presentation

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitmat.FeatureCollectors.Data.AdditionsHistory
import com.example.fruitmat.FeatureCollectors.Domain.Manager.CollectorsManagerImpl
import com.example.fruitmat.FeatureCollectors.Domain.UseCaseDisplayCollectorData.domain.UseCaseCollectorDataHelper
import com.example.fruitmat.FeatureCollectors.presentation.RcAdapter
import com.example.fruitmat.R
import java.lang.Exception

class UseCaseDisplayCollectorDataImpl(
    private val managerImpl: CollectorsManagerImpl,
    val context: Context?, private val holder:RcAdapter.ReviewHolder)
    : UseCaseDisplayCollectorData {

    private lateinit var mDialogView: View
    private lateinit var actualCases:TextView
    private lateinit var actualKg:TextView



    override fun openDialogBox(position: Int):AlertDialog{
        mDialogView = LayoutInflater.from(context).inflate(R.layout.add_collected,null)
        val builder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle(managerImpl.getCollector(position).name)
            .setCancelable(true)
        return  builder.show()
    }
    fun refreshCurrentHarvest(position: Int){
        actualCases.text = managerImpl.getCollector(position).cages.toString()
        actualKg.text = managerImpl.getCollector(position).kilograms.toString()
    }


    override fun trigger(position:Int){
        val currentWorker = managerImpl.getCollector(position)
        val mDialog = openDialogBox(position)
        val cages = mDialogView.findViewById<EditText>(R.id.etGiveCages)
        val kg = mDialogView.findViewById<EditText>(R.id.etGiveKg)
        val btn = mDialogView.findViewById<Button>(R.id.btnApplyAdding)
        val tvTime = mDialogView.findViewById<TextView>(R.id.tvstartEndTime)
        actualCases = mDialogView.findViewById<TextView>(R.id.tvCurrentCages)
        actualKg = mDialogView.findViewById<TextView>(R.id.tvCurrentKg)
        mDialogView.findViewById<Button>(R.id.btnClose).setOnClickListener {
            mDialog.dismiss()
        }

        refreshCurrentHarvest(position)
        val recyclerView = mDialogView.findViewById<RecyclerView>(R.id.recCollectorHistory)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = HistoryRecAdapter(currentWorker.additionsHistoryLst.reversed())
        tvTime.text = "start ${currentWorker.startTime.hour}:${currentWorker.startTime.minute}"

        mDialogView.findViewById<Button>(R.id.btnPayOut).setOnClickListener {
//                pay btn
            if (currentWorker.paycheck == 0f) {
                tvTime.text =
                    "start ${currentWorker.startTime.hour}:${currentWorker.startTime.minute}\n end: ${currentWorker.endTime.hour}:${currentWorker.endTime.minute}"
                val tvPayment = mDialogView.findViewById<TextView>(R.id.tvPaymentString)
                tvPayment.text =
                    UseCaseCollectorDataHelper().paymentString(currentWorker)
                holder.imgPay.visibility =
                    UseCaseCollectorDataHelper().togglePaymentVisibility(currentWorker)
                }
            }

//        apply btn
        btn.setOnClickListener {
            if (currentWorker.paycheck == 0f) {
                val numCages: Int = try {
                    cages.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }
                val numKg: Float = try {
                    kg.text.toString().toFloat()
                } catch (e: Exception) {
                    0f
                }
                if (numCages != 0 || numKg != 0f) {
                    Toast.makeText(
                        context,
                        "added $numCages cages and $numKg kg",
                        Toast.LENGTH_SHORT
                    ).show()
                    managerImpl.addHarvestedToCollector(position, numCages, numKg)
                    holder.tvcages.text =
                        currentWorker.cages.toString()
                    holder.tvKg.text =
                        currentWorker.kilograms.toString()
                    cages.setText("")
                    kg.setText("")
                    refreshCurrentHarvest(position)
                }
            }
        }
        if (currentWorker.paycheck != 0f) {
            tvTime.text =
                "start ${currentWorker.startTime.hour}:${currentWorker.startTime.minute}\n end: ${currentWorker.endTime.hour}:${currentWorker.endTime.minute}"
            val tvPayment = mDialogView.findViewById<TextView>(R.id.tvPaymentString)
            tvPayment.text = managerImpl.getCollector(position).paycheck.toString()
        }
    }
}
