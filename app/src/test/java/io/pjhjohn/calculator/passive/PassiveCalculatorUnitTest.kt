package io.pjhjohn.calculator.passive

import io.pjhjohn.calculator.UnitTestWatcher
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class PassiveCalculatorUnitTest : UnitTestWatcher() {

    @Before
    fun before() {

    }

    @After
    fun after() {

    }

    @Test
    fun litmus() {
        assertThat(true).isTrue()
    }

}
