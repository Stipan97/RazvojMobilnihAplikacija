package com.example.mysoundboardapp

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    private var mSoundMap: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupUI()
        this.loadSounds()
    }

    private fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.gg] = this.mSoundPool.load(this, R.raw.gg, 1)
        this.mSoundMap[R.raw.gj] = this.mSoundPool.load(this, R.raw.gj, 1)
        this.mSoundMap[R.raw.wp] = this.mSoundPool.load(this, R.raw.wp, 1)
    }

    private fun setupUI() {
        this.ibGG.setOnClickListener(this)
        this.ibGJ.setOnClickListener(this)
        this.ibWP.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (!this.mLoaded) return
        if (v != null) {
            when (v.getId()) {
                R.id.ibGG -> playSound(R.raw.gg)
                R.id.ibGJ -> playSound(R.raw.gj)
                R.id.ibWP -> playSound(R.raw.wp)
            }
        }
    }

    private fun playSound(selectedSound: Int) {
        val soundId = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }
}