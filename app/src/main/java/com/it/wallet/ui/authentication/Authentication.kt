package com.it.wallet.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.it.wallet.MainActivity
import com.it.wallet.databinding.ActivityAuthentificationBinding
import com.it.wallet.ui.app.App

class Authentication : AppCompatActivity() {

    private lateinit var binding: ActivityAuthentificationBinding

    override fun onStart() {
        super.onStart()
        if(App.instance?.getPrefs()?.isAuthenticated == true){
            MainActivity.start(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthentificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            App.instance?.getPrefs()?.isAuthenticated = true
            App.instance?.getPrefs()?.password = binding.pass.text.toString()
            App.instance?.getPrefs()?.username = binding.login.text.toString()
            MainActivity.start(this)
        }
    }
}