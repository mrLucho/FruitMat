package com.example.fruitmat.FeatureCollectors.Domain.UseCaseDisplayCollectorData.domain

import android.view.View
import com.example.fruitmat.FeatureCollectors.Data.CollectorWithHistory
import com.example.fruitmat.FeatureCollectors.Domain.Manager.CollectorsManagerImpl
import com.example.fruitmat.common.constants.Constants
import com.example.fruitmat.common.constants.dailyConsts
import java.time.LocalDateTime

class UseCaseCollectorDataHelper:PayOutCollector {
    override fun payAndMarkColPayed(collectorWithHistory: CollectorWithHistory):Float{
//
        val  pay = (collectorWithHistory.cages * Constants.cageCapacity + collectorWithHistory.kilograms )* dailyConsts.workerPaymentForKg
        collectorWithHistory.paycheck = pay
        collectorWithHistory.endTime= LocalDateTime.now()
        return pay
    }
    override fun paymentString(collectorWithHistory: CollectorWithHistory):String{
        return "(${collectorWithHistory.cages * Constants.cageCapacity} + ${collectorWithHistory.kilograms}) * ${dailyConsts.workerPaymentForKg} = ${payAndMarkColPayed(collectorWithHistory)}"

    }
    override fun togglePaymentVisibility(collectorWithHistory: CollectorWithHistory): Int {
        return if(collectorWithHistory.paycheck != 0f){
            View.VISIBLE
        }
        else{
            View.INVISIBLE
        }
    }
}