package com.example.fruitmat.FeatureCollectors.Domain.Manager

import com.example.fruitmat.FeatureCollectors.Data.CollectorWithHistory

interface CollectorsManager {
    fun getCollectorsAsArrayList():ArrayList<CollectorWithHistory>
    fun getCollector(position:Int): CollectorWithHistory
    fun addCollector(name: String)
    fun addHarvestedToCollector(position: Int,extraCages:Int,extraKg:Float)
    fun fillWithDummyData()
//    fun clearAllResources():Void
//    fun generateActionReport() for logging user activity
}