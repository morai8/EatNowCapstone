package com.dicoding.picodiploma.eatnowcapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivityBmiBinding
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivitySignInBinding

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PreferenceHelper.setFirstLogin(this, false)
    }
}