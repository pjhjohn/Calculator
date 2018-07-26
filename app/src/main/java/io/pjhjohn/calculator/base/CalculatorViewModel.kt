package io.pjhjohn.calculator.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.model.PanelInput

abstract class CalculatorViewModel(
    val calculator: Calculator
) : ViewModel() {

    val expression: MutableLiveData<Expression> = MutableLiveData()
    val evaluationResult: MutableLiveData<Operand> = MutableLiveData()

    abstract fun init()
    abstract fun input(value: PanelInput)
    abstract fun sync()

}
