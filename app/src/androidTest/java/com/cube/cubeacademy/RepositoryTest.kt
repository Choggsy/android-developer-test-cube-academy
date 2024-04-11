package com.cube.cubeacademy

import com.cube.cubeacademy.di.MockApiService
import com.cube.cubeacademy.lib.di.Repository
import com.cube.cubeacademy.lib.models.Nomination
import com.cube.cubeacademy.lib.models.Nominee
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

private const val ID1 = "1"

private const val ID2 = "2"

private const val ID3 = "3"

private const val REASON = "reason"

private const val PROCESS = "process"

@HiltAndroidTest
class RepositoryTest {
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
        val result = Repository(MockApiService()).getAllNominations()
        val expected = listOf(
            Nomination(ID1, ID1, REASON, PROCESS, "2023-10-11", "2023-11-11"),
            Nomination(ID2, ID2, REASON, PROCESS, "2023-10-11", "2023-11-11"),
            Nomination(ID1, ID3, REASON, PROCESS, "2023-10-11", "2023-11-11"),
            Nomination(ID2, "4", REASON, PROCESS, "2023-10-11", "2023-11-11"),
        )

        assertEquals(expected, result);
    }

    @Test
    fun getNomineesTest() = runBlocking {
        val result = Repository(MockApiService()).getAllNominees()
        val expected = listOf(
            Nominee(ID1, "FirstTest1", "LastTest1"),
            Nominee(ID2, "FirstTest2", "LastTest2"),
            Nominee(ID3, "FirstTest3", "LastTest3"),
        )

        assertEquals(expected, result);
    }

    @Test
    fun createNominationTest()  = runBlocking{
        // TODO: Write a test for creating a new nomination using the mock api
        val result = Repository(MockApiService()).createNomination(ID1, REASON, PROCESS)
        val expected = Nomination("3",ID1, REASON, PROCESS,"2023-10-11","2024-04-11");
        assertEquals(expected, result);
    }
}