package com.cube.cubeacademy.lib.di

import com.cube.cubeacademy.databinding.ActivityCreateNominationBinding
import com.cube.cubeacademy.lib.models.Nominee
import kotlinx.coroutines.runBlocking

class NewNomination(val repository: Repository, val binding: ActivityCreateNominationBinding) {
    private lateinit var radioValue: String


    fun create(process: String){
        val reason = binding.message.text.toString()
        return repository.createNomination("9a4bd093-e74c-4918-87cc-0c689cca78bf",reason,getRadioValue(process))
    }

//    private fun getNominationID(): String {
//        var foundNomineeID = ""
//        runBlocking {
//            val fullNameList = binding.nomineeNameSpinner.getSelectedItem().toString().split("[^ ]* (.*)".toRegex())
//            val firstName = fullNameList[0]
//            val lastName = fullNameList[1]
//            foundNomineeID = repository.getAllNominees().find { nominee: Nominee ->
//                nominee.firstName.equals(firstName) && nominee.lastName.equals(lastName)}?.nomineeId.toString()
//        }
//        return foundNomineeID
//    }

    private fun getRadioValue(process: String): String {
        when (process) {
            "Very Unfair" -> radioValue = "very_unfair"
            "Unfair" -> radioValue = "unfair"
            "Unsure" -> radioValue = "not_sure"
            "Fair" -> radioValue = "fair"
            "Very Fair" -> radioValue = "very_fair"
        }
        return radioValue
    }
}