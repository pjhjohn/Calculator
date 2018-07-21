package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.UnitTestWatcher
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class OperatorUnitTest : UnitTestWatcher() {

    @Before
    fun before() {

    }

    @After
    fun after() {

    }

    @Test
    fun testPlusOperator() {
        assertThat(Operator.Plus.isEmpty).isFalse()
        assertThat(Operator.Plus.toString()).isEqualTo("+")
    }

    @Test
    fun testMinusOperator() {
        assertThat(Operator.Minus.isEmpty).isFalse()
        assertThat(Operator.Minus.toString()).isEqualTo("-")
    }

    @Test
    fun testMultiplyOperator() {
        assertThat(Operator.Multiply.isEmpty).isFalse()
        assertThat(Operator.Multiply.toString()).isEqualTo("×")
    }

    @Test
    fun testDivideOperator() {
        assertThat(Operator.Divide.isEmpty).isFalse()
        assertThat(Operator.Divide.toString()).isEqualTo("÷")
    }

    @Test
    fun testEmptyOperatorWithoutException() {
        assertThat(Operator.Empty.isEmpty).isTrue()
        assertThat(Operator.Empty.toString()).isEmpty()
    }

}
