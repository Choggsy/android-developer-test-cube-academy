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
import java.time.LocalDate
import javax.inject.Inject

private const val ID1 = "1"

private const val ID2 = "2"

private const val ID3 = "3"

private const val REASON = "reason"

private const val PROCESS = "process"

private const val DATE_SUBMITTED = "2023-10-11"

private const val DATE_CLOSED = "2023-11-11"

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
            Nomination(ID1, ID1, REASON, PROCESS, DATE_SUBMITTED, DATE_CLOSED),
            Nomination(ID2, ID2, REASON, PROCESS, DATE_SUBMITTED, DATE_CLOSED),
            Nomination(ID1, ID3, REASON, PROCESS, DATE_SUBMITTED, DATE_CLOSED),
            Nomination(ID2, "4", REASON, PROCESS, DATE_SUBMITTED, DATE_CLOSED),
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
    fun createNominationTest() = runBlocking {
        // TODO: Come back and implement correct Nomination Date
        val result = Repository(MockApiService()).createNomination(ID1, REASON, PROCESS)
        val nominationCreateDate = LocalDate.now()
        val expected = Nomination(
            "",
            ID1,
            REASON,
            PROCESS,
            nominationCreateDate.toString(),
            nominationCreateDate.plusMonths(1).withDayOfMonth(1).toString());
        assertEquals(expected, result);
    }

    // would like to add Parametrized nominationClosesWithinCreatedMonth if I have the time
//    also use the assertK (Kotlins AssertJ) for more BDD style assertions for readability and more assertion control
}