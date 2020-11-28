package br.com.house.digital.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.house.digital.databinding.ActivityLoginBinding
import br.com.house.digital.databinding.ActivityMainBinding
import br.com.house.digital.databinding.ActivitySplashScreenBinding

class ActivityMain : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}