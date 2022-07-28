package com.example.fruitmat.FeatureCollectors.Domain

import com.example.fruitmat.FeatureCollectors.Data.CollectorDto
import com.example.fruitmat.FeatureCollectors.Domain.Manager.CollectorsManagerImpl
import org.junit.Test
import junit.framework.TestCase.assertEquals

class CollectorsManagerImplTest {
    @Test
    fun create(){
        val manager = CollectorsManagerImpl(ArrayList<CollectorDto>())
        assertEquals(manager.getCollectorsAsArrayList(),ArrayList<CollectorDto>())

    }
    @Test
    fun addCollector(){
        val manager = CollectorsManagerImpl(ArrayList<CollectorDto>())
        manager.addCollector("something")
        manager.addCollector("somethingOther")

        assertEquals(manager.getCollector(0), CollectorDto("something",0,0f))
        assertEquals(manager.getCollector(1), CollectorDto("somethingOther",0,0f))
    }
    @Test
    fun addHarvested(){
        val manager = CollectorsManagerImpl(ArrayList<CollectorDto>())
        manager.addCollector("something")
        manager.addHarvestedToCollector(0,2,1f)
        assertEquals(manager.getCollector(0), CollectorDto("something",2,1f))
    }

    @Test
    fun addHarvestedValidationNeeded(){
        val manager = CollectorsManagerImpl(ArrayList<CollectorDto>())
        manager.addCollector("something")
        manager.addHarvestedToCollector(0,2,7.5f)
        assertEquals(manager.getCollector(0), CollectorDto("something",3,2.5f))
    }
}