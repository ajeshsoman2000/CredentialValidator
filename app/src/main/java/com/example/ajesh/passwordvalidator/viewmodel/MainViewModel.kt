package com.example.ajesh.passwordvalidator.viewmodel

import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.example.ajesh.passwordvalidator.listeners.ValidatorListener
import com.example.ajesh.passwordvalidator.model.ValidatorRepository

class MainViewModel(var listener: ValidatorListener): ViewModel() {

    private var email = ""

    var emailTextWatch = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            email = p0.toString()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }

    fun validate() {
        if(email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidatorRepository().fetchBreachedAccountsList(listener,email)
        } else {
            listener.invalidEmail()
        }
    }
}