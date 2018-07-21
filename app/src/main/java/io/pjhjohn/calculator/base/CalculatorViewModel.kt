package io.pjhjohn.calculator.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.model.PanelInput

abstract class CalculatorViewModel(
    val calculator: Calculator
) : ViewModel() {

    val expression: ObservableField<String> = ObservableField(calculator.expr.toString())
    val evaluationResult: ObservableField<String> = ObservableField(calculator.eval.toString())

    abstract fun input(value: PanelInput)
}
