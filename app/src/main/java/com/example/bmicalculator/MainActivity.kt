package com.example.bmicalculator



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow
import android.util.Log
class MainActivity : AppCompatActivity() {

    lateinit var heightEditText: EditText
    lateinit var weightTextView: TextView
    lateinit var resultTextView: TextView
    lateinit var resultCalculate: TextView
    lateinit var calculateButton: Button
    lateinit var minusButton: Button
    lateinit var addButton: Button
    lateinit var checkLevel:TextView

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
        Log.i("IMC","textview")
        resultCalculate = findViewById(R.id.resultCalculate)
        Log.i("IMC","calculate")
        calculateButton = findViewById(R.id.calculateButton)
        checkLevel = findViewById(R.id.checkLevel)
        setHeight()
        setWeight()
        setCheckLevel()
        minusButton.setOnClickListener{
            weight--
            setWeight()
            Log.i("IMC","He reducido el peso")
        }
        addButton.setOnClickListener{
            weight++
            setWeight()
            Log.i("IMC","He aumentado el peso")
        }
        calculateButton.setOnClickListener{
            height=heightEditText.text.toString().toInt()
            val result = weight / (height/100f).pow(2)
            resultCalculate.text = result.toString()
            Log.i("IMC","He calculado el resultado")

        }
    }
    fun setHeight(){
        heightEditText.setText(height.toString())
    }
    fun setWeight(){
        weightTextView.text = weight.toString()
    }
    fun setCheckLevel(){

    }

            }