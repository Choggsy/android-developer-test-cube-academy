package com.cube.cubeacademy.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.rule.IntentsRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule

//TODO : come back and have another go implementing Espresso UI tests
@HiltAndroidTest
class MainActivityTest {
    //  note : This is my First time using Espresso API but from my research I can see its a nice framework to test the UI of the Android App. I imagine they should be wired similar to a Java integration test

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val intentsTestRule = IntentsRule()

    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun cleanup() {
        scenario.close()
    }
    //espresso tests state exceptions,interactions and assertions
//    Espresso-Intents works like Mockito!

    //use onView for most, should be able to identify with just withText and R.id
    // for ListView,GridView or Spinner use onData instead
//
//    @Test
//    fun canScrollOnHomePage() {
//        scenario = launch(Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java))
//        onView(isRoot()).perform()
//    }
//        @Test
//    fun checkOnClickButtonOpensCreateNominationActivity() {
//        scenario = launch(Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java))
//
//        intending(hasComponent(CreateNominationActivity::class.java.name))
//            .respondWith(ActivityResult(Activity.RESULT_OK, null))
//
//        onView(withId(R.id.create_button)).perform(click())
//    }

    //If i have time i would come back and test with the espresso-contrib package as it contains accessibility checks.
}