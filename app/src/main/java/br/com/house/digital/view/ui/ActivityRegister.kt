package br.com.house.digital.view.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import br.com.house.digital.R
import br.com.house.digital.databinding.ActivityLoginBinding
import br.com.house.digital.databinding.ActivityRegisterBinding

class ActivityRegister : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            startActivity(Intent(this@ActivityRegister, ActivityLogin::class.java))
            finish()
        }

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.includeConfigToolbar.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTitle("")
        binding.includeConfigToolbar.imageViewLogo.visibility = View.GONE;
        binding.includeConfigToolbar.textViewTitle.text = "Register";
    }
}