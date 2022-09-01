package com.example.fruitmat.FeatureCollectors.Domain.UseCaseAddCollector

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitmat.FeatureCollectors.Domain.Manager.CollectorsManagerImpl
import com.example.fruitmat.FeatureCollectors.presentation.RcAdapter
import com.example.fruitmat.R


class UseCaseAddCollectorImpl(val managerImpl: CollectorsManagerImpl,val recyclerView: RecyclerView,val context: Context?)
    : UseCaseAddCollector {
    private lateinit var mDialogView:View

    fun nameIsValid(name:String):Boolean{
        if (name == ""){
            return false
        }
        for (collector in managerImpl.getCollectorsAsArrayList()){
            if (collector.name == name){
                return false
            }
        }
        return true
    }

    override fun updateRecAdapter(){
//        create new adapter with bigger Collectors lst
        val adapter = RcAdapter(managerImpl,context)
        recyclerView.adapter = adapter
    }
    override fun popDialogView() {
        mDialogView = LayoutInflater.from(context).inflate(R.layout.add_collector_dialog,null)
    }
    override fun trigger(){
        popDialogView()
        val builder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("EnterName")
        val mDialog = builder.show()

        val submitBtn = mDialogView.findViewById<Button>(R.id.submit)
        val cancelBtn = mDialogView.findViewById<Button>(R.id.cancel)
        val editText = mDialogView.findViewById<EditText>(R.id.etEnterCollectorsName)

        submitBtn.setOnClickListener {
            val txt = editText.text.toString()
            if (nameIsValid(txt)){
                mDialog.dismiss()
                managerImpl.addCollector(txt)
                updateRecAdapter()
            }
            else{
                Toast.makeText(context,
                    "invalid name",Toast.LENGTH_SHORT).show()
            }

        }
        cancelBtn.setOnClickListener {
            mDialog.dismiss()
        }
    }
}