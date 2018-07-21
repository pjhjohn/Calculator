package io.pjhjohn.calculator.passive

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.base.Calculator
import io.pjhjohn.calculator.model.PanelInput

class PassiveCalculatorViewModel : ViewModel() {

    val calculator: Calculator by lazy { PassiveCalculator }

    val expression: ObservableField<String> = ObservableField(calculator.expr.toString())
    val evaluationResult: ObservableField<String> = ObservableField(calculator.eval.toString())

    fun input(value: PanelInput) {
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

        expression.set(calculator.expr.toString())
        evaluationResult.set(calculator.eval.toString())
    }
}
