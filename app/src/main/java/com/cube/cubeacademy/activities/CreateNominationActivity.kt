package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.cube.cubeacademy.databinding.ActivityCreateNominationBinding
import com.cube.cubeacademy.lib.di.DropDownNameHandler
import com.cube.cubeacademy.lib.di.Repository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateNominationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNominationBinding
    private lateinit var backButton: Button
    private lateinit var radioButton: RadioButton

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateNominationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backButtonListener()
        populateUI()
    }

    private fun populateUI() {
        /**
         * TODO:
         * - Add the logic for the views / maybe done
         * - add the logic to create the new nomination using the api
         */
        DropDownNameHandler(repository).populateDropdownWithNames(binding,this)
    }

    private fun backButtonListener() {
        backButton = binding.backButton
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun radioButtonListener() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            //setSomething to indicate submit button can be pressed
            radioButton = findViewById(checkedId)
        }
    }
}