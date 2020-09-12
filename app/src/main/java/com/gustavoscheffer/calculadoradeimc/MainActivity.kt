package com.gustavoscheffer.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_calcular).setOnClickListener {CalcularIMC()}
    }

    private fun CalcularIMC(){
        var altura = findViewById<EditText>(R.id.input_altura).text.toString().toDoubleOrNull();
        var peso = findViewById<EditText>(R.id.input_peso).text.toString().toDoubleOrNull();
        var imc : Double = 0.0;

        fun checkIMC(value: Double): String{
            return when(value){
                in 1..17 -> "Muito abaixo do peso."
                in 17.0..18.49 -> "Abaixo do peso."
                in 18.5..24.99 -> "Peso normal."
                in 25.0..29.99 -> "Acima do peso."
                in 30.0..34.99 -> "Obesidade I."
                in 35.0..39.99 -> "Obesidade II."
                in 0..0 -> "Insira sua altura e peso corretamente"
                else -> "Obesidade III."
            }
        }

        if(altura != null && peso != null){
            imc = (peso / (altura * altura)).round(2);
        }
        findViewById<TextView>(R.id.text_resultado).text = imc.toString();
        findViewById<TextView>(R.id.classificacao_imc).text = checkIMC(imc)
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}