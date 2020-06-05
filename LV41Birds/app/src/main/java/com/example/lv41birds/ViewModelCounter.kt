package com.example.lv41birds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelCounter : ViewModel() {
    var counterFirstBird = MutableLiveData<Int>(0)
    var counterSecondBird = MutableLiveData<Int>(0)
    var counterThirdBird = MutableLiveData<Int>(0)
    var counterFourthBird = MutableLiveData<Int>(0)

    var bgColor = MutableLiveData<Int>(R.color.white)

    var observer = MutableLiveData<Boolean>(true)
}