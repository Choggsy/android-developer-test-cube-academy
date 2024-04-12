package com.cube.cubeacademy.lib.di

import com.cube.cubeacademy.lib.api.ApiService
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import java.time.LocalDate
import java.util.stream.IntStream

class Repository(val api: ApiService) {
    // TODO: Add additional code if you need it :: TRY TO USE FOR THE NOMINATION ID

//    - As part of the logic of the code, the basic DI modules and the retrofit instance and endpoints have already been defined, but they are not being used.
//  - You should update the [Repository] class to work with the API.

    suspend fun getAllNominations(): List<Nomination> {
        return api.getAllNominations().data
    }

    suspend fun getAllNominees(): List<Nominee> {
        return api.getAllNominees().data;
    }

    suspend fun createNomination(nomineeId: String, reason: String, process: String): Nomination? {
        val createDate = LocalDate.now(); //done this way to increment based of last in api list of nominations.
        return Nomination(
            "", //FIXME :: Get the nominationID from the user logged in as they are the ones Nominating the Nominee
            nomineeId,
            reason,
            process,
            createDate.toString(),
            createDate.plusMonths(1).withDayOfMonth(1).toString()
        )
        // I Chose to set the close date to the first of the month after of the date nomination created. As these are monthly employee nominations, so would close by the start of the next month.
        // I set that are a variable in case microsecond discrepancy causes issue
    }
}