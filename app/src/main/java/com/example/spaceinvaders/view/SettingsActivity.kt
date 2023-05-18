package com.example.spaceinvaders.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.spaceinvaders.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val volumeSlider = findViewById<SeekBar>(R.id.volumeSlider)
        volumeSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Zmieniaj głośność efektów dźwiękowych zgodnie z pozycją suwaka.
                // Dla przykładu, użyjemy wartości `progress` bezpośrednio jako głośności.
                // W prawdziwej grze, prawdopodobnie chciałbyś przechować tę wartość w SharedPreferences lub innym miejscu, aby była dostępna w innych częściach gry.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
