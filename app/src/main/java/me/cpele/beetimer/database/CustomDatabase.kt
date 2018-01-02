package me.cpele.beetimer.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import me.cpele.beetimer.database.dao.GoalTimingDao
import me.cpele.beetimer.database.dao.StatusChangeDao
import me.cpele.beetimer.domain.GoalTiming
import me.cpele.beetimer.domain.StatusChange

@Database(entities = [GoalTiming::class, StatusChange::class], version = 1)
@TypeConverters(ConvertStatus::class, ConvertDate::class)
abstract class CustomDatabase : RoomDatabase() {
    abstract fun goalTimingDao(): GoalTimingDao
    abstract fun statusDao(): StatusChangeDao
}