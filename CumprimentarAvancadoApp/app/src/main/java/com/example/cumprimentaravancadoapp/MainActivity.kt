package com.example.cumprimentaravancadoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cumprimentaravancadoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonManha.setOnClickListener {

            var primeiroNome = binding.editPrimeiroNome.text.toString()
            var apelido= binding.editApelido.text.toString()

            if(primeiroNome.isNullOrEmpty() || apelido.isNullOrEmpty()){
                Toast.makeText(applicationContext,"Por favor, preencha os campos todos",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Bom dia $primeiroNome $apelido!",Toast.LENGTH_SHORT).show()
            }

        }

        binding.buttonTarde.setOnClickListener {

            var primeiroNome = binding.editPrimeiroNome.text.toString()
            var apelido= binding.editApelido.text.toString()

            if(primeiroNome.isNullOrEmpty() || apelido.isNullOrEmpty()){
                Toast.makeText(applicationContext,"Por favor, preencha os campos todos",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Boa tarde $primeiroNome $apelido!",Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonNoite.setOnClickListener {

            var primeiroNome = binding.editPrimeiroNome.text.toString()
            var apelido= binding.editApelido.text.toString()

            if(primeiroNome.isNullOrEmpty() || apelido.isNullOrEmpty()){
                Toast.makeText(applicationContext,"Por favor, preencha os campos todos",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Boa noite $primeiroNome $apelido!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}