package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.calc.model.Operand
import io.pjhjohn.calculator.calc.model.Operator

enum class PanelInput(val value: String) {

    Number0("0"),
    Number1("1"),
    Number2("2"),
    Number3("3"),
    Number4("4"),
    Number5("5"),
    Number6("6"),
    Number7("7"),
    Number8("8"),
    Number9("9"),
    OperatorPlus("+"),
    OperatorMinus("-"),
    OperatorMultiply("×"),
    OperatorDivide("÷"),
    CommandReset("c"),
    CommandEvaluate("=");

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
