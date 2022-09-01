package com.example.fruitmat.FeatureCollectors.Data

import androidx.room.Entity
import java.time.LocalDateTime

@Entity
data class CollectorWithHistory(val name: String,
                                var cages:Int,
                                var kilograms:Float,
                                val startTime: LocalDateTime,
                                var endTime: LocalDateTime,
                                var paycheck:Float,
                                var endOfWork:Boolean=false,
                                var additionsHistoryLst :ArrayList<AdditionsHistory> = ArrayList())
