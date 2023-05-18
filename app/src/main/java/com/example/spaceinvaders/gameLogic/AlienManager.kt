package com.example.spaceinvaders.gameLogic

import kotlin.random.Random
import androidx.lifecycle.MutableLiveData
import com.example.spaceinvaders.gameObjects.Alien

class AlienManager(private val gameViewModel: GameViewModel) {
    private val maxAliensWidth = 10
    private val gridHeight = 20

    // Metoda do generowania nowych przeciwników.
    fun spawnAliens() {
        val newAliens = mutableListOf<Alien>()
        val numberOfAliens = Random.nextInt(maxAliensWidth)

        for (i in 0 until numberOfAliens) {
            val alien = Alien(i.toFloat(), 0f, 1f, 100)
            newAliens.add(alien)
        }

        val game = gameViewModel.game.value
        game?.aliens = game?.aliens.orEmpty() + newAliens
        gameViewModel.updateGame(game!!)
    }

    // Metoda do poruszania przeciwnikami.
    fun moveAliens() {
        val game = gameViewModel.game.value
        for (alien in game?.aliens.orEmpty()) {
            alien.positionY += 1
        }
        spawnAliens()
        gameViewModel.updateGame(game!!)
    }

    // Metoda do usuwania przeciwników, którzy zostali zniszczeni.
    fun removeDestroyedAliens() {
        val game = gameViewModel.game.value
        val player = game?.player
        var allAliensDestroyed = true
        game?.aliens?.let { aliens ->
            val it = aliens.iterator()
            while (it.hasNext()) {
                val alien = it.next()
                if (alien.health <= 0) {
                    player?.score = player?.score?.plus(1) ?: 0
                    it.remove()
                } else {
                    allAliensDestroyed = false
                }
            }
        }

        if (allAliensDestroyed) {
            gameViewModel.allAliensDestroyedEvent.value = Event(Unit)
        }

        gameViewModel.updateGame(game!!)
    }
}

