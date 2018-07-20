package io.pjhjohn.calculator.view.active

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.calc.ActiveCalculator

class ActiveCalculatorViewModel : ViewModel() {

    val calculator by lazy { ActiveCalculator }

    val expression: ObservableField<String> = object : ObservableField<String>(calculator.expression) {
        override fun get(): String? = calculator.expression.get().toString()
    }

    val evaluationResult: ObservableField<String> = object : ObservableField<String>(calculator.evaluationResult) {
        override fun get(): String? = calculator.evaluationResult.get().toString()
    }

    fun input(value: String) = calculator.input(value)
}
