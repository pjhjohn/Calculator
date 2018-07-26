package io.pjhjohn.calculator.active

import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.model.PanelInput
import io.pjhjohn.calculator.util.Storage

class ActiveCalculatorViewModel : CalculatorViewModel(ActiveCalculator()) {

    companion object {
        val LAST_EVALUATION_KEY = Storage.LAST_EVALUATION_FORMAT.format(ActiveCalculatorViewModel::class.java.simpleName)
    }

    override fun initialize() {
        val value = Storage.getStringNullable(LAST_EVALUATION_KEY)?.toFloatOrNull()
        val operand = if (value != null) Operand.Fresh(value) else Operand.Empty

        calculator.expr = Expression(operand)
        calculator.eval = operand
        evaluationResult.value = operand.toString()
    }

    override fun input(value: PanelInput) {
        when (value) {
            PanelInput.Number0,
            PanelInput.Number1,
            PanelInput.Number2,
            PanelInput.Number3,
            PanelInput.Number4,
            PanelInput.Number5,
            PanelInput.Number6,
            PanelInput.Number7,
            PanelInput.Number8,
            PanelInput.Number9
            -> calculator.input(value.toOperand())

            PanelInput.OperatorPlus,
            PanelInput.OperatorMinus,
            PanelInput.OperatorMultiply,
            PanelInput.OperatorDivide
            -> calculator.input(value.toOperator())

            PanelInput.CommandReset
            -> calculator.reset()

            PanelInput.CommandEvaluate
            -> calculator.evaluate()
        }

        expression.value = calculator.expr.toString()
        evaluationResult.value = calculator.eval.toString()
        Storage.put(calculator.eval.toString() to LAST_EVALUATION_KEY)
    }
}
