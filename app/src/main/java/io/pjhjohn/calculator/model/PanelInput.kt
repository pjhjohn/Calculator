package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.calc.model.Operand
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

    fun toOperand(): Operand = when (this) {
        Number0 -> Operand.Dirty(0)
        Number1 -> Operand.Dirty(1)
        Number2 -> Operand.Dirty(2)
        Number3 -> Operand.Dirty(3)
        Number4 -> Operand.Dirty(4)
        Number5 -> Operand.Dirty(5)
        Number6 -> Operand.Dirty(6)
        Number7 -> Operand.Dirty(7)
        Number8 -> Operand.Dirty(8)
        Number9 -> Operand.Dirty(9)
        else -> throw IllegalArgumentException("Cannot convert PanelInput($this) to Int")
    }

    fun toOperator(): Operator = when (this) {
        OperatorPlus -> Operator.Plus
        OperatorMinus -> Operator.Minus
        OperatorMultiply -> Operator.Multiply
        OperatorDivide -> Operator.Divide
        else -> throw IllegalArgumentException("Cannot convert PanelInput($this) to Operator")
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
