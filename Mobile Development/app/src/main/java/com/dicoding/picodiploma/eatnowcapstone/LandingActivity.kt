package com.dicoding.picodiploma.eatnowcapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.button.MaterialButton


class LandingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener{
            val intentToRegisterActivity = Intent(this,RegisterActivity::class.java)
            startActivity(intentToRegisterActivity)
        }
        val buttonSignIn = findViewById<Button>(R.id.buttonSignIn)
        buttonSignIn.setOnClickListener{
            val intentToSignInActivity = Intent(this,SignInActivity::class.java)
            startActivity(intentToSignInActivity)
        }

    }


}