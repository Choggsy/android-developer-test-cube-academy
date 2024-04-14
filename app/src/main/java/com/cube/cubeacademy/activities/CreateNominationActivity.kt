package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var submitButton: Button
    private lateinit var radioButton: RadioButton

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNominationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DropDownNameHandler(repository).populateDropdownWithNames(binding, this)
        backButtonListener()

        radioButtonListener()
        reasonTextListener()

        populateUI()
    }

    private fun populateUI() {
        /**
         * TODO:
         * - Add the logic for the views / maybe done
         * - add the logic to create the new nomination using the api
         *  android:enabled="false" -> true WHEN  all submitEnable -> true THEN startActivity4
         *
         *  onClick for submit THen set value (could be private with status fed in)
         */
        submitButton = binding.submitButton

        if (reasonTextListener() == true) {
            submitButton.isEnabled = true
        }
    }

    private fun backButtonListener() {
        backButton = binding.backButton
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun radioButtonListener() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioButton = findViewById(checkedId)
        }
    }

    private fun reasonTextListener(): Boolean {
        var reasonSubmitReady = false
        binding.message.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(arg0: Editable) {
                reasonSubmitReady = true
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                reasonSubmitReady = false
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        return reasonSubmitReady
    }
}