package com.dicoding.picodiploma.eatnowcapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        }
    }
}