package com.example.cleancarpetgame.menu.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cleancarpetgame.GameActivity
import com.example.cleancarpetgame.R
import com.example.cleancarpetgame.databinding.ActivityMenuBinding
import com.example.cleancarpetgame.menu.viewmodel.MenuViewModel
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private var vmMenu: MenuViewModel = MenuViewModel()
    private var isExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)

        onBackPressedDispatcher.addCallback(
            this,
            backPressedCallback
        )
        binding.viewmodel = vmMenu
        binding.executePendingBindings()
        observe()
    }

    private fun observe() {
        vmMenu.getEventNavigate().observe(this) { navigate ->
            if (GameActivity::class.java == navigate) {
                val nextActivity = Intent(this, navigate)
                startActivity(nextActivity)

                finish()

            } else if (MenuActivity::class.java == navigate) {
                closeApp()
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