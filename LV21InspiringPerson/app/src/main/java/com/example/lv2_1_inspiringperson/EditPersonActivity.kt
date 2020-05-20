package com.example.lv2_1_inspiringperson

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.io.File

class EditPersonActivity : AppCompatActivity() {

    var imageURI: Uri? = null
    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_person)

        val position = intent.getStringExtra(EXTRA_POSITION)
        editFillBoxes(InspiringPeople.inspiringPeople[position.toInt()])

        imageView = findViewById(R.id.ivPreview)
        imageView!!.setImageURI(InspiringPeople.inspiringPeople[position.toInt()].image)

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            InspiringPeople.inspiringPeople.removeAt(position.toInt())
            InspiringPeople.inspiringPeople.add(
                InspiringPerson(
                    imageURI,
                    findViewById<EditText>(R.id.etName).text.toString(),
                    findViewById<EditText>(R.id.etSurname).text.toString(),
                    findViewById<EditText>(R.id.etBDate).text.toString(),
                    if(findViewById<RadioButton>(R.id.rbAlive).isChecked) "Alive" else findViewById<EditText>(R.id.etName).text.toString(),
                    findViewById<EditText>(R.id.etDesc).text.toString(),
                    findViewById<EditText>(R.id.etQuotes).text.toString()
                )
            )
            Toast.makeText(this, "Person Edited!", Toast.LENGTH_SHORT).show()
            finish()
        }

        findViewById<Button>(R.id.btnChoose).setOnClickListener {
            pickImageFromGallery()
        }
    }

    fun editFillBoxes(inspiringPerson: InspiringPerson) {
        findViewById<EditText>(R.id.etName).setText(inspiringPerson.name)
        findViewById<EditText>(R.id.etSurname).setText(inspiringPerson.surname)
        findViewById<EditText>(R.id.etBDate).setText(inspiringPerson.birthDate)
        if(inspiringPerson.deathDate == "Alive") {
            findViewById<RadioButton>(R.id.rbAlive).isChecked = true
        } else {
            findViewById<RadioButton>(R.id.rbDead).isChecked = true
            findViewById<EditText>(R.id.etDDate).setText(inspiringPerson.deathDate)
        }
        findViewById<EditText>(R.id.etDesc).setText(inspiringPerson.description)
        findViewById<EditText>(R.id.etQuotes).setText(inspiringPerson.quotes)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageURI = data?.data
        imageView?.setImageURI(imageURI)
    }
}
