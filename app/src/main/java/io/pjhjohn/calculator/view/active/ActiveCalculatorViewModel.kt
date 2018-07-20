package io.pjhjohn.calculator.view.active

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.calc.ActiveCalculator
import io.pjhjohn.calculator.calc.Calculator
import io.pjhjohn.calculator.model.PanelInput
import io.pjhjohn.calculator.model.toPanelInput

class ActiveCalculatorViewModel : ViewModel() {

    val calculator: Calculator by lazy { ActiveCalculator }

    val expression: ObservableField<String> = ObservableField(calculator.expr.toString())
    val evaluationResult: ObservableField<String> = ObservableField(calculator.eval.toString())

    fun input(value: String) {
        val panelInput = value.toPanelInput()
        when (panelInput.type) {
            PanelInput.Type.NUMBER -> {
                calculator.input(panelInput.toOperand())
            }
            PanelInput.Type.OPERATOR -> {
                calculator.input(panelInput.toOperator())
            }
            PanelInput.Type.RESET -> {
                calculator.reset()
            }
            PanelInput.Type.EVALUATE -> {
                calculator.evaluate()
            }
        }

        expression.set(calculator.expr.toString())
        evaluationResult.set(calculator.eval.toString())
    }
}
