package com.nads.advancedapp

import android.app.Application
import com.nads.advancedapp.roomdb.AdvancedDatabase
import com.nads.advancedapp.roomdb.AdvancedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AdvancedAppApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AdvancedDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { AdvancedRepository(database.advancendao()) }

}