package br.com.house.digital.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.house.digital.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this@ActivityLogin, ActivityMain::class.java))
            finish()
        }
    }
}