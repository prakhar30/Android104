package com.example.android104.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "userRecords")
data class UserRecord(
        @ColumnInfo(name = "name") var name: String? = null,
        @ColumnInfo(name = "phoneNumber") var phoneNumber: String? = null,
        @ColumnInfo(name = "email") var email: String? = null
): Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}