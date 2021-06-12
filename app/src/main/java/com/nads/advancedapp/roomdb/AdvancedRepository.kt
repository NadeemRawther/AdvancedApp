package com.nads.advancedapp.roomdb

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class AdvancedRepository (private val advancedDAO: AdvancedDAO) {

    val allWords: Flow<List<AdvancedAppEntity>> = advancedDAO.getAlphabetizedWords()


    suspend fun getdatas(): Flow<List<AdvancedAppEntity>> {
       return advancedDAO.getAlphabetizedWords()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(advancedAppEntity: AdvancedAppEntity) {
        advancedDAO.insert(advancedAppEntity)
    }
}
