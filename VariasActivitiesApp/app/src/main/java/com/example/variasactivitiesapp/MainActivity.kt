package com.example.variasactivitiesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.variasactivitiesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {

            var usernameInserido = binding.editUsername.text.toString()
            var passwordInserida = binding.editPassword.text.toString()

            if (usernameInserido.equals("quimCsMaster64") && passwordInserida.equals("quimrei")) {

                // LOGIN VÁLIDO - Redirecionar para o menu
                val i = Intent(this, CsDropsActivity::class.java)
                startActivity(i)

            } else {
                // LOGIN INVÁLIDO
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                Toast.makeText(applicationContext, "Credenciais Inválidas", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }
}