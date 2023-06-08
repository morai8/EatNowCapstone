package com.dicoding.picodiploma.eatnowcapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.buttonRegister.setOnClickListener {
            val name = binding.nameInputField.text.toString()
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            val confirmPassword = binding.confPasswordField.text.toString()

            viewModel.registerCek(name, email, password, confirmPassword)
            observeResponse()
        }


    }

    private fun observeResponse() {
        viewModel.registerResponse_.observe(this) { responseMessage ->
            if (responseMessage.msg == "Register Success") {
                val intentToLanding = Intent(this,LandingActivity::class.java)
                startActivity(intentToLanding)
                Toast.makeText(this,responseMessage.msg,Toast.LENGTH_SHORT).show()
                finish()
            } else{
                    Toast.makeText(this,responseMessage.msg,Toast.LENGTH_SHORT).show()
                }
            }
    }
}
