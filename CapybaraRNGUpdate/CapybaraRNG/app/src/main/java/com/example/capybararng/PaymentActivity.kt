package com.example.capybararng

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.capybararng.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPaymentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intentAnterior = intent
        var usernameLogin = intentAnterior.extras?.getString("username");

        binding.textInstrucoesPagamento.text="$usernameLogin Insira o montante e método de pagamento"

        binding.imagePaypal.setOnClickListener {

            var creditosComprados = binding.editCreditosComprados.text.toString().toInt()

            var dinheiroGasto = creditosComprados / 50

            binding.textTotalPago.text = "PAGO: $dinheiroGasto€"

            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this, VendingActivity::class.java)

                i.putExtra("username", usernameLogin)
                i.putExtra("creditos", creditosComprados.toString())

                startActivity(i)
                finish()
            }, 1500)

        }

        binding.imageMbway.setOnClickListener {

            var creditosComprados = binding.editCreditosComprados.text.toString().toInt()

            var dinheiroGasto = creditosComprados / 50

            binding.textTotalPago.text = "PAGO: $dinheiroGasto€"

            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this, VendingActivity::class.java)
                startActivity(i)
                finish()
            }, 1500)

        }

        binding.imageVisa.setOnClickListener {

            var creditosComprados = binding.editCreditosComprados.text.toString().toInt()

            var dinheiroGasto = (creditosComprados / 50) + 5

            binding.textTotalPago.text = "PAGO: $dinheiroGasto€"

            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this, VendingActivity::class.java)
                startActivity(i)
                finish()
            }, 1500)

        }

        binding.imagePaysafecard.setOnClickListener {

            var creditosComprados = binding.editCreditosComprados.text.toString().toInt()

            var dinheiroGasto = creditosComprados / 50

            binding.textTotalPago.text = "PAGO: $dinheiroGasto€"

            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this, VendingActivity::class.java)
                startActivity(i)
                finish()
            }, 1500)

        }

    }
}