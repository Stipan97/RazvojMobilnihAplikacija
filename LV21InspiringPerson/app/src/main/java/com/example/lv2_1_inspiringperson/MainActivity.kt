package com.example.lv2_1_inspiringperson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavBar = BottomNavBar(findViewById(R.id.navBar), supportFragmentManager)
        bottomNavBar.handleNavBarItems()
        bottomNavBar.showDefaultFragment()

        //InspiringPeople.inspiringPeople.add(InspiringPerson(File("Download/placeholder.jpeg"),"Nikola", "Tesla", "01/01/1111", "02/02/2222", "Ovo je Nikola Tesla, opis opis", "\"Ovo je prvi citat\", \"Ovo je drugi citat\"/0"))
        //InspiringPeople.inspiringPeople.add(InspiringPerson(File("/sdcard/Download/placeholder.jpeg"),"Nikola1", "Tesla", "01/01/1111", "02/02/2222", "Ovo je Nikola Tesla, opis opis", "\"Ovo je prvi citat\", \"Ovo je drugi citat\"/0"))
        //InspiringPeople.inspiringPeople.add(InspiringPerson(File("C:\\Users\\Stjepan Posavec\\Documents\\Projects\\RazvojMobilnihAplikacija\\LV21InspiringPerson\\app\\src\\main\\res\\drawable\\placeholder.jpeg"),"Nikola2", "Tesla", "01/01/1111", "02/02/2222", "Ovo je Nikola Tesla, opis opis", "\"Ovo je prvi citat\", \"Ovo je drugi citat\"/0"))
        //InspiringPeople.inspiringPeople.add(InspiringPerson(File("C:\\Users\\Stjepan Posavec\\Documents\\Projects\\RazvojMobilnihAplikacija\\LV21InspiringPerson\\app\\src\\main\\res\\drawable\\placeholder.jpeg"),"Nikola3", "Tesla", "01/01/1111", "02/02/2222", "Ovo je Nikola Tesla, opis opis", "\"Ovo je prvi citat\", \"Ovo je drugi citat\"/0"))
    }
}
