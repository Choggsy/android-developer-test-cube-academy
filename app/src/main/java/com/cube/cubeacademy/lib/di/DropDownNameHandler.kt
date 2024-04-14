package com.cube.cubeacademy.lib.di

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.cube.cubeacademy.R
import com.cube.cubeacademy.databinding.ActivityCreateNominationBinding

class DropDownNameHandler(val repository: Repository) {
    private lateinit var spinner: Spinner

    fun populateDropdownWithNames(binding: ActivityCreateNominationBinding, context: Context, ) {
        val nameList = repository.getNomineeNameList()
        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, nameList)

        spinner = binding.root.findViewById(R.id.nominee_name_spinner)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = arrayAdapter
        setSpinner()
    }

    private fun setSpinner() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                parent.getItemAtPosition(position).toString() } //did originally use Toast here but removed since it doesn't match the FIGMA
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}