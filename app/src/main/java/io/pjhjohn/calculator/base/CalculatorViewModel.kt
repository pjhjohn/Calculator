package io.pjhjohn.calculator.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.pjhjohn.calculator.model.PanelInput

abstract class CalculatorViewModel(
    val calculator: Calculator
) : ViewModel() {

    val expression: MutableLiveData<String> = MutableLiveData()
    val evaluationResult: MutableLiveData<String> = MutableLiveData()

    abstract fun input(value: PanelInput)
}
