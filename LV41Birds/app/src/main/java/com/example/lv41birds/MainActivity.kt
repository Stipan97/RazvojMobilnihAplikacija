package com.example.lv41birds

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.lv41birds.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelCounter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ViewModelCounter::class.java)

        setupUI()

        viewModel.observer.observe(this, androidx.lifecycle.Observer {
            binding.invalidateAll()
        })
    }

    private fun setupUI() {
        binding.apply {
            binding.counters = viewModel

            btnHawk.setOnClickListener { incrementBirdAndChangeColor(1) }
            btnEagle.setOnClickListener { incrementBirdAndChangeColor(2) }
            btnVulture.setOnClickListener { incrementBirdAndChangeColor(3) }
            btnSparrow.setOnClickListener { incrementBirdAndChangeColor(4) }

            btnReset.setOnClickListener { resetCountersAndColor() }
        }
    }

    private fun incrementBirdAndChangeColor(i: Int) {
        var hawkCounter = viewModel.counterFirstBird.value
        var eagleCounter = viewModel.counterSecondBird.value
        var vultureCounter = viewModel.counterThirdBird.value
        var sparrowCounter = viewModel.counterFourthBird.value

        when(i) {
            1 -> {
                viewModel.counterFirstBird.value = hawkCounter?.plus(1)
                setBgColor(R.color.hawk)
            }
            2 -> {
                viewModel.counterSecondBird.value = eagleCounter?.plus(1)
                setBgColor(R.color.eagle)
            }
            3 -> {
                viewModel.counterThirdBird.value = vultureCounter?.plus(1)
                setBgColor(R.color.vulture)
            }
            4 -> {
                viewModel.counterFourthBird.value = sparrowCounter?.plus(1)
                setBgColor(R.color.sparrow)
            }
            else -> Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }

        refresh()
    }

    private fun refresh() {
        viewModel.observer.value = !viewModel.observer.value!!
    }

    private fun resetCountersAndColor() {
        resetCounters()
        setBgColor(R.color.white)
    }

    private fun resetCounters() {
        viewModel.counterFirstBird.value = 0
        viewModel.counterSecondBird.value = 0
        viewModel.counterThirdBird.value = 0
        viewModel.counterFourthBird.value = 0

        refresh()
    }

    private fun setBgColor(color: Int) {
        viewModel.bgColor.value = color
    }
}