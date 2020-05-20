package com.example.lv2_1_inspiringperson

import android.net.Uri
import kotlin.collections.ArrayList

data class InspiringPerson(val image: Uri?, val name: String, val surname: String, val birthDate: String, val deathDate: String, val description: String, val quotes: String) {
    var ipQuotes = ArrayList<String>()

    init {
        var i = 0
        var tmpString = ""
        while (quotes[i] != '/' && quotes[i+1] != '0') {
            if (quotes[i] == '"' && quotes[i+1] != ',') {
                i++
                while (quotes[i] != '"' && quotes[i+1] != ',') {
                    tmpString += quotes[i]
                    i++
                }
                ipQuotes.add(tmpString)
                tmpString = ""
            }
            i++
        }
    }
}