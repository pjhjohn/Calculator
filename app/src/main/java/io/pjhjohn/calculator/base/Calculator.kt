package io.pjhjohn.calculator.base

import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.model.Operator

interface Calculator {

    var expr: Expression
    var eval: Operand

    fun input(operand: Operand)
    fun input(operator: Operator)
    fun reset()
    fun evaluate()

}
