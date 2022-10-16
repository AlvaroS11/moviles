package com.example.datingorrelated

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {


    @Insert
    fun insertGame(game: GameStats)

    @Query("SELECT * FROM gameStats WHERE playerName = :name")
    fun findGame(name: String): List<GameStats>

    @Query("DELETE FROM gameStats WHERE playerName = :name")
    fun deleteGame(name: String)

    @Query("SELECT * FROM gameStats ORDER BY timeSecs")
    fun getAllGame(): LiveData<List<GameStats>>
}


