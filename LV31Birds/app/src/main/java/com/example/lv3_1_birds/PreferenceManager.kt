package com.example.lv3_1_birds

import android.content.Context
import android.widget.Toast

class PreferenceManager {
    companion object{
        const val PREFS_FILE = "MyPreferences"
        const val PREFS_KEY_HAWK = "Hawk"
        const val PREFS_KEY_EAGLE = "Eagle"
        const val PREFS_KEY_VULTURE = "Vulture"
        const val PREFS_KEY_SPARROW = "Sparrow"
        const val PREFS_KEY_LAST_COLOR = "bgColor"
    }

    fun saveBirds(hawk: Int, eagle: Int, vulture: Int, sparrow: Int, color: Int, myContext: Context) {
        val sharedPreferences = myContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(PREFS_KEY_HAWK, hawk)
        editor.putInt(PREFS_KEY_EAGLE, eagle)
        editor.putInt(PREFS_KEY_VULTURE, vulture)
        editor.putInt(PREFS_KEY_SPARROW, sparrow)
        editor.putInt(PREFS_KEY_LAST_COLOR, color)
        editor.apply()
    }

    fun retrieveBirds(i: Int, myContext: Context): Int {
        val sharedPreferences = myContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        when(i) {
            1 -> return sharedPreferences.getInt(PREFS_KEY_HAWK, 0)
            2 -> return sharedPreferences.getInt(PREFS_KEY_EAGLE, 0)
            3 -> return sharedPreferences.getInt(PREFS_KEY_VULTURE, 0)
            4 -> return sharedPreferences.getInt(PREFS_KEY_SPARROW, 0)
            5 -> return sharedPreferences.getInt(PREFS_KEY_LAST_COLOR, R.color.white)
            else -> {
                Toast.makeText(myContext, "Something went wrong!", Toast.LENGTH_SHORT).show()
                return 0
            }
        }
    }
}