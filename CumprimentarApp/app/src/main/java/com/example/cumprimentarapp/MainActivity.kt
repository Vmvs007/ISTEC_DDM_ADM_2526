package com.example.cumprimentarapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cumprimentarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonCumprimentar.setOnClickListener {

            // Buscar o nome
            var nomeUtilizador = binding.editNome.text.toString()

            // Trocar o TextView para Olá "nome"
            binding.textOla.text="Olá $nomeUtilizador"
        }


    }
}