package com.example.datingorrelated

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameStats")
class GameStats {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "gameId")
    var id: Int = 0

    @ColumnInfo(name = "playerName")
    var playerName: String = ""
    var timeSecs: Int = 0
    var score: Int = 0

    constructor() {}

    constructor(id: Int, productname: String, timesecs: Int, score: Int) {
        this.playerName = productname
        this.timeSecs = timesecs
        this.score = score
    }
    constructor(playername: String, timesecs: Int, score: Int) {
        this.playerName = playername
        this.timeSecs = timesecs
        this.score = score
    }
}