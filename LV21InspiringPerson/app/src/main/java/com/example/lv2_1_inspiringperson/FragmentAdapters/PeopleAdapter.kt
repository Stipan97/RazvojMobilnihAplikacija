package com.example.lv2_1_inspiringperson

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

const val EXTRA_POSITION = "-1"

class PeopleAdapter(private val personList: ArrayList<InspiringPerson>, private val context: Context, private val fragmentManager: FragmentManager?) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile = itemView.findViewById<ImageView>(R.id.ivProfile)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        val tvDesc = itemView.findViewById<TextView>(R.id.tvDesc)
        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        val ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false))
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person: InspiringPerson = personList[position]
        val tvNameString = person.name + " " + person.surname
        val tvDateString = person.birthDate + " - " + person.deathDate

        holder.ivProfile.setImageURI(InspiringPeople.inspiringPeople[position].image)
        holder.tvName.text = tvNameString
        holder.tvDate.text = tvDateString
        holder.tvDesc.text = person.description

        holder.ivProfile.setOnClickListener {
            Toast.makeText(context, InspiringPeople.inspiringPeople[position].name + " " + InspiringPeople.inspiringPeople[position].surname + ": " + InspiringPeople.rndQuote(position), Toast.LENGTH_LONG).show()
        }

        holder.ivDelete.setOnClickListener {
            removeItem(position)
        }

        holder.ivEdit.setOnClickListener {
            editItem(position)
        }
    }

    private fun editItem(position: Int) {
        val intent = Intent(context, EditPersonActivity::class.java).apply {
            putExtra(EXTRA_POSITION, position.toString())
        }
        context.startActivity(intent)
    }

    private fun removeItem(position: Int) {
        InspiringPeople.inspiringPeople.removeAt(position)
        notifyDataSetChanged()
    }
}