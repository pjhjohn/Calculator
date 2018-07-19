package io.pjhjohn.calculator.model

sealed class Operator {

    object Plus : Operator()
    object Minus : Operator()
    object Multiply : Operator()
    object Divide : Operator()
    object Empty : Operator()

    val isEmpty: Boolean
        get() = this is Empty

    val value: String
        get() = when (this) {
            is Plus -> "+"
            is Minus -> "-"
            is Multiply -> "×"
            is Divide -> "÷"
            is Empty -> throw IllegalArgumentException("Operator is empty")
        }

    override fun toString(): String = when (this) {
        is Plus -> "+"
        is Minus -> "-"
        is Multiply -> "×"
        is Divide -> "÷"
        is Empty -> ""
    }

}

fun PanelInput.asOperator(): Operator = when (this) {
    PanelInput.OperatorPlus -> Operator.Plus
    PanelInput.OperatorMinus -> Operator.Minus
    PanelInput.OperatorMultiply -> Operator.Multiply
    PanelInput.OperatorDivide -> Operator.Divide
    else -> throw IllegalArgumentException("Unsupported Operator($this)")
}
