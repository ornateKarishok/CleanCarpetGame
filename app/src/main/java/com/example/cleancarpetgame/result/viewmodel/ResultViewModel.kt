package com.example.cleancarpetgame.result.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleancarpetgame.game.ui.GameActivity
import com.example.cleancarpetgame.menu.ui.MenuActivity

class ResultViewModel : ViewModel() {
    private val eventNavigate = MutableLiveData<Class<*>>()
    fun onContinueButtonClick() {
        eventNavigate.value = GameActivity::class.java
    }

    fun onHomeButtonClick() {
        eventNavigate.value = MenuActivity::class.java
    }

    fun getEventNavigate(): LiveData<Class<*>?> {
        return eventNavigate
    }

}

