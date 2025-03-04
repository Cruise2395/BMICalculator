package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import java.util.Locale
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    // propiedades que hemos creado en el layout
    lateinit var heightTextView: TextView
    lateinit var heightSlider: Slider

    lateinit var weightTextView: TextView
    lateinit var weightAddButton: Button
    lateinit var weightMinusButton: Button

    lateinit var calculateButton: Button
    lateinit var resultTextView: TextView
    lateinit var descriptionTextView: TextView

    var height = 170.0f
    var weight = 75.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        heightTextView = findViewById(R.id.heightTextView)
        heightSlider = findViewById(R.id.heightSlider)

        weightTextView = findViewById(R.id.weightTextView)
        weightMinusButton = findViewById(R.id.weightMinusButton)
        weightAddButton = findViewById(R.id.weightAddButton)

        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        heightSlider.addOnChangeListener { slider, value, fromUser ->
            height = value
            heightTextView.text = "${height.toInt()}"
        }

        weightAddButton.setOnClickListener {
            weight++
            weightTextView.text = "${weight.toInt()}"
        }
        weightMinusButton.setOnClickListener {
            weight--
            weightMinusButton.text = "${weight.toInt()}"
        }
        calculateButton.setOnClickListener {
            val result = weight / (height / 100).pow(2)

            resultTextView.text = String.format(Locale.getDefault(), "%.2f", result)

            var colorId = 0
            var textId = 0
            when (result){
                in 0f..<18.5f -> {
                    colorId = R.color.imc_underweight
                    textId = R.string.imc_underweight
                }
                in 0f..<18.5f -> {
                    colorId = R.color.imc_normal
                    textId = R.string.imc_normal
                }
                in 25f..<30f-> {
                    colorId = R.color.imc_overweight
                    textId = R.string.imc_overweight
                }
                in 30f..35f -> {
                    colorId = R.color.imc_obesity1
                    textId = R.string.imc_obesity1
                }
                in 30f..35f -> {
                    colorId = R.color.imc_obesity2
                    textId = R.string.imc_obesity2
                }
                else -> {
                    colorId = R.color.imc_obesity3
                    textId = R.string.imc_obesity3
                }
            }
            resultTextView.setTextColor(getColor(colorId))
            descriptionTextView.setTextColor(getColor(colorId))
            descriptionTextView.text = getString(textId)
        }
    }
}