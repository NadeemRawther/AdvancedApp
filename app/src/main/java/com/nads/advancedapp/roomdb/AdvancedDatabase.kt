package com.nads.advancedapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(AdvancedAppEntity::class), version = 1, exportSchema = false)
abstract class AdvancedDatabase:RoomDatabase() {
    abstract fun advancendao(): AdvancedDAO


    private class AdvancedDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val advancedDAO = database.advancendao()

                    // Delete all content here.
                   advancedDAO.deleteAll()

                    // Add sample words.
                    var advancedAppEntity = AdvancedAppEntity(0,"nadeem","sidewall","good")
                    advancedDAO.insert(advancedAppEntity)
                    advancedAppEntity = AdvancedAppEntity(1,"naser","mancode","fine")
                    advancedDAO.insert(advancedAppEntity)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AdvancedDatabase? = null

        fun getDatabase(context: Context ,scope: CoroutineScope
        ): AdvancedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AdvancedDatabase::class.java,
                    "advanced_database"
                ).addCallback(AdvancedDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
