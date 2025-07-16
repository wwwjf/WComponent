package com.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


open class SplashViewModel : ViewModel() {

    var title:ObservableField<String> = ObservableField("")

    init {
        title.set("跳转到Mine")
    }

}