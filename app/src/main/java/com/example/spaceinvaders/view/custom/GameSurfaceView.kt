package com.example.spaceinvaders.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.spaceinvaders.GameViewModel
import kotlin.concurrent.thread

class GameSurfaceView(context: Context, private val gameViewModel: GameViewModel) : SurfaceView(context), SurfaceHolder.Callback {
    private val holder: SurfaceHolder = getHolder()
    private val paint: Paint = Paint()
    private val textPaint: Paint = Paint()

    private var running = false
    private val gameLoopThread = thread(start = false) {
        while (running) {
            val canvas: Canvas = holder.lockCanvas()
            canvas.drawColor(Color.DKGRAY)
            drawGame(canvas)
            holder.unlockCanvasAndPost(canvas)
            Thread.sleep(1000 / 60) // 60 FPS
        }
    }

    init {
        holder.addCallback(this)
        textPaint.textSize = 30f
        textPaint.textAlign = Paint.Align.CENTER
        setOnTouchListener { _, event -> handleTouch(event) }
    }

    private fun handleTouch(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                val x = event.x / 50 // 50 to rozmiar obiektów (n)
                gameViewModel.movePlayer(x.toInt())
                return true
            }
        }
        return false
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        running = true
        gameLoopThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        val canvas: Canvas = holder.lockCanvas()
        canvas.drawColor(Color.DKGRAY)
        drawGame(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {running = false}

    private fun drawGame(canvas: Canvas) {
        val game = gameViewModel.game.value
        val m = width // Szerokość planszy
        val l = height // Wysokość planszy
        val n = 50 // Rozmiar statku kosmicznego i przeciwników
        val r = 10 // Rozmiar pocisku

        paint.color = Color.GREEN
        textPaint.color = Color.WHITE
        game?.aliens?.forEach {
            val x = it.positionX * n
            val y = it.positionY * n
            canvas.drawRect(x, y, x + n, y + n, paint)
            canvas.drawText("A", x + n / 2f, y + n / 2f + textPaint.textSize / 2f, textPaint)
        }

        paint.color = Color.YELLOW
        game?.bullets?.forEach {
            canvas.drawRect(it.positionX * r, it.positionY * r, (it.positionX + 1) * r, (it.positionY + 1) * r, paint)
        }

        paint.color = Color.WHITE
        textPaint.color = Color.BLACK
        game?.player?.spaceship?.let {
            val x = it.positionX * n
            val y = it.positionY * n
            canvas.drawRect(x, y, x + n, y + n, paint)
            canvas.drawText("S", x + n / 2f, y + n / 2f + textPaint.textSize / 2f, textPaint)
        }
    }
}

