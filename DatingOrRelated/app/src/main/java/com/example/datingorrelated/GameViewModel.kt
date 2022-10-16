package com.example.datingorrelated

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel (application: Application) : ViewModel() {

    val allGames: LiveData<List<GameStats>>
    private val repository: gameRepository
    val searchResults: MutableLiveData<List<GameStats>>

    init {
        val gameDb = GameRoomDatabase.getInstance(application)
        val productDao = gameDb.gameDao()
        repository = gameRepository(productDao)

        allGames = repository.allProducts
        searchResults = repository.searchResults
    }
    fun insertProduct(game: GameStats) {
        repository.insertGame(game)
    }

    fun findProduct(name: String) {
        repository.findGame(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteGame(name)
    }

}
