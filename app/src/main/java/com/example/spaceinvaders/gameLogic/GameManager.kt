package com.example.spaceinvaders.gameLogic

import android.graphics.Path
import com.example.spaceinvaders.gameObjects.Game
import com.example.spaceinvaders.gameObjects.Player
import com.example.spaceinvaders.gameObjects.Spaceship

class GameManager(private val gameViewModel: GameViewModel, private val alienManager: AlienManager, private val bulletManager: BulletManager) {

    // Metoda do generowania nowych przeciwników.
    fun spawnAliens() {
        alienManager.spawnAliens()
    }

    // Metoda do poruszania statkiem kosmicznym gracza.
    fun moveSpaceship(direction: Direction) {
        val game = gameViewModel.game.value
        val spaceship = game?.player?.spaceship
        spaceship?.positionX += when (direction) {
            Direction.LEFT -> -1
            Direction.RIGHT -> 1
        }
        gameViewModel.updateGame(game!!)
    }

    // Metoda do strzelania pociskiem.
    fun shootBullet() {
        bulletManager.spawnBullet()
    }

    // Metoda do sprawdzania kolizji między pociskami a przeciwnikami.
    fun checkCollisions() {
        bulletManager.moveBullets()
        alienManager.moveAliens()
    }

    // Metoda do aktualizowania wyniku gracza.
    fun updateScore() {
        // Score is updated in alienManager.removeDestroyedAliens()
    }

    // Metoda do zakończenia gry.
    fun endGame() {
        val game = gameViewModel.game.value
        game?.isGameOver = true
        gameViewModel.updateGame(game!!)
    }

    // Metoda do rozpoczęcia nowej gry.
    fun startNewGame() {
        val game = Game(Player("Player1", 0, Spaceship(0f, 0f, 0f, 100)), emptyList(), emptyList(), false)
        gameViewModel.updateGame(game)
    }
}

enum class Direction {
    LEFT, RIGHT
}

