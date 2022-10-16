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

    constructor() {}

    constructor(id: Int, productname: String, timesecs: Int) {
        this.playerName = productname
        this.timeSecs = timesecs
    }
    constructor(playername: String, timesecs: Int) {
        this.playerName = playername
        this.timeSecs = timesecs
    }
}