package com.example.spaceinvaders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spaceinvaders.gameLogic.Event
import com.example.spaceinvaders.gameObjects.Game

class GameViewModel: ViewModel() {
    private val _game = MutableLiveData<Game>()
    val game: LiveData<Game> = _game

    private val _allAliensDestroyedEvent = MutableLiveData<Event<Unit>>()
    val allAliensDestroyedEvent: LiveData<Event<Unit>> = _allAliensDestroyedEvent

    fun updateGame(updatedGame: Game) {
        _game.value = updatedGame
    }

    fun updateAliensDestroyedEvent(updatedEvent: Event<Unit>) {
        _allAliensDestroyedEvent.value = updatedEvent
    }

    fun movePlayer(x: Int) {
        game.value?.player?.spaceship?.positionX = x.toFloat()

    }
}
