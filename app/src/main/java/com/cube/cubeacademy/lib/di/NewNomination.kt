package com.cube.cubeacademy.lib.di

import com.cube.cubeacademy.databinding.ActivityCreateNominationBinding
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import kotlinx.coroutines.runBlocking

class NewNomination(val repository: Repository, val binding: ActivityCreateNominationBinding) {
    fun create(process: String): Nomination {
        val reason = binding.message.text.toString()
        return Repository(repository.api).createNomination(getNominationID(),reason,process)
    }

    private fun getNominationID(): String {
        //just for testing, make private later
        var foundNomineeID = ""
        runBlocking {
            val fullNameList = binding.nomineeNameSpinner.getSelectedItem().toString().split("[^ ]* (.*)".toRegex())
            val firstName = fullNameList[0]
            val lastName = fullNameList[1]
            foundNomineeID = repository.getAllNominees().find { nominee: Nominee ->
                nominee.firstName.equals(firstName) && nominee.lastName.equals(lastName)}?.nomineeId.toString()
        }
        return foundNomineeID
    }
}