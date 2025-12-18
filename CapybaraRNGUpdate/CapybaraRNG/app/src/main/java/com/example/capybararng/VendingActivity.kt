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

    // Define os conjuntos de capivaras para cada raridade
    private val dailyCapybaras = listOf(
        R.drawable.capybara_01,
        R.drawable.daily_01,
        R.drawable.daily_02,
        R.drawable.daily_03,
        R.drawable.daily_04,
        R.drawable.daily_05
    )

    private val commonCapybaras = listOf(
        R.drawable.capybara_01,
        R.drawable.comum_01,
        R.drawable.comum_02,
        R.drawable.comum_03,
        R.drawable.comum_04,
        R.drawable.comum_05,
        R.drawable.comum_06,
        R.drawable.comum_07,
        R.drawable.comum_08,
        R.drawable.comum_09,
        R.drawable.comum_10,
        R.drawable.comum_11,
        R.drawable.comum_12,
        R.drawable.comum_13,
        R.drawable.comum_14,
        R.drawable.comum_15,
        R.drawable.comum_16,
        R.drawable.comum_17,
        R.drawable.comum_18,
        R.drawable.comum_19,
        R.drawable.comum_20,
        R.drawable.comum_21,
        R.drawable.comum_22,
        R.drawable.comum_23,
        R.drawable.comum_24,
        R.drawable.comum_25,
        R.drawable.comum_26
    )

    private val rareCapybaras = listOf(
        R.drawable.rare_01,
        R.drawable.rare_02,
        R.drawable.rare_03,
        R.drawable.rare_04,
        R.drawable.rare_05,
        R.drawable.rare_06,
        R.drawable.rare_07,
        R.drawable.rare_08,
        R.drawable.rare_09,
        R.drawable.rare_10,
        R.drawable.rare_11,
        R.drawable.rare_12,
        R.drawable.rare_13,
        R.drawable.rare_14
    )

    private val ultraRareCapybaras = listOf(
        R.drawable.ultra_01,
        R.drawable.ultra_02,
        R.drawable.ultra_03,
        R.drawable.ultra_04,
        R.drawable.ultra_05,
        R.drawable.ultra_06,
        R.drawable.ultra_07,
        R.drawable.ultra_08,
        R.drawable.ultra_09,
        R.drawable.ultra_10,
        R.drawable.ultra_11
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Recebe dados da Intent
        val intentAnterior = intent
        var username = intentAnterior.extras?.getString("username")
        var creditosComprados = intentAnterior.extras?.getString("creditos")

        if (creditosComprados != null) {
            credits = creditosComprados.toInt()
        }

        binding.textUsername.text = "$username"
        updateCreditsDisplay()


        setupButtons()
        updateCreditsDisplay()
    }

    private fun setupButtons() {
        binding.buttonDaily.setOnClickListener {
            if (!dailyUsed) {
                performGacha(0, "Daily")
                dailyUsed = true
                binding.buttonDaily.isEnabled = false
                binding.buttonDaily.alpha = 0.5f
            } else {
                Toast.makeText(this, "Daily já foi usado hoje!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCommon.setOnClickListener {
            performGacha(20, "Common")
        }

        binding.buttonRare.setOnClickListener {
            performGacha(100, "Rare")
        }
    }

    private fun performGacha(cost: Int, gachaType: String) {
        // Verifica se já está a animar
        if (isAnimating) {
            Toast.makeText(this, "Aguarda a animação atual!", Toast.LENGTH_SHORT).show()
            return
        }

        // Verifica se tem créditos suficientes
        if (cost > 0 && credits < cost) {
            Toast.makeText(this, "Créditos insuficientes!", Toast.LENGTH_SHORT).show()
            return
        }

        // Deduz os créditos
        if (cost > 0) {
            credits -= cost
            updateCreditsDisplay()
        }

        // Desativa os botões durante a animação
        isAnimating = true
        setButtonsEnabled(false)

        // Fase 1: Mostra a vending a fazer shuffle
        binding.imageCapybara.setImageResource(R.drawable.vending_shuffle)

        // Fase 2: Após 2 segundos, mostra a capivara aleatória
        Handler(Looper.getMainLooper()).postDelayed({
            val (capybara, rarity) = getRandomCapybaraByProbability(gachaType)
            binding.imageCapybara.setImageResource(capybara)

            Toast.makeText(this, "Ganhaste uma capivara $rarity!", Toast.LENGTH_LONG).show()

            // Fase 3: Após mais 4 segundos, volta à vending normal
            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageCapybara.setImageResource(R.drawable.vending_machine)
                isAnimating = false
                setButtonsEnabled(true)
            }, 4000)

        }, 2000)
    }

    private fun getRandomCapybaraByProbability(gachaType: String): Pair<Int, String> {
        val roll = Random.nextInt(1, 101) // 1 a 100

        return when (gachaType) {
            "Daily" -> {
                when {
                    roll <= 70 -> {
                        // 70% Daily
                        Pair(dailyCapybaras.random(), "Daily")
                    }

                    roll <= 95 -> {
                        // 25% Common
                        Pair(commonCapybaras.random(), "Common")
                    }

                    else -> {
                        // 5% Rare
                        Pair(rareCapybaras.random(), "Rare")
                    }
                }
            }

            "Common" -> {
                when {
                    roll <= 10 -> {
                        // 10% Daily
                        Pair(dailyCapybaras.random(), "Daily")
                    }

                    roll <= 80 -> {
                        // 70% Common
                        Pair(commonCapybaras.random(), "Common")
                    }

                    roll <= 98 -> {
                        // 18% Rare
                        Pair(rareCapybaras.random(), "Rare")
                    }

                    else -> {
                        // 2% Ultra Rare
                        Pair(ultraRareCapybaras.random(), "Ultra Rare ✨")
                    }
                }
            }

            "Rare" -> {
                when {
                    roll <= 5 -> {
                        // 5% Daily
                        Pair(dailyCapybaras.random(), "Daily")
                    }

                    roll <= 15 -> {
                        // 10% Common
                        Pair(commonCapybaras.random(), "Common")
                    }

                    roll <= 85 -> {
                        // 70% Rare
                        Pair(rareCapybaras.random(), "Rare")
                    }

                    else -> {
                        // 15% Ultra Rare
                        Pair(ultraRareCapybaras.random(), "Ultra Rare ✨")
                    }
                }
            }

            else -> Pair(dailyCapybaras.random(), "Daily")
        }
    }

    private fun updateCreditsDisplay() {
        binding.textCredits.text = "$credits C"
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        binding.buttonCommon.isEnabled = enabled
        binding.buttonRare.isEnabled = enabled

        // Daily só é reativado se ainda não foi usado
        if (!dailyUsed) {
            binding.buttonDaily.isEnabled = enabled
        }

        // Efeito visual

//        if(enabled==true){
//            binding.buttonCommon.alpha=1.0f
//            binding.buttonRare.alpha=1.0f
//        }else{
//            binding.buttonCommon.alpha=0.5f
//            binding.buttonRare.alpha=0.5f
//        }

        binding.buttonCommon.alpha = if (enabled) 1.0f else 0.5f
        binding.buttonRare.alpha = if (enabled) 1.0f else 0.5f
    }
}