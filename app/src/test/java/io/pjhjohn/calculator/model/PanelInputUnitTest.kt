package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.UnitTestWatcher
import io.pjhjohn.calculator.model.PanelInput.*
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class PanelInputUnitTest : UnitTestWatcher() {

    private val panelInputs = listOf(
        Number0,
        Number1,
        Number2,
        Number3,
        Number4,
        Number5,
        Number6,
        Number7,
        Number8,
        Number9,
        OperatorPlus,
        OperatorMinus,
        OperatorMultiply,
        OperatorDivide,
        CommandReset,
        CommandEvaluate
    )

    @Before
    fun before() {

    }

    @After
    fun after() {

    }

    @Test
    fun testToOperand() {
        assertThat(true).isTrue()

        val expectations = listOf(
            Operand.Dirty(0),
            Operand.Dirty(1),
            Operand.Dirty(2),
            Operand.Dirty(3),
            Operand.Dirty(4),
            Operand.Dirty(5),
            Operand.Dirty(6),
            Operand.Dirty(7),
            Operand.Dirty(8),
            Operand.Dirty(9),
            null,
            null,
            null,
            null,
            null,
            null
        )

        panelInputs.zip(expectations).forEach { pair ->
            if (pair.second != null) assertThat(pair.first.toOperand()).isEqualToComparingFieldByField(pair.second)
            else assertThatIllegalArgumentException().isThrownBy { pair.first.toOperand() }
        }
    }

    @Test
    fun testToOperator() {
        assertThat(true).isTrue()

        val expectations = listOf(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            Operator.Plus,
            Operator.Minus,
            Operator.Multiply,
            Operator.Divide,
            null,
            null
        )

        panelInputs.zip(expectations).forEach { pair ->
            if (pair.second != null) assertThat(pair.first.toOperator()).isEqualTo(pair.second)
            else assertThatIllegalArgumentException().isThrownBy { pair.first.toOperator() }
        }
    }

}
