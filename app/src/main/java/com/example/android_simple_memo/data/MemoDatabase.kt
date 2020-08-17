package com.example.android_simple_memo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_simple_memo.common.DateConverter

@Database(entities = [Memo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MemoDatabase: RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object {
        @Volatile
        private var INSTANCE: MemoDatabase? = null

        fun getDatabase(context: Context): MemoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemoDatabase::class.java,
                    "memo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
