package io.pjhjohn.calculator.view.active

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.calc.ActiveCalculator
import io.pjhjohn.calculator.model.PanelInput
import io.pjhjohn.calculator.model.toPanelInput

class ActiveCalculatorViewModel : ViewModel() {

    val calculator by lazy { ActiveCalculator }

    val expression: ObservableField<String> = object : ObservableField<String>(calculator.expression) {
        override fun get(): String? = calculator.expression.get().toString()
    }

    val evaluationResult: ObservableField<String> = object : ObservableField<String>(calculator.evaluationResult) {
        override fun get(): String? = calculator.evaluationResult.get().toString()
    }

    fun input(value: String) {
        val panelInput = value.toPanelInput()
        when(panelInput.type) {
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
    }
}
