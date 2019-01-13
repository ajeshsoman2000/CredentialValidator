package com.example.ajesh.passwordvalidator.servicelayer

import android.util.Log
import com.example.ajesh.passwordvalidator.listeners.ValidatorListener
import com.example.ajesh.passwordvalidator.model.BreachModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BreachValidatorService: Callback<List<BreachModel>>{

    private var listener : ValidatorListener? = null

    override fun onResponse(call: Call<List<BreachModel>>, response: Response<List<BreachModel>>) {
        when(response.code()) {
            200 -> listener?.onMatchFound(response.body()?.size as Int, response.body() as List<BreachModel>)
            404 -> listener?.noMatchFound()
            else -> {
                listener?.errorOccured("Service failed due to unknown error.")
            }
        }
    }

    override fun onFailure(call: Call<List<BreachModel>>, t: Throwable) {
        listener?.errorOccured(t.message ?: "Service failed due to unknown error.")
    }

//    val BASE_URL = "https://api.pwnedpasswords.com"
    val BASE_URL = "https://haveibeenpwned.com"

    fun fetch(email: String, listener: ValidatorListener) {
        Log.v("BreachValidatorService", "email :: $email")
        this.listener = listener
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val breachServiceInterface = retrofit.create(BreachServiceInterface::class.java)

        val call: Call<List<BreachModel>> = breachServiceInterface.getBreachedAccounts(email)
        call.enqueue(this)

    }
}

interface BreachServiceInterface {

    @GET("/api/v2/breachedaccount/{email}")
    fun getBreachedAccounts(@Path("email") emailId: String ): Call<List<BreachModel>>
}