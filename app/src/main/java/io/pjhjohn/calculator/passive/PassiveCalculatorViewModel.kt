package io.pjhjohn.calculator.passive

import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.model.PanelInput
import io.pjhjohn.calculator.util.Storage

class PassiveCalculatorViewModel : CalculatorViewModel(PassiveCalculator()) {

    companion object {
        val LAST_EVALUATION_KEY = Storage.LAST_EVALUATION_FORMAT.format(PassiveCalculatorViewModel::class.java.simpleName)
    }

    override fun initialize() {
        val operand =
            if (LAST_EVALUATION_KEY in Storage) Operand.Fresh(Storage.getFloat(LAST_EVALUATION_KEY, 0.0f))
            else Operand.Empty

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
        if (calculator.eval.isEmpty.not()) Storage.put(calculator.eval.value to LAST_EVALUATION_KEY)
    }
}
