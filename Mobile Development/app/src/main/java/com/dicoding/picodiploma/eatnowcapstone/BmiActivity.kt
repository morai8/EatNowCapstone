package com.dicoding.picodiploma.eatnowcapstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.eatnowcapstone.databinding.ActivityBmiBinding
import android.content.SharedPreferences
import android.preference.PreferenceManager

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        PreferenceHelper.setFirstLogin(this, false)

        binding.buttonSubmit.setOnClickListener {
            val heightString = binding.heightField.text.toString()
            val weightString = binding.weightField.text.toString()

            val height = heightString.toIntOrNull()
            val weight = weightString.toIntOrNull()

            if (height != null && weight != null) {
                val gender = when (binding.rgGender.checkedRadioButtonId) {
                    R.id.rb_male -> "male"
                    R.id.rb_female -> "female"
                    else -> ""
                }
                sharedPreferences.edit().putString("gender", gender).apply()

                sharedPreferences.edit().putInt("height", height).apply()

                sharedPreferences.edit().putInt("weight", weight).apply()

            } else {
            }
            val intentToHomeActivity = Intent(this,HomeActivity::class.java)
            startActivity(intentToHomeActivity)
        }

    }
    private fun retrieveSavedData() {
        val gender = sharedPreferences.getString("gender", "")
        val height = sharedPreferences.getInt("height", 0)
        val weight = sharedPreferences.getInt("weight", 0)

    }
}