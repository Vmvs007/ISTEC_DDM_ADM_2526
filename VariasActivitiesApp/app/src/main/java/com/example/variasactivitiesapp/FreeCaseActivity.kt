package com.example.variasactivitiesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.variasactivitiesapp.databinding.ActivityFreeCaseBinding

class FreeCaseActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFreeCaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonReturn.setOnClickListener {
            val i = Intent(this, CsDropsActivity::class.java)
            startActivity(i)
        }

    }
}