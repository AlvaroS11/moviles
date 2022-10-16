package com.example.datingorrelated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class gameRepository(private val gameDao: GameDao) {

    val allProducts: LiveData<List<GameStats>> = gameDao.getAllGame()
    val searchResults = MutableLiveData<List<GameStats>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertGame(newgame: GameStats) {
        coroutineScope.launch(Dispatchers.IO) {
            gameDao.insertGame(newgame)
        }
    }

    fun deleteGame(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            gameDao.deleteGame(name)
        }
    }

    fun findGame(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<GameStats>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async gameDao.findGame(name)
        }
}