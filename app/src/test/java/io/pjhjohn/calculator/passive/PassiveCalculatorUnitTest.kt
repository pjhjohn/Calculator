package io.pjhjohn.calculator.passive

import io.pjhjohn.calculator.UnitTestWatcher
import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.model.PanelInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class PassiveCalculatorUnitTest : UnitTestWatcher() {

    private val calculator = PassiveCalculator()
    private val inputsList = listOf(
        "56++÷÷595-0÷-c".toCharArray(),
        "1÷26912×264521".toCharArray(),
        "34-+49081=×8-4".toCharArray(),
        "5833026+6÷6099".toCharArray(),
        "+c+138138+-77×".toCharArray(),
        "-58==3-6÷50+91".toCharArray(),
        "+886=8×4==÷29+".toCharArray(),
        "c3×3+9-1==6=×=".toCharArray()
    )

    @Before
    fun before() {

    }

    @After
    fun after() {

    }

    @Test
    fun testCalculator() {
        val expectationsList = listOf(
            listOf("", "", "", "", "", "", "", "", "", "", "", "", "", ""),
            listOf("", "", "", "", "", "", "", "", "", "", "", "", "", ""),
            listOf("", "", "", "", "", "", "", "", "", "49115", "49115", "49115", "49115", "49115"),
            listOf("", "", "", "", "", "", "", "", "", "", "", "", "", ""),
            listOf("", "", "", "", "", "", "", "", "", "", "", "", "", ""),
            listOf("", "", "", "58", "58", "58", "58", "58", "58", "58", "58", "58", "58", "58"),
            listOf("", "", "", "", "886", "886", "886", "886", "32", "128", "128", "128", "128", "128"),
            listOf("", "", "", "", "", "", "", "", "9", "27", "27", "6", "6", "6")
        )

        inputsList.zip(expectationsList).forEach { listPair ->
            calculator.expr = Expression()
            calculator.eval = Operand.Empty
            listPair.first.zip(listPair.second).forEach {
                val input = it.first.toString()
                val expectation = it.second
                println(calculator.expr.copy(hide = false))
                calculator.input(input.toPanelInput())
                assertThat(calculator.eval.toString()).isEqualTo(expectation)
            }
        }
    }

    private fun PassiveCalculator.input(value: PanelInput) {
        when (value) {
            PanelInput.Number0,
            PanelInput.Number1,
            PanelInput.Number2,
            PanelInput.Number3,
            PanelInput.Number4,
            PanelInput.Number5,
            PanelInput.Number6,
            PanelInput.Number7,
            PanelInput.Number8,
            PanelInput.Number9
            -> calculator.input(value.toOperand())

            PanelInput.OperatorPlus,
            PanelInput.OperatorMinus,
            PanelInput.OperatorMultiply,
            PanelInput.OperatorDivide
            -> calculator.input(value.toOperator())

            PanelInput.CommandReset
            -> calculator.reset()

            PanelInput.CommandEvaluate
            -> calculator.evaluate()
        }
    }

    private fun String.toPanelInput(): PanelInput = when (this) {
        PanelInput.Number0.value -> PanelInput.Number0
        PanelInput.Number1.value -> PanelInput.Number1
        PanelInput.Number2.value -> PanelInput.Number2
        PanelInput.Number3.value -> PanelInput.Number3
        PanelInput.Number4.value -> PanelInput.Number4
        PanelInput.Number5.value -> PanelInput.Number5
        PanelInput.Number6.value -> PanelInput.Number6
        PanelInput.Number7.value -> PanelInput.Number7
        PanelInput.Number8.value -> PanelInput.Number8
        PanelInput.Number9.value -> PanelInput.Number9
        PanelInput.OperatorPlus.value -> PanelInput.OperatorPlus
        PanelInput.OperatorMinus.value -> PanelInput.OperatorMinus
        PanelInput.OperatorMultiply.value -> PanelInput.OperatorMultiply
        PanelInput.OperatorDivide.value -> PanelInput.OperatorDivide
        PanelInput.CommandReset.value -> PanelInput.CommandReset
        PanelInput.CommandEvaluate.value -> PanelInput.CommandEvaluate
        else -> throw IllegalArgumentException()
    }

}
