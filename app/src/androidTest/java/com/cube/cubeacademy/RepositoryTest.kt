package com.cube.cubeacademy

import com.cube.cubeacademy.di.MockApiService
import com.cube.cubeacademy.lib.di.Repository
import com.cube.cubeacademy.lib.models.Nomination
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class RepositoryTest {
    // FIXME Can use the MockApiSerivce to run the repository Tests

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: Repository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getNominationsTest() = runBlocking {//added to tests on suspend functions
        val mockApiService = MockApiService();
        val data = "data";

        val result = Repository(mockApiService).getAllNominations()
        val expected = listOf(Nomination(data, data, data, data, data, data))
//<[Nomination(nominationId=1, nomineeId=1, reason=reason, process=process, dateSubmitted=2023-10-11, closingDate=2023-11-11), Nomination(nominationId=2, nomineeId=2, reason=reason, process=process, dateSubmitted=2023-10-11, closingDate=2023-11-11), Nomination(nominationId=1, nomineeId=3, reason=reason, process=process, dateSubmitted=2023-10-11, closingDate=2023-11-11), Nomination(nominationId=2, nomineeId=4, reason=reason, process=process, dateSubmitted=2023-10-11, closingDate=2023-11-11)]>
        assertEquals(expected,result);
    }

    @Test
    fun getNomineesTest() {
        // TODO: Write a test for getting all the nominees from the mock api
    }

    @Test
    fun createNominationTest() {
        // TODO: Write a test for creating a new nomination using the mock api
    }
}