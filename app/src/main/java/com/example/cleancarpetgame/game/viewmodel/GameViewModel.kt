package com.example.cleancarpetgame.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleancarpetgame.R
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val imageOfCarpet = MutableLiveData<Int>()

    fun getRandomImageOfCarpet() {
        val carpetImages = arrayOf(
            R.drawable.carpet1,
            R.drawable.carpet2,
            R.drawable.carpet3,
            R.drawable.carpet4,
            R.drawable.carpet5
        )

        val randomIndex = Random.nextInt(carpetImages.size)

        imageOfCarpet.value = carpetImages[randomIndex]
    }

    fun getEventImageOfCarpet(): LiveData<Int> {
        return imageOfCarpet
    }

}