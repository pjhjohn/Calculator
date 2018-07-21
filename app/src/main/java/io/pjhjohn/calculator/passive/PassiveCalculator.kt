package io.pjhjohn.calculator.passive

import io.pjhjohn.calculator.base.Calculator
import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.model.Operator

object PassiveCalculator : Calculator {

    override var expr: Expression = Expression()
    override var eval: Operand = Operand.Empty

    override fun input(operand: Operand) {
        expr = if (expr.locked) expr else when (expr.lastArgument) {
            Expression.Argument.NONE,
            Expression.Argument.OPERAND1
            -> {
                expr.operand1.update(operand).let {
                    expr.copy(operand1 = it, operator = Operator.Empty, operand2 = Operand.Empty)
                }
            }

            Expression.Argument.OPERATOR,
            Expression.Argument.OPERAND2
            -> {
                if (expr.operand2 is Operand.Fresh) {
                    expr.operand1.update(operand).let {
                        expr.copy(operand1 = it, operator = Operator.Empty, operand2 = Operand.Empty)
                    }
                } else {
                    expr.operand2.update(operand).let {
                        expr.copy(operand2 = it)
                    }
                }
            }
        }.copy(hide = false)
    }

    override fun input(operator: Operator) {
        expr = if (expr.locked) expr else when (expr.lastArgument) {
            Expression.Argument.NONE
            -> expr

            Expression.Argument.OPERAND1,
            Expression.Argument.OPERATOR
            -> expr.copy(operator = operator, operand2 = Operand.Empty)

            Expression.Argument.OPERAND2
            -> {
                if (expr.operand2 is Operand.Fresh) {
                    expr.copy(operator = operator, operand2 = Operand.Empty)
                } else {
                    expr.copy(locked = true)
                }
            }
        }.copy(hide = false)
    }

    override fun reset() {
        eval = Operand.Empty
        expr = Expression()
    }

    override fun evaluate() {
        expr = when (expr.lastArgument) {
            Expression.Argument.NONE
            -> expr

            Expression.Argument.OPERAND1
            -> {
                eval = Operand.Fresh(expr.operand1.value)
                expr.copy(operand1 = Operand.Fresh(expr.operand1.value))
            }

            Expression.Argument.OPERATOR
            -> expr

            Expression.Argument.OPERAND2
            -> {
                if (expr.operand2 is Operand.Fresh) {
                    expr.evaluate()?.let {
                        eval = Operand.Fresh(it)
                        expr.copy(operand1 = Operand.Fresh(it))
                    } ?: expr
                } else {
                    expr.evaluate()?.let {
                        eval = Operand.Fresh(it)
                        expr.copy(operand1 = Operand.Fresh(it), operand2 = Operand.Fresh(expr.operand2.value))
                    } ?: expr
                }
            }
        }.copy(locked = false, hide = true)
    }

}
