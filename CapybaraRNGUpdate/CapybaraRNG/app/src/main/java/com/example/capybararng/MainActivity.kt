package com.example.capybararng

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capybararng.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {

            var usernameInput = binding.editUsername.text.toString()
            var passwordInput = binding.editPassword.text.toString()

            if (passwordInput.equals("123456")) {

                // Login válido - avançar
                var i = Intent(this, PaymentActivity::class.java)

                i.putExtra("username", usernameInput)

                startActivity(i)

            } else {

                // Login inválido

                // Limpar campos
                binding.editUsername.setText("")
                binding.editPassword.setText("")

                // Toast
                Toast.makeText(applicationContext, "Login Inválido", Toast.LENGTH_SHORT).show()

            }
        }

    }
}