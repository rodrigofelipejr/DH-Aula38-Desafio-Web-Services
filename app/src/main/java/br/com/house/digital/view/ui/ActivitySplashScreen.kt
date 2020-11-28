package br.com.house.digital.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.house.digital.databinding.ActivitySplashScreenBinding

class ActivitySplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.imageViewLogoMarvel.alpha = 0f
        binding.imageViewLogoMarvel.animate().setDuration(1250).alpha(1f)

        binding.imageViewBackground.alpha = 0f
        binding.imageViewBackground.animate().setDuration(1500).alpha(1f).withEndAction {
            startActivity(Intent(this@ActivitySplashScreen, ActivityLogin::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}