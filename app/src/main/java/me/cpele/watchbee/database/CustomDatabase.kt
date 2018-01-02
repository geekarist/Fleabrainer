package me.cpele.watchbee.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import me.cpele.watchbee.database.dao.GoalTimingDao
import me.cpele.watchbee.database.dao.StatusChangeDao
import me.cpele.watchbee.domain.GoalTiming
import me.cpele.watchbee.domain.StatusChange

@Database(entities = [GoalTiming::class, StatusChange::class], version = 1)
@TypeConverters(ConvertStatus::class, ConvertDate::class)
abstract class CustomDatabase : RoomDatabase() {
    abstract fun goalTimingDao(): GoalTimingDao
    abstract fun statusDao(): StatusChangeDao
}