package io.pjhjohn.calculator.view.active

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.model.*
import io.pjhjohn.calculator.model.PanelInput.Type.*

class ActiveCalculatorViewModel : ViewModel() {

    val expression: ObservableField<Expression> = ObservableField(Expression())
    val evaluationResult: ObservableField<Operand> = ObservableField(Operand.Empty)

    fun input(value: String) {
        val panelInput = value.asPanelInput()
        val expr = expression.get() ?: return

        expression.set(when(panelInput.type) {
            NUMBER -> {
                when (expr.lastArgument) {
                    Expression.Argument.NONE,
                    Expression.Argument.OPERAND1
                    -> {
                        expr.operand1.update(panelInput).let {
                            evaluationResult.set(it)
                            expr.copy(operand1 = it, operator = Operator.Empty, operand2 = Operand.Empty)
                        }
                    }

                    Expression.Argument.OPERATOR,
                    Expression.Argument.OPERAND2
                    -> {
                        if (expr.operand2.isFresh) {
                            expr.operand1.update(panelInput).let {
                                evaluationResult.set(it)
                                expr.copy(operand1 = it, operator = Operator.Empty, operand2 = Operand.Empty)
                            }
                        } else {
                            expr.operand2.update(panelInput).let {
                                evaluationResult.set(it)
                                expr.copy(operand2 = it)
                            }
                        }
                    }
                }
            }
            OPERATOR -> {
                val operator = panelInput.asOperator()
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
                        if (expr.operand2.isFresh) {
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
            }
            RESET -> {
                evaluationResult.set(Operand.Empty)
                Expression()
            }
            EVALUATE -> {
                when (expr.lastArgument) {
                    Expression.Argument.NONE,
                    Expression.Argument.OPERAND1,
                    Expression.Argument.OPERATOR
                    -> expr

                    Expression.Argument.OPERAND2
                    -> {
                        if (expr.operand2.isFresh) {
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
            }
        })
    }

}
