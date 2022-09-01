package com.example.fruitmat.FeatureCollectors.Domain

import com.example.fruitmat.FeatureCollectors.Data.CollectorWithHistory
import com.example.fruitmat.FeatureCollectors.Domain.Manager.CollectorsManagerImpl
import org.junit.Test
import junit.framework.TestCase.assertEquals

class CollectorsManagerImplTest {
    @Test
    fun create(){
        val manager = CollectorsManagerImpl(ArrayList<CollectorWithHistory>())
        assertEquals(manager.getCollectorsAsArrayList(),ArrayList<CollectorWithHistory>())

    }

//    @Test
//    fun addHarvestedValidationNeeded(){
//        val manager = CollectorsManagerImpl(ArrayList<CollectorDto>())
//        manager.addCollector("something")
//        manager.addHarvestedToCollector(0,2,7.5f)
//        assertEquals(manager.getCollector(0), CollectorDto("something",3,2.5f))
//    }
}