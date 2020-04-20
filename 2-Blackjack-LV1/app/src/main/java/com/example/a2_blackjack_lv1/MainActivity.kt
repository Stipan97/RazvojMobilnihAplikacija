package com.example.a2_blackjack_lv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val card = Card()
    private var compResult = 0
    private var playerResult = 0
    private var compCards = arrayListOf<String>()
    private var playerCards = arrayListOf<String>()
    private var asDoubleCheckPlayer = false
    private var asDoubleCheckComp = false
    private var asCardOneFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCompResult: TextView = findViewById(R.id.tvCompResult)
        val tvCompCards: TextView = findViewById(R.id.tvCompCards)
        val tvPlayerResult: TextView = findViewById(R.id.tvPlayerResult)
        val tvPlayerCards: TextView = findViewById(R.id.tvPlayerCards)

        val hitButton: Button = findViewById(R.id.btnHit)
        val dealButton: Button = findViewById(R.id.btnDeal)
        dealButton.setOnClickListener{
            initialDeal()
            setInitialTextViews(tvCompResult, tvCompCards, tvPlayerResult, tvPlayerCards)
            dealButton.isEnabled = false
            hitButton.isEnabled = true
            asDoubleCheckComp = false
            asDoubleCheckPlayer = false
            if (checkResultDeal()) {
                hitButton.isEnabled = false
                dealButton.isEnabled = true
            }
        }

        hitButton.setOnClickListener {
            addCard(true)
            if (checkResultHit()) {
                hitButton.isEnabled = false
                dealButton.isEnabled = true
            }
            addCardInView(tvPlayerResult, tvPlayerCards)
        }

        val stopButton: Button = findViewById(R.id.btnStop)
        stopButton.setOnClickListener {
            hitButton.isEnabled = false
            asCardOneFlag = false
            dealerPlay()
            if(checkResult()) {
                dealButton.isEnabled = true
            }
        }
    }

    private fun dealerPlay() {
        while (compResult < 17) {
            addCard(false)
            if (!asDoubleCheckComp && !asCardOneFlag) {
                asCardDoubleCheck(compCards, 2)
            }
            addCardInViewComp(tvCompResult, tvCompCards)
        }
    }

    private fun addCardInViewComp(tvCompResult: TextView, tvCompCards: TextView) {
        tvCompResult.text = compResult.toString()
        tvCompCards.text = ""
        tvCompCards.text = cardsInString(tvCompCards, compCards)
    }

    private fun checkResult(): Boolean {
        if (compResult == 21) {
            Toast.makeText(applicationContext, "Lose!", Toast.LENGTH_SHORT).show()
            return true
        } else if (compResult > 21) {
            Toast.makeText(applicationContext, "Win!", Toast.LENGTH_SHORT).show()
            return true
        } else if (compResult < playerResult) {
            Toast.makeText(applicationContext, "Win!", Toast.LENGTH_SHORT).show()
            return true
        } else if (compResult > playerResult) {
            Toast.makeText(applicationContext, "Lose!", Toast.LENGTH_SHORT).show()
            return true
        } else {
            Toast.makeText(applicationContext, "Tie!", Toast.LENGTH_SHORT).show()
            return true
        }
    }

    private fun checkResultDeal(): Boolean {
        if (playerResult == 21) {
            Toast.makeText(applicationContext, "Win!", Toast.LENGTH_SHORT).show()
            return true
        } else {
            return false
        }
    }

    private fun addCardInView(tvPlayerResult: TextView, tvPlayerCards: TextView) {
        tvPlayerResult.text = playerResult.toString()
        tvPlayerCards.text = ""
        tvPlayerCards.text = cardsInString(tvPlayerCards, playerCards)
    }

    private fun checkResultHit(): Boolean {
        if (playerResult == 21) {
            Toast.makeText(applicationContext, "Win!", Toast.LENGTH_SHORT).show()
            return true
        } else if (!asDoubleCheckPlayer && !asCardOneFlag){
            asCardDoubleCheck(playerCards, 1)
        }
        if (playerResult > 21) {
            Toast.makeText(applicationContext, "Busted!", Toast.LENGTH_SHORT).show()
            return true
        } else {
            return false
        }
    }

    private fun asCardDoubleCheck(cards: ArrayList<String>, result: Int) {
        var tmpResult = 0
        var i = 0
        if (cards.contains("A")) {
            val index = cards.indexOf("A")
            for (cardTmp in cards){
                if(index != i) {
                    tmpResult += cardNameToPoints(cardTmp)
                }
                i++
            }
            if (tmpResult > 10){
                if(result == 1) {
                    playerResult -= 10
                    asDoubleCheckPlayer = true
                } else {
                    compResult -= 10
                    asDoubleCheckComp = true
                }
            }
        }
    }

    private fun addCard(check: Boolean) {
        card.pickCard()
        if (check){
            playerCards.add(intToCard(card.value))
            playerResult += cardToPoints(card.value, playerResult)
        } else {
            compCards.add(intToCard(card.value))
            compResult += cardToPoints(card.value, compResult)
        }
    }

    private fun setInitialTextViews(tvCompResult: TextView, tvCompCards: TextView, tvPlayerResult: TextView, tvPlayerCards: TextView) {
        tvCompResult.text = compResult.toString()
        tvCompCards.text = ""
        tvCompCards.text = cardsInString(tvCompCards, compCards)

        tvPlayerResult.text = playerResult.toString()
        tvPlayerCards.text = ""
        tvPlayerCards.text = cardsInString(tvPlayerCards, playerCards)
    }

    private fun cardsInString(tvCards: TextView, tmpCards: ArrayList<String>): String {
        val sb = StringBuilder()
        sb.append(tvCards.text.toString())
        for (cardTmp in tmpCards) {
            sb.append(cardTmp).append(" ")
        }
        return sb.toString()
    }

    private fun initialDeal() {
        compCards.clear()
        compResult = 0
        playerCards.clear()
        playerResult = 0
        card.pickCard()
        compCards.add(intToCard(card.value))
        compResult += cardToPoints(card.value, compResult)
        card.pickCard()
        playerCards.add(intToCard(card.value))
        playerResult += cardToPoints(card.value, playerResult)
        card.pickCard()
        playerCards.add(intToCard(card.value))
        playerResult += cardToPoints(card.value, playerResult)
    }

    private fun cardToPoints(value: Int, currentResult: Int): Int {
        var asCard = 11
        if (currentResult + 11 > 21){
            asCard = 1
            asCardOneFlag = true;
        }
        return when(value) {
            1 -> 1
            2 -> 2
            3 -> 3
            4 -> 4
            5 -> 5
            6 -> 6
            7 -> 7
            8 -> 8
            9 -> 9
            10 -> 10
            11 -> 10
            12 -> 10
            13 -> 10
            else -> asCard
        }
    }

    private fun intToCard(value: Int): String {
        return when(value) {
            1 -> "1"
            2 -> "2"
            3 -> "3"
            4 -> "4"
            5 -> "5"
            6 -> "6"
            7 -> "7"
            8 -> "8"
            9 -> "9"
            10 -> "10"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> "A"
        }
    }

    private fun cardNameToPoints(name: String): Int {
        return when(name) {
            "1" -> 1
            "2" -> 2
            "3" -> 3
            "4" -> 4
            "5" -> 5
            "6" -> 6
            "7" -> 7
            "8" -> 8
            "9" -> 9
            "10" -> 10
            "J" -> 10
            "Q" -> 10
            else -> 10
        }
    }
}
