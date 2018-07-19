package io.pjhjohn.calculator.view.active

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ActiveCalculatorViewModel : ViewModel() {

    val evaluationResult: ObservableField<String> = ObservableField("initial")

    fun input(value: String) {
        evaluationResult.set(value)
    }

}
