package io.pjhjohn.calculator.model

data class Expression(
    val operand1: Operand = Operand.Empty,
    val operator: Operator = Operator.Empty,
    val operand2: Operand = Operand.Empty,
    val hide: Boolean = true
) {

    val lastArgument: Argument
        get() =
            when {
                operand1.isEmpty -> Argument.NONE
                operator.isEmpty -> Argument.OPERAND1
                operand2.isEmpty -> Argument.OPERATOR
                else -> Argument.OPERAND2
            }

    fun evaluate(): Float? {
        return if (operand1.isEmpty || operand2.isEmpty) null
        else when (operator) {
            Operator.Plus -> operand1.value + operand2.value
            Operator.Minus -> operand1.value - operand2.value
            Operator.Multiply -> operand1.value * operand2.value
            Operator.Divide -> operand1.value / operand2.value
            Operator.Empty -> null
        }
    }

    enum class Argument {
        NONE, OPERAND1, OPERATOR, OPERAND2
    }

    override fun toString(): String =
        if (hide) ""
        else "$operand1 $operator $operand2"

}
