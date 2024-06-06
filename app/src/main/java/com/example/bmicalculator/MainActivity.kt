package com.example.bmicalculator



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow
import android.graphics.Color
import android.util.Log
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var heightEditText: EditText
    lateinit var weightTextView: TextView
    lateinit var resultTextView: TextView
    lateinit var resultCalculate: TextView
    lateinit var calculateButton: Button
    lateinit var minusButton: Button
    lateinit var addButton: Button
    lateinit var checkLevel:TextView
    lateinit var seekBar: SeekBar

    var height:Int=150
    var weight:Int=70

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightEditText = findViewById(R.id.heightEditText)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.addButton)
        weightTextView = findViewById(R.id.weightTextView)
        resultTextView = findViewById(R.id.resultTextView)

        resultCalculate = findViewById(R.id.resultCalculate)

        calculateButton = findViewById(R.id.calculateButton)
        checkLevel = findViewById(R.id.checkLevel)
        seekBar =findViewById(R.id.seekBar)
        setHeight()
        setWeight()

        minusButton.setOnClickListener{
            weight--
            setWeight()
        }
        addButton.setOnClickListener{
            weight++
            setWeight()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weight = progress
                weightTextView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementar nada aquí para este caso
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementar nada aquí para este caso
            }
        })

        calculateButton.setOnClickListener{
            height=heightEditText.text.toString().toInt()
            val result = weight / (height/100f).pow(2)
            resultCalculate.text = result.toString()
            when (result){
                in (1f..18.4f) -> {
                    checkLevel.text="Insuficiente"
                    checkLevel.setTextColor(Color.BLUE)

                }
                in (18.5f..24.9f) -> {
                    checkLevel.text="Normal"
                    checkLevel.setTextColor(Color.GREEN)
                }
                in (25f..29f) -> {
                    checkLevel.text="Pre-obesidad"
                    checkLevel.setTextColor(Color.YELLOW)
                }
                in (30f..50f) -> {
                    checkLevel.text="Obesidad"
                    checkLevel.setTextColor(Color.RED)
                }
                in (50f..100f) -> {
                    checkLevel.text="Peligro!!"
                    checkLevel.setTextColor(Color.BLACK)
                }
            }
        }
    }
    fun setHeight(){
        heightEditText.setText(height.toString())
    }
    fun setWeight(){
        weightTextView.text = weight.toString()
    }


            }