package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.calc.model.Operator

sealed class PanelInput(val type: Type, val value: String) {

    object Number0 : PanelInput(Type.NUMBER, "0")
    object Number1 : PanelInput(Type.NUMBER, "1")
    object Number2 : PanelInput(Type.NUMBER, "2")
    object Number3 : PanelInput(Type.NUMBER, "3")
    object Number4 : PanelInput(Type.NUMBER, "4")
    object Number5 : PanelInput(Type.NUMBER, "5")
    object Number6 : PanelInput(Type.NUMBER, "6")
    object Number7 : PanelInput(Type.NUMBER, "7")
    object Number8 : PanelInput(Type.NUMBER, "8")
    object Number9 : PanelInput(Type.NUMBER, "9")

    object OperatorPlus : PanelInput(Type.OPERATOR, "+")
    object OperatorMinus : PanelInput(Type.OPERATOR, "-")
    object OperatorMultiply : PanelInput(Type.OPERATOR, "×")
    object OperatorDivide : PanelInput(Type.OPERATOR, "÷")

    object CommandReset : PanelInput(Type.RESET, "c")
    object CommandEvaluate : PanelInput(Type.EVALUATE, "=")

    enum class Type {
        NUMBER, OPERATOR, RESET, EVALUATE
    }

    fun toOperator(): Operator = when (this) {
        OperatorPlus -> Operator.Plus
        OperatorMinus -> Operator.Minus
        OperatorMultiply -> Operator.Multiply
        OperatorDivide -> Operator.Divide
        else -> throw IllegalArgumentException("Cannot convert PanelInput($this) to Operator")
    }

    fun toInt(): Int = when (this) {
        Number0 -> 0
        Number1 -> 1
        Number2 -> 2
        Number3 -> 3
        Number4 -> 4
        Number5 -> 5
        Number6 -> 6
        Number7 -> 7
        Number8 -> 8
        Number9 -> 9
        else -> throw IllegalArgumentException("Cannot convert PanelInput($this) to Int")
    }

}

fun String.toPanelInput(): PanelInput = when (this) {
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
    else -> throw IllegalArgumentException("Unsupported PanelInput($this)")
}
