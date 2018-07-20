package io.pjhjohn.calculator.calc

import androidx.databinding.ObservableField
import io.pjhjohn.calculator.calc.model.Expression
import io.pjhjohn.calculator.calc.model.Operand
import io.pjhjohn.calculator.calc.model.Operator

interface Calculator {
    val expression: ObservableField<Expression>
    val evaluationResult: ObservableField<Operand>

    fun input(operand: Operand)
    fun input(operator: Operator)
    fun reset()
    fun evaluate()
}
