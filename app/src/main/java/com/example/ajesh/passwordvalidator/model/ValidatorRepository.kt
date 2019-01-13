package com.example.ajesh.passwordvalidator.model

import android.util.Log
import com.example.ajesh.passwordvalidator.listeners.ValidatorListener
import com.example.ajesh.passwordvalidator.servicelayer.BreachValidatorService

class ValidatorRepository {

    fun fetchBreachedAccountsList(listener: ValidatorListener, sliceOf5Chars: String) {
        Log.v("ValidatorRepository :: ", "first 5 characters => $sliceOf5Chars")
        BreachValidatorService().fetch(sliceOf5Chars, listener)
    }
}