package com.dicoding.picodiploma.eatnowcapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivityEditBmiBinding
class EditBmiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}