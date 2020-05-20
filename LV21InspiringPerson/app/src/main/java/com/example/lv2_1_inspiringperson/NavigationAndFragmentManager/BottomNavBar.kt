package com.example.lv2_1_inspiringperson

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.lv2_1_inspiringperson.Fragments.AddPersonFragment
import com.example.lv2_1_inspiringperson.Fragments.ViewPeopleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavBar(bottomNavView: BottomNavigationView, private val supportFragmentManager: FragmentManager) {
    private val bottomNavBar = bottomNavView
    lateinit var addPersonFragment: AddPersonFragment
    lateinit var viewPeopleFragment: ViewPeopleFragment

    fun handleNavBarItems() {
        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.add_person -> {
                    addPersonFragment =
                        AddPersonFragment()
                    defaultFragmentBehaviour(R.id.frame, addPersonFragment)
                }

                R.id.view_people -> {
                    viewPeopleFragment =
                        ViewPeopleFragment()
                    defaultFragmentBehaviour(R.id.frame, viewPeopleFragment)
                }
            }

            true
        }
    }

    private fun defaultFragmentBehaviour(id: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(id, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun showDefaultFragment() {
        defaultFragmentBehaviour(R.id.frame,
            AddPersonFragment()
        )
    }
}