package com.example.fruitmat.FeatureCollectors.Domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CollectorDtoTest {
    @Test
    fun correctlyValidate(){
        //given
        val overloaded = CollectorDto("nothing",0,7.5f)
        val normal = CollectorDto("nothing",1,2.5f)

//        when
        overloaded.validate()

//        then
        assertEquals(overloaded,normal)
    }
    @Test
    fun correctlyAddWithoutValidation(){

//        given
        val toBeAdded = CollectorDto("nothing",0,0f)
        val normal = CollectorDto("nothing",1,2.5f)

//        when
        toBeAdded.add(1,2.5f)

//        then
        assertEquals(toBeAdded,normal)

    }
    @Test
    fun correctlyAddWithValidation(){

//        given
        val toBeAdded = CollectorDto("nothing",0,0f)
        val normal = CollectorDto("nothing",1,2.5f)

//        when
        toBeAdded.add(0,7.5f)

//        then
        assertEquals(toBeAdded,normal)

    }
}