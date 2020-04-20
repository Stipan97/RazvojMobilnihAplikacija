package com.example.a1_yamb_lv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import java.io.Console
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val dice = Dice()
    private var rollCounter = 0
    private var dicesValue = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtDices = arrayListOf<TextView>()
        txtDices.add(findViewById(R.id.tvDice1))
        txtDices.add(findViewById(R.id.tvDice2))
        txtDices.add(findViewById(R.id.tvDice3))
        txtDices.add(findViewById(R.id.tvDice4))
        txtDices.add(findViewById(R.id.tvDice5))
        txtDices.add(findViewById(R.id.tvDice6))

        val lockedDices = arrayListOf<CheckBox>()
        lockedDices.add((findViewById(R.id.cbDice1)))
        lockedDices.add((findViewById(R.id.cbDice2)))
        lockedDices.add((findViewById(R.id.cbDice3)))
        lockedDices.add((findViewById(R.id.cbDice4)))
        lockedDices.add((findViewById(R.id.cbDice5)))
        lockedDices.add((findViewById(R.id.cbDice6)))
        checkBoxToggle(lockedDices)

        val rollButton: Button = findViewById(R.id.btnRoll)
        rollButton.setOnClickListener{
            txtDices.forEachIndexed { index, txtDice ->
                if (!lockedDices[index].isChecked) {
                    dice.rollDice()
                    txtDice.text = dice.value.toString()
                }
            }
            rollCounter++
            if (rollCounter == 3){
                rollButton.isEnabled = false
                rollCounter = 0
            } else if (rollCounter == 1){
                checkBoxToggle(lockedDices)
            }
            checkResult(txtDices)
        }

        val resetButton: Button = findViewById(R.id.btnReset)
        resetButton.setOnClickListener{
            rollButton.isEnabled = true
            checkBoxToggle(lockedDices)
            rollCounter = 0
        }
    }

    private fun checkBoxToggle(lockedDices: ArrayList<CheckBox>) {
        for (checkBox in lockedDices){
            checkBox.isEnabled = !checkBox.isEnabled
            if (checkBox.isChecked){
                checkBox.isChecked = false
            }
        }
    }

    private fun checkResult(txtDices: ArrayList<TextView>) {
        dicesValue.clear()
        for (txtDice in txtDices){
            dicesValue.add(txtDice.text.toString().toInt())
        }
        checkPokerAndYamb(dicesValue)
        checkScale(dicesValue)
    }

    private fun checkScale(dicesValue: ArrayList<Int>) {
        dicesValue.sort()
        var counter = 0
        var previousValue = dicesValue[0] - 1
        for (currentValue in dicesValue){
            if (currentValue > previousValue && currentValue == (previousValue + 1)){
                counter++
                previousValue = currentValue
            }
        }
        if (counter >= 5){
            Toast.makeText(applicationContext, "Scale!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPokerAndYamb(dicesValue: ArrayList<Int>) {
        for (diceValue in dicesValue){
            var counter = 0
            for (otherDicesValue in dicesValue){
                if (diceValue == otherDicesValue){
                    counter++;
                }
            }
            if (counter == 4){
                Toast.makeText(applicationContext, "Poker!", Toast.LENGTH_SHORT).show()
            } else if (counter >= 5){
                Toast.makeText(applicationContext, "Yamb!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
