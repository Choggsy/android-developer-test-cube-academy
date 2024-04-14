package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
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

    private var isReasonEntered = false
    private var isRadioEntered = false
    private var isNomineeEntered = false

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
        dropDownListener()
    }

    /**
     * TODO:
     * - Add the logic for the views / maybe done
     * - add the logic to create the new nomination using the api
     *  android:enabled="false" -> true WHEN  all submitEnable -> true THEN startActivity4
     *
     *  onClick for submit THen set value for createNomination(could be private with status fed in)
     */

    private fun isFormComplete() {
        val submitButton = binding.submitButton
        submitButton.isEnabled = isReasonEntered && isRadioEntered && isNomineeEntered
    }

    private fun dropDownListener() {
        binding.nomineeNameSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val dropDownValue = binding.nomineeNameSpinner.getSelectedItem().toString()
                    isNomineeEntered = dropDownValue.isNotBlank() && dropDownValue != "Select Option"
                    isFormComplete()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun reasonTextListener() {
        binding.message.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(arg0: Editable) {
                isReasonEntered = arg0.isNotBlank()
                isFormComplete()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
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
            isRadioEntered = radioButton.isChecked
            isFormComplete()
        }
    }
}