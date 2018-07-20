package io.pjhjohn.calculator.calc

import androidx.databinding.ObservableField
import io.pjhjohn.calculator.calc.model.Expression
import io.pjhjohn.calculator.calc.model.Operand
import io.pjhjohn.calculator.calc.model.Operator

object ActiveCalculator : Calculator {

    override val expression: ObservableField<Expression> = ObservableField(Expression())
    override val evaluationResult: ObservableField<Operand> = ObservableField(Operand.Empty)

    override fun input(operand: Operand) {
        val expr = expression.get() ?: return
        expression.set(
            when (expr.lastArgument) {
                Expression.Argument.NONE,
                Expression.Argument.OPERAND1
                -> {
                    expr.operand1.update(operand).let {
                        evaluationResult.set(it)
                        expr.copy(operand1 = it, operator = Operator.Empty, operand2 = Operand.Empty)
                    }
                }

                Expression.Argument.OPERATOR,
                Expression.Argument.OPERAND2
                -> {
                    if (expr.operand2 is Operand.Fresh) {
                        expr.operand1.update(operand).let {
                            evaluationResult.set(it)
                            expr.copy(operand1 = it, operator = Operator.Empty, operand2 = Operand.Empty)
                        }
                    } else {
                        expr.operand2.update(operand).let {
                            evaluationResult.set(it)
                            expr.copy(operand2 = it)
                        }
                    }
                }
            }
        )
    }

    override fun input(operator: Operator) {
        val expr = expression.get() ?: return
        expression.set(
            when (expr.lastArgument) {
                Expression.Argument.NONE
                -> expr

                Expression.Argument.OPERAND1,
                Expression.Argument.OPERATOR
                -> {
                    evaluationResult.set(Operand.Fresh(expr.operand1.value))
                    expr.copy(operator = operator, operand2 = Operand.Empty)
                }

                Expression.Argument.OPERAND2
                -> {
                    if (expr.operand2 is Operand.Fresh) {
                        evaluationResult.set(Operand.Fresh(expr.operand1.value))
                        expr.copy(operator = operator, operand2 = Operand.Empty)
                    } else {
                        expr.evaluate()?.let {
                            evaluationResult.set(Operand.Fresh(it))
                            expr.copy(operand1 = Operand.Fresh(it), operator = operator, operand2 = Operand.Empty)
                        }
                    }
                }
            }
        )
    }

    override fun reset() {
        evaluationResult.set(Operand.Empty)
        expression.set(Expression())
    }

    override fun evaluate() {
        val expr = expression.get() ?: return
        expression.set(
            when (expr.lastArgument) {
                Expression.Argument.NONE,
                Expression.Argument.OPERAND1,
                Expression.Argument.OPERATOR
                -> expr

                Expression.Argument.OPERAND2
                -> {
                    if (expr.operand2 is Operand.Fresh) {
                        expr.evaluate()?.let {
                            evaluationResult.set(Operand.Fresh(it))
                            expr.copy(operand1 = Operand.Fresh(it))
                        }
                    } else {
                        expr.evaluate()?.let {
                            evaluationResult.set(Operand.Fresh(it))
                            expr.copy(operand1 = Operand.Fresh(it), operand2 = Operand.Fresh(expr.operand2.value))
                        }
                    }
                }
            }
        )
    }

}
