package com.cube.cubeacademy.di

import com.cube.cubeacademy.lib.di.DropDownNameHandler
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class DropDownNameHandlerTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dropDownHandler: DropDownNameHandler

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun canPopulate() {
        //FIXME :: come back after minimal implementation. Use Mockito
    }
}