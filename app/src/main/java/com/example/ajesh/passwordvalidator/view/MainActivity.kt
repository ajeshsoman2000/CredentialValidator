package com.example.ajesh.passwordvalidator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.ajesh.passwordvalidator.R
import com.example.ajesh.passwordvalidator.databinding.ActivityMainBinding
import com.example.ajesh.passwordvalidator.listeners.ValidatorListener
import com.example.ajesh.passwordvalidator.model.BreachModel
import com.example.ajesh.passwordvalidator.viewmodel.MainViewModel
import com.example.ajesh.passwordvalidator.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ValidatorListener {
    override fun onMatchFound(count: Int, accounts: List<BreachModel>) {
        showToast("Your email id was found in $count data breaches.")
        accounts.forEach {
            Log.v("MainActivity ","Name: ${it.Name}")
            Log.v("MainActivity ","Title: ${it.Title}")
            Log.v("MainActivity ","Domain: ${it.Domain}")
        }
    }

    override fun noMatchFound() {
        showToast("Your email id not found in any data breach as of now.")
    }

    override fun invalidEmail() {
        showToast("Please enter a valid email id.")
    }

    override fun errorOccured(msg: String) {
        showToast(msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
        activityMainBinding.viewModel = ViewModelProviders.of(this@MainActivity,ViewModelFactory(this)).get(MainViewModel::class.java)

        et_email.addTextChangedListener((activityMainBinding.viewModel as MainViewModel).emailTextWatch)

        btn_validate.setOnClickListener {
            (activityMainBinding.viewModel as MainViewModel).validate()
        }
    }
}
