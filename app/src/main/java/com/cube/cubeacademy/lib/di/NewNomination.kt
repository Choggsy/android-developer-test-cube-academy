package com.cube.cubeacademy.lib.di

import com.cube.cubeacademy.databinding.ActivityCreateNominationBinding
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import kotlinx.coroutines.runBlocking

class NewNomination(val repository: Repository, val binding: ActivityCreateNominationBinding) {
    private lateinit var radioValue: String

    fun create(process: String): Nomination{
        val reason = binding.message.text.toString()
        return repository.createNomination(getNominationID(),reason,getRadioValue(process))
    }

    private fun getNominationID(): String {
        var foundNomineeID = " "
        runBlocking {
            val fullNameList = binding.nomineeNameSpinner.getSelectedItem().toString().split(" ")
            foundNomineeID = repository.getAllNominees().find { nominee: Nominee ->
                nominee.firstName.equals(fullNameList[0]) && nominee.lastName.equals(fullNameList[1])}?.nomineeId.toString()
        }
        return foundNomineeID
    }

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