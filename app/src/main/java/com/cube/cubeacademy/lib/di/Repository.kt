package com.cube.cubeacademy.lib.di

import com.cube.cubeacademy.lib.api.ApiService
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import java.time.LocalDate
import java.util.stream.IntStream

class Repository(val api: ApiService) {
    // TODO: Add additional code if you need it

    suspend fun getAllNominations(): List<Nomination> {
        return api.getAllNominations().data
    }

    suspend fun getAllNominees(): List<Nominee> {
        return api.getAllNominees().data;
    }

    suspend fun createNomination(nomineeId: String, reason: String, process: String): Nomination? {
        // TODO: Write the code to create a new nomination using the api
        // TODO: change Close date to the end of the month created instead of an added month.
        // I Chose to increment the closing date by a month of the day created. As these are monthly employee nominations.
        // I set that are a variable in case microsecond discrepancy causes issue
        val createDate = LocalDate.now()
        val newNominationId = api.getAllNominations().data.last().nominationId.toInt().plus(1); //done this way to increment based of last in api list of nominations.
        return Nomination(
            newNominationId.toString(),
            nomineeId,
            reason,
            process,
            createDate.toString(),
            createDate.plusMonths(1).toString()
        )
    }
}