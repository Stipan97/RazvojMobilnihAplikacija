package com.example.lv2_1_inspiringperson

import kotlin.random.Random

object InspiringPeople {
    var inspiringPeople: ArrayList<InspiringPerson> = ArrayList()

    fun rndQuote(position: Int): String{
        var rnd = Random.nextInt(0, inspiringPeople[position].ipQuotes.size)
        return inspiringPeople[position].ipQuotes[rnd]
    }
}