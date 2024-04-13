package com.cube.cubeacademy.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.cube.cubeacademy.R
import com.cube.cubeacademy.databinding.ActivityCreateNominationBinding
import com.cube.cubeacademy.lib.di.Repository
import com.cube.cubeacademy.lib.models.Nominee
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class CreateNominationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNominationBinding
    private lateinit var backButton: Button
    private lateinit var radioButton: RadioButton
    private lateinit var spinner: Spinner

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateNominationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backButtonListener()
        populateUI()
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

    private fun populateUI() {
        /**
         * TODO: Populate the form after having added the views to the xml file (Look for TODO comments in the xml file)
         * 		 Add the logic for the views and at the end, add the logic to create the new nomination using the api
         * 		 The nominees drop down list items should come from the api (By fetching the nominee list)
         */
        populateDropdownWithNames()
    }


    private fun populateDropdownWithNames() {
        //FIXME :: extract most of this logic into own handler to unit Test.
//        would add custom xml for the dropdown background if I have time to match the FIGMA : https://www.youtube.com/watch?v=N8GfosWTt44
        val nameList = mutableListOf<String>()
        runBlocking {
            nameList.add(
                0,
                "Select Option"
            ) //would have like this to be something set in the .xml but research pointed towards this being the common implementation
            repository.getAllNominees()
                .forEach { nominee: Nominee ->
                    nameList.add(
                        nominee.firstName.plus(" ").plus(nominee.lastName)
                    )
                }
        }

        spinner = findViewById(R.id.nominee_name_spinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nameList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = arrayAdapter

        //Spinner Pattern
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent.getItemAtPosition(position).toString()
            } //did originally use Toast here but removed since it doesn't match the FIGMA

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}