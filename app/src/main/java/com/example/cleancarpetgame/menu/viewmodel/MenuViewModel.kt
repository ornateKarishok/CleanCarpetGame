package com.example.cleancarpetgame.menu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleancarpetgame.GameActivity
import com.example.cleancarpetgame.menu.ui.MenuActivity

class MenuViewModel : ViewModel() {
    private val eventNavigate = MutableLiveData<Class<*>>()
    fun getEventNavigate(): LiveData<Class<*>?> {
        return eventNavigate
    }

    fun onStartGameButtonClick() {
        eventNavigate.value = GameActivity::class.java
    }

    fun onFinishGameButtonClick() {
        eventNavigate.value = MenuActivity::class.java
    }
}