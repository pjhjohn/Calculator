package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.UnitTestWatcher
import io.pjhjohn.calculator.model.Expression.Argument.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ExpressionUnitTest : UnitTestWatcher() {

    private val operands = listOf(Operand.Empty, Operand.Fresh(-2.5f), Operand.Dirty(2))
    private val operators = listOf(Operator.Empty, Operator.Plus, Operator.Minus, Operator.Multiply, Operator.Divide)
    private val expressions: MutableList<Expression> = mutableListOf()

    @Before
    fun before() {
        operands.forEach { operand1 ->
            operators.forEach { operator ->
                operands.forEach { operand2 ->
                    expressions.add(Expression(operand1, operator, operand2))
                }
            }
        }
    }

    @After
    fun after() {

    }

    @Test
    fun testOperand1() {
        val expectations = listOf(
            operands[0], operands[0], operands[0],
            operands[0], operands[0], operands[0],
            operands[0], operands[0], operands[0],
            operands[0], operands[0], operands[0],
            operands[0], operands[0], operands[0],

            operands[1], operands[1], operands[1],
            operands[1], operands[1], operands[1],
            operands[1], operands[1], operands[1],
            operands[1], operands[1], operands[1],
            operands[1], operands[1], operands[1],

            operands[2], operands[2], operands[2],
            operands[2], operands[2], operands[2],
            operands[2], operands[2], operands[2],
            operands[2], operands[2], operands[2],
            operands[2], operands[2], operands[2]
        )

        expressions.zip(expectations).forEach { pair ->
            assertThat(pair.first.operand1).isEqualTo(pair.second)
        }
    }

    @Test
    fun testOperator() {
        val expectations = listOf(
            operators[0], operators[0], operators[0],
            operators[1], operators[1], operators[1],
            operators[2], operators[2], operators[2],
            operators[3], operators[3], operators[3],
            operators[4], operators[4], operators[4],

            operators[0], operators[0], operators[0],
            operators[1], operators[1], operators[1],
            operators[2], operators[2], operators[2],
            operators[3], operators[3], operators[3],
            operators[4], operators[4], operators[4],

            operators[0], operators[0], operators[0],
            operators[1], operators[1], operators[1],
            operators[2], operators[2], operators[2],
            operators[3], operators[3], operators[3],
            operators[4], operators[4], operators[4]
        )

        expressions.zip(expectations).forEach { pair ->
            assertThat(pair.first.operator).isEqualTo(pair.second)
        }
    }

    @Test
    fun testOperand2() {
        val expectations = listOf(
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],

            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],

            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2],
            operands[0], operands[1], operands[2]
        )

        expressions.zip(expectations).forEach { pair ->
            assertThat(pair.first.operand2).isEqualTo(pair.second)
        }
    }

    @Test
    fun testLastArgument() {
        val expectations = listOf(
            NONE, NONE, NONE,
            NONE, NONE, NONE,
            NONE, NONE, NONE,
            NONE, NONE, NONE,
            NONE, NONE, NONE,

            OPERAND1, OPERAND1, OPERAND1,
            OPERATOR, OPERAND2, OPERAND2,
            OPERATOR, OPERAND2, OPERAND2,
            OPERATOR, OPERAND2, OPERAND2,
            OPERATOR, OPERAND2, OPERAND2,

            OPERAND1, OPERAND1, OPERAND1,
            OPERATOR, OPERAND2, OPERAND2,
            OPERATOR, OPERAND2, OPERAND2,
            OPERATOR, OPERAND2, OPERAND2,
            OPERATOR, OPERAND2, OPERAND2
        )

        expressions.zip(expectations).forEach { pair ->
            assertThat(pair.first.lastArgument).isEqualTo(pair.second)
        }
    }

    @Test
    fun testEvaluate() {
        val expectations = listOf(
            null, null, null,
            null, null, null,
            null, null, null,
            null, null, null,
            null, null, null,

            null, null, null,
            null, -5.0f, -0.5f,
            null, 0.0f, -4.5f,
            null, 6.25f, -5.0f,
            null, 1.0f, -1.25f,

            null, null, null,
            null, -0.5f, 4.0f,
            null, 4.5f, 0.0f,
            null, -5.0f, 4.0f,
            null, -0.8f, 1.0f
        )

        expressions.zip(expectations).forEach { pair ->
            assertThat(pair.first.evaluate()).isEqualTo(pair.second)
        }
    }

}
