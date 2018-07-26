package io.pjhjohn.calculator.model

data class Expression(
    val operand1: Operand = Operand.Empty,
    val operator: Operator = Operator.Empty,
    val operand2: Operand = Operand.Empty,
    val locked: Boolean = false,
    val hide: Boolean = true
) {

    enum class Argument {
        NONE, OPERAND1, OPERATOR, OPERAND2
    }

    val lastArgument: Argument
        get() =
            when {
                operand1.isEmpty -> Argument.NONE
                operator.isEmpty -> Argument.OPERAND1
                operand2.isEmpty -> Argument.OPERATOR
                else -> Argument.OPERAND2
            }

    fun evaluate(): Float =
        if (operand1.isEmpty || operand2.isEmpty) throw IllegalStateException("$this is not evaluable")
        else when (operator) {
            Operator.Plus -> operand1.value + operand2.value
            Operator.Minus -> operand1.value - operand2.value
            Operator.Multiply -> operand1.value * operand2.value
            Operator.Divide -> operand1.value / operand2.value
            Operator.Empty -> throw IllegalStateException("$this is not evaluable")
        }

    fun asString(): String =
        if (hide) ""
        else "${operand1.asString()} ${operator.asString()} ${operand2.asString()}"

}
