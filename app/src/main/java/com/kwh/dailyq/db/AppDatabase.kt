package com.kwh.dailyq.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kwh.dailyq.db.dao.QuestionDao
import com.kwh.dailyq.db.dao.UserDao
import com.kwh.dailyq.db.entiry.QuestionEntity
import com.kwh.dailyq.db.entiry.UserEntity
import com.kwh.dailyq.ui.base.BaseFragment

@Database(
    entities = [
        UserEntity::class,
        QuestionEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuestionDao(): QuestionDao

    companion object {
        const val FILENAME = "dailyq.db"
        var INSTANCE: AppDatabase? = null

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                FILENAME
            ).fallbackToDestructiveMigration()
                .build()
        }

        fun init(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: create(context).also {
                INSTANCE = it
            }
        }

        fun getInstance(): AppDatabase = INSTANCE!!
    }

}