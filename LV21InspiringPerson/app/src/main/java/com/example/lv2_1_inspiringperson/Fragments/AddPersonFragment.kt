package com.example.lv2_1_inspiringperson

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class AddPersonFragment : Fragment() {

    var imageView: ImageView? = null
    var imageURI: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_add_person, container, false)

        v.findViewById<RadioGroup>(R.id.lLay).setOnCheckedChangeListener { _, _ ->
            v.findViewById<EditText>(R.id.etDDate).isEnabled = v.findViewById<RadioButton>(R.id.rbDead).isChecked
        }

        imageView = v.findViewById(R.id.ivPreview)

        v.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            InspiringPeople.inspiringPeople.add(
                InspiringPerson(
                    imageURI,
                    v.findViewById<EditText>(R.id.etName).text.toString(),
                    v.findViewById<EditText>(R.id.etSurname).text.toString(),
                    v.findViewById<EditText>(R.id.etBDate).text.toString(),
                    if(v.findViewById<RadioButton>(R.id.rbAlive).isChecked) "Alive" else v.findViewById<EditText>(R.id.etName).text.toString(),
                    v.findViewById<EditText>(R.id.etDesc).text.toString(),
                    v.findViewById<EditText>(R.id.etQuotes).text.toString()
                )
            )
            Toast.makeText(v.context, "Person Added!", Toast.LENGTH_SHORT).show()
        }

        v.findViewById<Button>(R.id.btnChoose).setOnClickListener {
            pickImageFromGallery()
        }

        return v
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
