package com.example.capybararng

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capybararng.databinding.ActivityVendingBinding
import kotlin.random.Random

class VendingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityVendingBinding.inflate(layoutInflater)
    }

    private var credits = 0
    private var isAnimating = false
    private var dailyUsed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intentAnterior = intent
        var username = intentAnterior.extras?.getString("username");
        var creditosComprados = intentAnterior.extras?.getString("creditos");

        if (creditosComprados != null) {
            credits = creditosComprados.toInt()
        }

        binding.textUsername.text = "$username"

        binding.textCredits.text = "$credits C"


        binding.buttonDaily.setOnClickListener {
            if (!dailyUsed) { // dailyUsed == false

                if (isAnimating) { // isAnimating == true
                    // Se entrar, quer dizer que estavamos a meio de outra animação
                    // Vamos mostrar um Toast, e parar o método setOnClickListener
                    Toast.makeText(this, "Aguarda a animação atual!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Como este é o botão daily, ao primeiro clique, passamos a variavel dailyUser para true
                dailyUsed = true

                // Disable botão Daily
                binding.buttonDaily.isEnabled = false
                binding.buttonDaily.alpha = 0.5f

                // Iniciamos uma animação, passamos a variável isAnimating para true
                isAnimating = true

                // Disable botões 20C (common) e 100C (rare)

                // isEnabled permite ao utilizador clicar no botão
                binding.buttonCommon.isEnabled = false
                binding.buttonRare.isEnabled = false

                // alpha é a opacidade (1.0f é 100% visivel || 0.5f é 50% visivel)
                binding.buttonCommon.alpha = 0.5f
                binding.buttonRare.alpha = 0.5f

                // Trocar a imagem da vending normal para vending shuffle
                binding.imageCapybara.setImageResource(R.drawable.vending_shuffle)

                Handler(Looper.getMainLooper()).postDelayed({

                    // Aleatorizar as imagens
                    var categoriaAleatoria = Random.nextInt(1, 101)

                    var randomCapybara = R.drawable.capybara_01

                    if (categoriaAleatoria >= 1 && categoriaAleatoria <= 70) { // DAILY
                        randomCapybara = when (Random.nextInt(4)) {
                            0 -> R.drawable.capybara_01
                            1 -> R.drawable.capybara_daily_01
                            2 -> R.drawable.capybara_daily_02
                            else-> R.drawable.capybara_daily_03

                        }
                        Toast.makeText(this, "Ganhaste uma capivara Daily!", Toast.LENGTH_LONG)
                            .show()
                    }

                    if (categoriaAleatoria >= 71 && categoriaAleatoria <= 94) { // COMMON
                        randomCapybara = when (Random.nextInt(10)) {
                            0 -> R.drawable.capybara_01
                            1 -> R.drawable.capybara_comum_01
                            2 -> R.drawable.capybara_comum_02
                            3 -> R.drawable.capybara_comum_03
                            4 -> R.drawable.capybara_comum_04
                            5 -> R.drawable.capybara_comum_05
                            6 -> R.drawable.capybara_comum_06
                            7 -> R.drawable.capybara_comum_07
                            8 -> R.drawable.capybara_comum_08
                           else-> R.drawable.capybara_comum_09
                        }
                        Toast.makeText(this, "Ganhaste uma capivara Common!", Toast.LENGTH_LONG)
                            .show()
                    }

                    if (categoriaAleatoria >= 95 && categoriaAleatoria <= 100) { // RARE
                        randomCapybara = when (Random.nextInt(7)) {
                            0 -> R.drawable.capybara_01
                            1 -> R.drawable.capybara_rare_01
                            2 -> R.drawable.capybara_rare_02
                            3 -> R.drawable.capybara_rare_03
                            4 -> R.drawable.capybara_rare_04
                            5 -> R.drawable.capybara_rare_05
                            else -> R.drawable.capybara_rare_06
                        }
                        Toast.makeText(this, "Ganhaste uma capivara Rare!", Toast.LENGTH_LONG)
                            .show()

                    }

                    // Trocamos efetivamente a imagem para a que está na variável randomCapybara
                    binding.imageCapybara.setImageResource(randomCapybara)


                    // Passados 5 segundos
                    Handler(Looper.getMainLooper()).postDelayed({

                        // Trocar imagem para máquina de vending
                        binding.imageCapybara.setImageResource(R.drawable.vending_machine)

                        isAnimating = false
                        binding.buttonCommon.isEnabled = true
                        binding.buttonRare.isEnabled = true
                        binding.buttonCommon.alpha = 1.0f
                        binding.buttonRare.alpha = 1.0f
                    }, 5000)

                }, 3000)

            } else {
                Toast.makeText(this, "Daily já foi usado hoje!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCommon.setOnClickListener {

            if (isAnimating) {
                Toast.makeText(this, "Aguarda a animação atual!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (credits < 20) {
                Toast.makeText(this, "Créditos insuficientes!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            credits = credits - 20
            binding.textCredits.text = "$credits C"

            isAnimating = true
            binding.buttonDaily.isEnabled = false
            binding.buttonCommon.isEnabled = false
            binding.buttonRare.isEnabled = false
            binding.buttonCommon.alpha = 0.5f
            binding.buttonRare.alpha = 0.5f
            if (!dailyUsed) {
                binding.buttonDaily.alpha = 0.5f
            }

            binding.imageCapybara.setImageResource(R.drawable.vending_shuffle)

            Handler(Looper.getMainLooper()).postDelayed({
                // Aleatorizar as imagens
                var categoriaAleatoria = Random.nextInt(1, 101)

                var randomCapybara = R.drawable.capybara_01

                if (categoriaAleatoria >= 1 && categoriaAleatoria <= 10) { // DAILY
                    randomCapybara = when (Random.nextInt(4)) {
                        0 -> R.drawable.capybara_01
                        1 -> R.drawable.capybara_daily_01
                        2 -> R.drawable.capybara_daily_02
                        else-> R.drawable.capybara_daily_03

                    }
                    Toast.makeText(this, "Ganhaste uma capivara Daily!", Toast.LENGTH_LONG).show()
                }

                if (categoriaAleatoria >= 11 && categoriaAleatoria <= 80) { // COMMON
                    randomCapybara = when (Random.nextInt(10)) {
                        0 -> R.drawable.capybara_01
                        1 -> R.drawable.capybara_comum_01
                        2 -> R.drawable.capybara_comum_02
                        3 -> R.drawable.capybara_comum_03
                        4 -> R.drawable.capybara_comum_04
                        5 -> R.drawable.capybara_comum_05
                        6 -> R.drawable.capybara_comum_06
                        7 -> R.drawable.capybara_comum_07
                        8 -> R.drawable.capybara_comum_08
                        else-> R.drawable.capybara_comum_09
                    }
                    Toast.makeText(this, "Ganhaste uma capivara Common!", Toast.LENGTH_LONG).show()
                }

                if (categoriaAleatoria >= 81 && categoriaAleatoria <= 100) { // RARE
                    randomCapybara = when (Random.nextInt(7)) {
                        0 -> R.drawable.capybara_01
                        1 -> R.drawable.capybara_rare_01
                        2 -> R.drawable.capybara_rare_02
                        3 -> R.drawable.capybara_rare_03
                        4 -> R.drawable.capybara_rare_04
                        5 -> R.drawable.capybara_rare_05
                        else -> R.drawable.capybara_rare_06
                    }
                    Toast.makeText(this, "Ganhaste uma capivara Rare!", Toast.LENGTH_LONG).show()

                }

                binding.imageCapybara.setImageResource(randomCapybara)

                Handler(Looper.getMainLooper()).postDelayed({
                    binding.imageCapybara.setImageResource(R.drawable.vending_machine)
                    isAnimating = false
                    binding.buttonCommon.isEnabled = true
                    binding.buttonRare.isEnabled = true
                    binding.buttonCommon.alpha = 1.0f
                    binding.buttonRare.alpha = 1.0f
                    if (!dailyUsed) {
                        binding.buttonDaily.isEnabled = true
                        binding.buttonDaily.alpha = 1.0f
                    }
                }, 5000)

            }, 3000)
        }

        binding.buttonRare.setOnClickListener {

            if (isAnimating) {
                Toast.makeText(this, "Aguarda a animação atual!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (credits < 100) {
                Toast.makeText(this, "Créditos insuficientes!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            credits -= 100
            binding.textCredits.text = "$credits C"

            isAnimating = true
            binding.buttonDaily.isEnabled = false
            binding.buttonCommon.isEnabled = false
            binding.buttonRare.isEnabled = false
            binding.buttonCommon.alpha = 0.5f
            binding.buttonRare.alpha = 0.5f
            if (!dailyUsed) {
                binding.buttonDaily.alpha = 0.5f
            }

            binding.imageCapybara.setImageResource(R.drawable.vending_shuffle)

            Handler(Looper.getMainLooper()).postDelayed({
                // Aleatorizar as imagens
                var categoriaAleatoria = Random.nextInt(1, 101)

                var randomCapybara = R.drawable.capybara_01

                if (categoriaAleatoria >= 1 && categoriaAleatoria <= 5) { // DAILY
                    randomCapybara = when (Random.nextInt(4)) {
                        0 -> R.drawable.capybara_01
                        1 -> R.drawable.capybara_daily_01
                        2 -> R.drawable.capybara_daily_02
                        else-> R.drawable.capybara_daily_03

                    }
                    Toast.makeText(this, "Ganhaste uma capivara Daily!", Toast.LENGTH_LONG).show()
                }

                if (categoriaAleatoria >= 6 && categoriaAleatoria <= 35) { // COMMON
                    randomCapybara = when (Random.nextInt(10)) {
                        0 -> R.drawable.capybara_01
                        1 -> R.drawable.capybara_comum_01
                        2 -> R.drawable.capybara_comum_02
                        3 -> R.drawable.capybara_comum_03
                        4 -> R.drawable.capybara_comum_04
                        5 -> R.drawable.capybara_comum_05
                        6 -> R.drawable.capybara_comum_06
                        7 -> R.drawable.capybara_comum_07
                        8 -> R.drawable.capybara_comum_08
                        else-> R.drawable.capybara_comum_09
                    }
                    Toast.makeText(this, "Ganhaste uma capivara Common!", Toast.LENGTH_LONG).show()
                }
                if (categoriaAleatoria >= 36 && categoriaAleatoria <= 100) { // RARE
                    randomCapybara = when (Random.nextInt(7)) {
                        0 -> R.drawable.capybara_01
                        1 -> R.drawable.capybara_rare_01
                        2 -> R.drawable.capybara_rare_02
                        3 -> R.drawable.capybara_rare_03
                        4 -> R.drawable.capybara_rare_04
                        5 -> R.drawable.capybara_rare_05
                        else -> R.drawable.capybara_rare_06
                    }
                    Toast.makeText(this, "Ganhaste uma capivara Rare!", Toast.LENGTH_LONG).show()

                }

                binding.imageCapybara.setImageResource(randomCapybara)

                Handler(Looper.getMainLooper()).postDelayed({
                    binding.imageCapybara.setImageResource(R.drawable.vending_machine)
                    isAnimating = false
                    binding.buttonCommon.isEnabled = true
                    binding.buttonRare.isEnabled = true
                    binding.buttonCommon.alpha = 1.0f
                    binding.buttonRare.alpha = 1.0f
                    if (!dailyUsed) {
                        binding.buttonDaily.isEnabled = true
                        binding.buttonDaily.alpha = 1.0f
                    }
                }, 5000)

            }, 3000)
        }
    }
}