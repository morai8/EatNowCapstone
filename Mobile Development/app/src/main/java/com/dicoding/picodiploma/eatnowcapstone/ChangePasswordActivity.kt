package com.dicoding.picodiploma.eatnowcapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSubmit.setOnClickListener {
            val currentPassword = binding.currentInputField.text.toString()
            val newPassword = binding.newPasswordInputField.text.toString()
            val reEnterPassword = binding.reEnterInputField.text.toString()

            if (currentPassword.isEmpty() || newPassword.isEmpty() || reEnterPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (newPassword != reEnterPassword) {
                Toast.makeText(this, "New password and re-entered password do not match", Toast.LENGTH_SHORT).show()
            } else {

            }

                Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}