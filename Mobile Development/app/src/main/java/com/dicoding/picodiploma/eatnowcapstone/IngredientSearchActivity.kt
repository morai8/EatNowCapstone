package com.dicoding.picodiploma.eatnowcapstone

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.dicoding.picodiploma.eatnowcapstone.databinding.FragmentDashboardBinding

class IngredientSearchActivity: AppCompatActivity() {
    private lateinit var containerLayout: ConstraintLayout
    private lateinit var addButton: Button
    var textViewCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)

        containerLayout = findViewById(R.id.src_Layout)


        addButton.setOnClickListener{
            addNewTextView()
        }
    }

    private fun addNewTextView() {
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(35,20,35,0)

        val newTextView = TextView(this)
        newTextView.id = View.generateViewId()
        newTextView.text = "Write Your Ingredient Here... ${textViewCount + 1}"

        newTextView.textSize = 16f

        newTextView.setPadding(8,8,8,8)
        newTextView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))
        newTextView.layoutParams = layoutParams

        containerLayout.addView(newTextView)
        textViewCount++

    }
}