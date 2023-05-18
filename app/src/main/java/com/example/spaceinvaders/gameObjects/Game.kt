package com.example.spaceinvaders.gameObjects

data class Game(
    var player: Player,
    var aliens: List<Alien>,
    var bullets: List<Bullet>,
    var isOver: Boolean
)