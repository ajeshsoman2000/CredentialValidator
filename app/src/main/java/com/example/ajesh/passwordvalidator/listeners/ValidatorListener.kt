package com.example.ajesh.passwordvalidator.listeners

import com.example.ajesh.passwordvalidator.model.BreachModel

interface ValidatorListener {
    fun onMatchFound(count: Int, accounts: List<BreachModel>)
    fun noMatchFound()
    fun invalidEmail()
    fun errorOccured(msg: String)
}