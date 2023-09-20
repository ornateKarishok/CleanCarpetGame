package com.example.cleancarpetgame.game.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cleancarpetgame.R
import com.example.cleancarpetgame.databinding.ActivityGameBinding
import com.example.cleancarpetgame.game.viewmodel.GameViewModel
import com.example.cleancarpetgame.result.ui.ResultActivity
import dev.skymansandy.scratchcardlayout.listener.ScratchListener
import dev.skymansandy.scratchcardlayout.ui.ScratchCardLayout
import kotlin.system.exitProcess


class GameActivity : AppCompatActivity(), ScratchListener {
    private lateinit var binding: ActivityGameBinding
    private var vmGame: GameViewModel = GameViewModel()
    private var isExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        binding.viewmodel = vmGame
        binding.scratchCard.setScratchListener(this)

        onBackPressedDispatcher.addCallback(
            this,
            backPressedCallback
        )
        vmGame.getRandomImageOfCarpet()
        observe()
    }

    private fun observe() {
        vmGame.getEventImageOfCarpet().observe(this) { image ->
            if (image != null) {
                binding.carpetIv.setImageResource(image)
            }
        }
    }

    override fun onScratchComplete() {
        val nextActivity = Intent(this, ResultActivity::class.java)
        startActivity(nextActivity)

        finish()
    }

    override fun onScratchProgress(
        scratchCardLayout: ScratchCardLayout,
        atLeastScratchedPercent: Int
    ) {
    }

    override fun onScratchStarted() {
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
