package com.example.variasactivitiesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.variasactivitiesapp.databinding.ActivityCsDropsBinding

class CsDropsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCsDropsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageCaseFree.setOnClickListener {
            val i = Intent(this, FreeCaseActivity::class.java)
            startActivity(i)
        }

    }
}