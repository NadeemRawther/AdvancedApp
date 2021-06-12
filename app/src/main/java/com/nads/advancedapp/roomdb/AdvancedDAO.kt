package com.nads.advancedapp.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AdvancedDAO {
    @Query("SELECT * FROM advanced_table ORDER BY name ASC")
    fun getAlphabetizedWords(): Flow<List<AdvancedAppEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(advancedAppEntity: AdvancedAppEntity)

    @Query("DELETE FROM advanced_table")
    suspend fun deleteAll()

}