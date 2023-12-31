package com.example.cleancarpetgame.result.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cleancarpetgame.R
import com.example.cleancarpetgame.databinding.ActivityResultBinding
import com.example.cleancarpetgame.game.ui.GameActivity
import com.example.cleancarpetgame.menu.ui.MenuActivity
import com.example.cleancarpetgame.result.viewmodel.ResultViewModel
import kotlin.system.exitProcess

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var vmResult: ResultViewModel = ResultViewModel()
    private var isExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        onBackPressedDispatcher.addCallback(
            this,
            backPressedCallback
        )
        binding.viewmodel = vmResult
        observe()
    }

    private fun observe() {
        vmResult.getEventNavigate().observe(this) { navigate ->
            if (GameActivity::class.java == navigate || MenuActivity::class.java == navigate) {
                val nextActivity = Intent(this, navigate)
                startActivity(nextActivity)

                finish()
            }
        }
    }

    private fun closeApp() {
        if (isExitPressedOnce) {
            finishAffinity()
            exitProcess(1)
        }

        this.isExitPressedOnce = true
        Toast.makeText(
            this,
            resources.getString(R.string.please_click_exit_again),
            Toast.LENGTH_SHORT
        ).show()

        Handler(Looper.getMainLooper()).postDelayed({
            isExitPressedOnce = false
        }, 2000)
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            closeApp()
        }
    }
}