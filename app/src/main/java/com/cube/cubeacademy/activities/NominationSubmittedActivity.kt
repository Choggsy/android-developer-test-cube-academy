package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cube.cubeacademy.databinding.ActivityNominationSubmittedBinding

class NominationSubmittedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNominationSubmittedBinding
    private lateinit var makeNominationButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNominationSubmittedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeNominationButtonListener()
        backButtonListener()
    }

    private fun makeNominationButtonListener() {
        makeNominationButton = binding.submitButton
        makeNominationButton.setOnClickListener {
            startActivity(Intent(this, CreateNominationActivity::class.java))
        }
    }

    private fun backButtonListener() {
        backButton = binding.backButton
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}