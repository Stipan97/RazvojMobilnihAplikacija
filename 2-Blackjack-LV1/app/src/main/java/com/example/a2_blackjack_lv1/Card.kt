package com.example.a2_blackjack_lv1

import java.util.*

class Card {

    var value = 1

    init {

    }

    fun pickCard() {
        value = 1 + (Random().nextInt(13) + 1)
    }
}