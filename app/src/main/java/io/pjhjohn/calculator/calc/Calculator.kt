package io.pjhjohn.calculator.calc

import io.pjhjohn.calculator.calc.model.Expression
import io.pjhjohn.calculator.calc.model.Operand
import io.pjhjohn.calculator.calc.model.Operator

interface Calculator {

    var expr: Expression
    var eval: Operand

    fun input(operand: Operand)
    fun input(operator: Operator)
    fun reset()
    fun evaluate()

}
