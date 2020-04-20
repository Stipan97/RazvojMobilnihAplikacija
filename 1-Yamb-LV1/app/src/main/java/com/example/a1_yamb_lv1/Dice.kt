package com.example.a1_yamb_lv1

import java.util.*

class Dice {

    var value = 1

    init {

    }

    fun rollDice(){
        value = Random().nextInt(6) + 1
    }
}