package com.mvvm.mindorks.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.mindorks.data.local.dao.UserDao
import com.mvvm.mindorks.data.model.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun useDao(): UserDao

    class Builder {

        companion object {
            val instance: AppDatabase? = null
        }

        fun build(context: Context): AppDatabase {
            return instance
                ?: Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
        }
    }

}