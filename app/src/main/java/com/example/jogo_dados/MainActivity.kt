package com.example.jogo_dados

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var dice1ImageView: ImageView
    private lateinit var dice2ImageView: ImageView
    private lateinit var resultTextView: TextView
    private lateinit var scoreTextView: TextView
    private var totalGames = 0
    private var totalWins = 0

    // Definindo as strings como constantes
    private val rollButtonText = "Jogar"
    private val initialResultText = "Resultado: Jogue para começar!"
    private val winMessageTemplate = "Você ganhou! A soma dos dados é %d."
    private val loseMessageTemplate = "Você perdeu! A soma dos dados é %d."
    private val scoreMessageTemplate = "Score: %d/%d = %d%%"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuração da interface do usuário
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
            gravity = android.view.Gravity.CENTER
            setBackgroundColor(Color.WHITE)
        }

        // Imagem do primeiro dado
        dice1ImageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(400, 400)
            setImageResource(R.drawable.dado1) // Começa mostrando 'dado1'
        }

        // Imagem do segundo dado
        dice2ImageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(400, 400)
            setImageResource(R.drawable.dado1) // Começa mostrando 'dado1'
        }

        // Botão para jogar
        val rollButton = Button(this).apply {
            text = rollButtonText
            setOnClickListener { rollDice() }
        }

        // Texto para mostrar o resultado
        resultTextView = TextView(this).apply {
            textSize = 18f
            setTextColor(Color.BLACK)
            text = initialResultText
        }

        // Texto para mostrar o placar
        scoreTextView = TextView(this).apply {
            textSize = 16f
            setTextColor(Color.BLACK)
            text = String.format(Locale.getDefault(), scoreMessageTemplate, 0, 0, 0)
        }

        // Adicionando as views ao layout
        mainLayout.addView(dice1ImageView)
        mainLayout.addView(dice2ImageView)
        mainLayout.addView(rollButton)
        mainLayout.addView(resultTextView)
        mainLayout.addView(scoreTextView)

        setContentView(mainLayout)
    }

    private fun rollDice() {
        // Gera dois números aleatórios entre 1 e 6
        val dice1 = Random.nextInt(1, 7)
        val dice2 = Random.nextInt(1, 7)

        // Atualiza as imagens dos dados
        updateDiceImage(dice1, dice1ImageView)
        updateDiceImage(dice2, dice2ImageView)

        // Verifica o resultado
        val sum = dice1 + dice2
        val message = if (sum == 7 || sum == 11) {
            totalWins++
            String.format(Locale.getDefault(), winMessageTemplate, sum)
        } else {
            String.format(Locale.getDefault(), loseMessageTemplate, sum)
        }

        totalGames++
        val winPercentage = (totalWins.toDouble() / totalGames * 100).toInt()
        resultTextView.text = message
        scoreTextView.text = String.format(Locale.getDefault(), scoreMessageTemplate, totalWins, totalGames, winPercentage)
    }

    private fun updateDiceImage(diceValue: Int, imageView: ImageView) {
        val drawableResource = when (diceValue) {
            1 -> R.drawable.dado1
            2 -> R.drawable.dado2
            3 -> R.drawable.dado3
            4 -> R.drawable.dado4
            5 -> R.drawable.dado5
            6 -> R.drawable.dado6
            else -> R.drawable.dado1
        }
        imageView.setImageResource(drawableResource)
    }
}
