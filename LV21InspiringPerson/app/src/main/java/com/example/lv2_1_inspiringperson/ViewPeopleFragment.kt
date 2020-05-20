package com.example.lv2_1_inspiringperson

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ViewPeopleFragment : Fragment() {

    private var tmpAdapter: PeopleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_view_people, container, false)

        val recyclerView = v.findViewById<RecyclerView>(R.id.rvPeople)
        recyclerView.layoutManager = LinearLayoutManager(v.context, RecyclerView.VERTICAL, false)

        val adapter = PeopleAdapter(InspiringPeople.inspiringPeople, v.context, fragmentManager)
        recyclerView.adapter = adapter

        tmpAdapter = adapter

        return v
    }
}
