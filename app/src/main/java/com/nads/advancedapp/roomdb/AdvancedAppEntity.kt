package com.nads.advancedapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "advanced_table")
data class AdvancedAppEntity(@PrimaryKey(autoGenerate = true) val id: Int,
                             @ColumnInfo (name = "Name") val name: String,
                             @ColumnInfo (name = "address") val address:String,
                             @ColumnInfo (name = "bio") val bio:String)
