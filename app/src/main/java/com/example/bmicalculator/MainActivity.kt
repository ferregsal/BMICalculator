package com.example.bmicalculator


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
class MainActivity : AppCompatActivity() {

    lateinit var welcomeTextView: TextView
    lateinit var clickButton: Button
    var veces = 0
    var colorRandom = Random.nextInt(-8252080, 8252080)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        welcomeTextView = findViewById(R.id.welcomeTextView)
        welcomeTextView.text ="Bienvenido a mi app"

        clickButton = findViewById(R.id.clickButton)
        clickButton.setOnClickListener {
            welcomeTextView.text = "El botón ha sido pulsado ${veces++} veces ${getRandomColor()}"
            it.setBackgroundColor(getRandomColor())
        }
    }
        private fun getRandomColor(): Int {
            val red = Random.nextInt(256)
            val green = Random.nextInt(256)
            val blue = Random.nextInt(256)
            return Color.rgb(red, green, blue)
        }

}