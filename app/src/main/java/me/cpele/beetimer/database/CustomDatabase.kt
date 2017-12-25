package me.cpele.beetimer.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import me.cpele.beetimer.api.Goal
import me.cpele.beetimer.api.User
import me.cpele.beetimer.database.dao.GoalDao
import me.cpele.beetimer.database.dao.StatusChangeDao
import me.cpele.beetimer.database.dao.UserDao
import me.cpele.beetimer.domain.StatusChange

@Database(entities = [Goal::class, User::class, StatusChange::class], version = 1)
@TypeConverters(ConvertStatus::class, ConvertDate::class)
abstract class CustomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun goalDao(): GoalDao
    abstract fun statusDao(): StatusChangeDao
}