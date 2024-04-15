package com.cube.cubeacademy.lib.api

import android.adservices.adid.AdId
import com.cube.cubeacademy.lib.models.DataWrapper
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
	@GET("api/nomination")
	suspend fun getAllNominations(): DataWrapper<List<Nomination>>

	@GET("api/nominee")
	suspend fun getAllNominees(): DataWrapper<List<Nominee>>
	
	@FormUrlEncoded
	@POST("api/nomination")
	suspend fun createNomination(
		@Field("nominee_id") nomineeId: String,
		@Field("reason") reason: String,
		@Field("process") process: String
	): DataWrapper<Nomination>

	@FormUrlEncoded
	@GET("api/nomination:nomination_id")
	suspend fun getNominationByID(
		@Field("nomination_id") nominationId: String,
	): DataWrapper<Nomination>
}