package com.example.spaceinvaders.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gracze")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nick: String,
    val points: Int,
    val gameHistory: List<Int>
)
