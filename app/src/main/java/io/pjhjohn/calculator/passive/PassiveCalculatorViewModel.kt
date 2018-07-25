package io.pjhjohn.calculator.passive

import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.model.PanelInput

class PassiveCalculatorViewModel : CalculatorViewModel(PassiveCalculator()) {

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
    }
}
