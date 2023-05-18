package com.example.spaceinvaders.gameLogic

import com.example.spaceinvaders.gameObjects.Alien
import com.example.spaceinvaders.gameObjects.Bullet

class BulletManager(private val gameViewModel: GameViewModel, private val alienManager: AlienManager) {
    private val bulletSpeed = 1f

    // Metoda do generowania nowego pocisku.
    fun spawnBullet() {
        val spaceship = gameViewModel.game.value?.player?.spaceship
        val bullet = Bullet(spaceship?.positionX ?: 0f, spaceship?.positionY ?: 0f, bulletSpeed, 50)
        val game = gameViewModel.game.value
        game?.bullets = game?.bullets.orEmpty() + bullet
        gameViewModel.updateGame(game!!)
    }

    // Metoda do poruszania pociskami.
    fun moveBullets() {
        val game = gameViewModel.game.value
        for (bullet in game?.bullets.orEmpty()) {
            bullet.positionY -= bulletSpeed
            checkCollision(bullet)
        }
        removeOffScreenBullets()
        gameViewModel.updateGame(game!!)
    }

    // Metoda do sprawdzania kolizji między pociskiem a przeciwnikami.
    private fun checkCollision(bullet: Bullet) {
        val game = gameViewModel.game.value
        game?.aliens?.let { aliens ->
            val it = aliens.iterator()
            while (it.hasNext()) {
                val alien = it.next()
                if (isCollision(bullet, alien)) {
                    alien.health -= bullet.damage
                    bullet.damage = 0 // Pocisk zostaje "zużyty"
                    if (alien.health <= 0) {
                        it.remove()
                    }
                }
            }
        }
        alienManager.removeDestroyedAliens()
    }

    // Metoda do usuwania pocisków, które opuściły ekran.
    fun removeOffScreenBullets() {
        val game = gameViewModel.game.value
        game?.bullets?.removeIf { it.positionY < 0 || it.damage <= 0 }
        gameViewModel.updateGame(game!!)
    }

    // Prosta metoda do sprawdzenia kolizji między pociskiem a przeciwnikiem.
    private fun isCollision(bullet: Bullet, alien: Alien): Boolean {
        // Tutaj możesz dodać bardziej skomplikowaną logikę kolizji.
        return bullet.positionX == alien.positionX && bullet.positionY == alien.positionY
    }
}

