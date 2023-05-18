package com.example.spaceinvaders.dataModel

import androidx.room.*

@Dao
interface PlayerDao {
    @Query("SELECT * FROM Playere")
    fun getAll(): List<Player>

    @Query("SELECT * FROM Playere WHERE nick = :nick")
    fun findByNick(nick: String): Player

    @Insert
    fun insert(Player: Player)

    @Update
    fun update(Player: Player)

    @Delete
    fun delete(Player: Player)
}

