package com.example.ajesh.passwordvalidator.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.ajesh.passwordvalidator.listeners.ValidatorListener

class ViewModelFactory(var listener: ValidatorListener): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(listener) as T
    }
}