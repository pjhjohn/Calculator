package io.pjhjohn.calculator.calc.model

enum class Operator {

    Plus,
    Minus,
    Multiply,
    Divide,
    Empty;

    val isEmpty: Boolean
        get() = this == Empty

    override fun toString(): String =
        when (this) {
            Plus -> "+"
            Minus -> "-"
            Multiply -> "×"
            Divide -> "÷"
            Empty -> ""
        }

}
