package com.example.lv3_1_birds

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var hawkCounter = 0
    var eagleCounter = 0
    var vultureCounter = 0
    var sparrowCounter = 0
    var bgColorLast = R.color.white

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        setupCountersLabels()

    }

    private fun setupCountersLabels() {
        tvFirstBird.text = PreferenceManager().retrieveBirds(1, this).toString()
        tvSecondBird.text = PreferenceManager().retrieveBirds(2, this).toString()
        tvThirdBird.text = PreferenceManager().retrieveBirds(3, this).toString()
        tvFourthBird.text = PreferenceManager().retrieveBirds(4, this).toString()

        layBirds.setBackgroundColor(ContextCompat.getColor(this, PreferenceManager().retrieveBirds(5, this)))
    }

    private fun setupUI() {
        btnHawk.setOnClickListener { incrementBird(1) }
        btnEagle.setOnClickListener { incrementBird(2) }
        btnVulture.setOnClickListener { incrementBird(3) }
        btnSparrow.setOnClickListener { incrementBird(4) }

        btnReset.setOnClickListener { resetCounters() }

        setupCountersLabels()
    }

    private fun resetCounters() {
        hawkCounter = 0
        eagleCounter = 0
        vultureCounter = 0
        sparrowCounter = 0

        bgColorLast = R.color.white

        saveBirdsStatus()
    }

    private fun incrementBird(i: Int) {
        hawkCounter = PreferenceManager().retrieveBirds(1, this)
        eagleCounter = PreferenceManager().retrieveBirds(2, this)
        vultureCounter = PreferenceManager().retrieveBirds(3, this)
        sparrowCounter = PreferenceManager().retrieveBirds(4, this)

        bgColorLast = PreferenceManager().retrieveBirds(5, this)
        when(i) {
            1 -> {
                hawkCounter++
                bgColorLast = R.color.hawk
                layBirds.setBackgroundColor(ContextCompat.getColor(this, bgColorLast))
            }
            2 -> {
                eagleCounter++
                bgColorLast = R.color.eagle
                layBirds.setBackgroundColor(ContextCompat.getColor(this, bgColorLast))
            }
            3 -> {
                vultureCounter++
                bgColorLast = R.color.vulture
                layBirds.setBackgroundColor(ContextCompat.getColor(this, bgColorLast))
            }
            4 -> {
                sparrowCounter++
                bgColorLast = R.color.sparrow
                layBirds.setBackgroundColor(ContextCompat.getColor(this, bgColorLast))
            }
            else -> Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }
        saveBirdsStatus()
    }

    private fun saveBirdsStatus() {
        val preferenceManager = PreferenceManager()
        preferenceManager.saveBirds(hawkCounter, eagleCounter, vultureCounter, sparrowCounter, bgColorLast, this)
        setupCountersLabels()
    }
}