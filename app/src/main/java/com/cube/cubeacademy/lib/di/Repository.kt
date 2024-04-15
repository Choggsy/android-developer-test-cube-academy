package com.cube.cubeacademy.lib.di

import com.cube.cubeacademy.lib.api.ApiService
import com.cube.cubeacademy.lib.models.DataWrapper
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.stream.IntStream

class Repository(val api: ApiService) {
    // TODO: Add additional code if you need it

//    - As part of the logic of the code, the basic DI modules and the retrofit instance and endpoints have already been defined, but they are not being used.
//  - You should update the [Repository] class to work with the API.


    suspend fun getAllNominations(): List<Nomination> {
        return api.getAllNominations().data
    }

    suspend fun getAllNominees(): List<Nominee> {
        return api.getAllNominees().data;
    }

    fun createNomination(nomineeId: String, reason: String, process: String){
        val processValidResponses = listOf("very_unfair", "unfair", "not_sure", "fair", "very_fair")
        if(!processValidResponses.contains(process)){
            throw Exception("the process $process was not one of these values: $processValidResponses")
        }else{
            runBlocking { api.createNomination(nomineeId, reason, process)}
        }
    }

    //Process: com.cube.cubeacademy, PID: 10791  retrofit2.HttpException: HTTP 422

    fun getNomineeNameList(): MutableList<String> {
        val nameList = mutableListOf<String>()
        runBlocking {
            this@Repository.getAllNominees()
                .forEach { nominee: Nominee -> nameList.add(nominee.firstName.plus(" ").plus(nominee.lastName)) } }
        return nameList
    } //note: extracted out of the dropdown spinner cause it could be reused. maybe if the nomination spinner was included within the app
}