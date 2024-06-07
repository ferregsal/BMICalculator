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
import com.google.android.material.slider.Slider
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.MotionEvent


class MainActivity : AppCompatActivity() {

    //lateinit var heightEditText: EditText
    lateinit var weightTextView: TextView
    lateinit var resultTextView: TextView
    lateinit var resultCalculate: TextView
    lateinit var calculateButton: Button
    lateinit var minusButton: Button
    lateinit var addButton: Button
    lateinit var checkLevel:TextView
    lateinit var seekBar: SeekBar
    lateinit var heightTextView: TextView
    lateinit var heightSlider : Slider
    var height:Int=150
    var weight:Int=70
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var decrementRunnable: Runnable
    private lateinit var incrementRunnable: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // heightEditText = findViewById(R.id.heightEditText)
        heightTextView = findViewById(R.id.heightTextView)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.addButton)
        weightTextView = findViewById(R.id.weightTextView)
        resultTextView = findViewById(R.id.resultTextView)

        resultCalculate = findViewById(R.id.resultCalculate)

        calculateButton = findViewById(R.id.calculateButton)
        checkLevel = findViewById(R.id.checkLevel)
        seekBar =findViewById(R.id.seekBar)
        heightSlider =findViewById(R.id.heightSlider)
        heightSlider.value=height.toFloat()
        setHeight()
        setWeight()

        heightSlider.addOnChangeListener { _,value,_->

            height=value.toInt()
            setHeight()
        }


        // Incrementar el peso
        addButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    handler.post(incrementRunnable)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    handler.removeCallbacks(incrementRunnable)
                }
            }
            true
        }

        // Decrementar el peso
        minusButton.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    handler.post(decrementRunnable)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    handler.removeCallbacks(decrementRunnable)
                }
            }
            true
        }

        decrementRunnable = Runnable {
            if (weight > 0) {
                weight--
                setWeight()
                seekBar.progress = weight
                handler.postDelayed(decrementRunnable, 100) // 100 ms delay
            }
        }

        incrementRunnable = Runnable {
            weight++
            setWeight()
            seekBar.progress = weight
            handler.postDelayed(incrementRunnable, 100) // 100 ms delay
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weight = progress
                weightTextView.text = progress.toString()+" Kg"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementar nada aquí para este caso
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementar nada aquí para este caso
            }
        })

        calculateButton.setOnClickListener{
            height=height.toString().toInt()
            val result = weight / (height/100f).pow(2)
            resultCalculate.text = "%.2f".format(result)
            when (result){
                in (1f..18.4f) -> {
                    checkLevel.text="Insuficiente"
                    checkLevel.setTextColor(Color.BLUE)
                    resultCalculate.setTextColor(Color.BLUE)


                }
                in (18.5f..24.9f) -> {
                    checkLevel.text="Normal"
                    checkLevel.setTextColor(Color.GREEN)
                    resultCalculate.setTextColor(Color.GREEN)
                }
                in (25f..29f) -> {
                    checkLevel.text="Pre-obesidad"
                    checkLevel.setTextColor(Color.YELLOW)
                    resultCalculate.setTextColor(Color.YELLOW)
                }
                in (30f..50f) -> {
                    checkLevel.text="Obesidad"
                    checkLevel.setTextColor(Color.RED)
                    resultCalculate.setTextColor(Color.RED)
                }
                else -> {
                    checkLevel.text="Peligro!!"
                    checkLevel.setTextColor(Color.MAGENTA)
                    resultCalculate.setTextColor(Color.MAGENTA)
                }
            }
        }
    }
    fun setHeight(){
        //heightEditText.setText(height.toString())
        heightTextView.setText(height.toString()+" cm")
    }
    fun setWeight(){
        weightTextView.text = weight.toString()+" Kg"
    }


            }