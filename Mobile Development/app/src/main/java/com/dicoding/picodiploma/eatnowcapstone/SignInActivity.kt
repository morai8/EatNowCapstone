package com.dicoding.picodiploma.eatnowcapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        binding.buttonSignIn.setOnClickListener {
            val email = binding.emailInputField.text.toString()
            val password = binding.passwordInputField.text.toString()

            viewModel.signInCek(email,password)
            observeResponse()
        }
    }
    private fun checkLoginStatus() {
        if (PreferenceHelper.isFirstLogin(this)) {
            val intentToBmi = Intent(this, BmiActivity::class.java)
            startActivity(intentToBmi)
            finish()
        } else {
            val intentToLanding = Intent(this, LandingActivity::class.java)
            startActivity(intentToLanding)
            finish()
        }
    }
    private fun observeResponse() {
        viewModel.signInResponse_.observe(this) { responseMessage ->
            Log.d("SignInActivity", "observeResponse: $responseMessage")
            if (responseMessage != null) {
                if (responseMessage.status == "success") {
                    PreferenceHelper.setFirstLogin(this, true)
                    val intentToLanding = Intent(this, LandingActivity::class.java)
                    startActivity(intentToLanding)
                    Toast.makeText(this, responseMessage.status, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, responseMessage.status, Toast.LENGTH_SHORT).show()
                }
                checkLoginStatus()
            } else {
                Toast.makeText(this, "An unexpected error occurred. Please try again.", Toast.LENGTH_SHORT).show()
                Log.e("SignInActivity", "observeResponse: responseMessage is null")
            }
        }
    }

}