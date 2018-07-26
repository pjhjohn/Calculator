package io.pjhjohn.calculator.model

enum class Operator {

    Plus,
    Minus,
    Multiply,
    Divide,
    Empty;

    val isEmpty: Boolean
        get() = this == Empty

    fun asString(): String =
        when (this) {
            Plus -> "+"
            Minus -> "-"
            Multiply -> "×"
            Divide -> "÷"
            Empty -> ""
        }

}
