package io.pjhjohn.calculator.passive

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.active.ActiveCalculatorActivity
import io.pjhjohn.calculator.base.CalculatorActivity
import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.common.CalculatorDisplayFragment
import io.pjhjohn.calculator.common.CalculatorPanelFragment
import io.pjhjohn.calculator.util.Storage

class PassiveCalculatorActivity : CalculatorActivity() {

    override fun swapCalculator() {
        Storage.put(ActiveCalculatorActivity::class.java.name to Storage.LAST_ACTIVITY_CLASSNAME)
        startActivity(Intent(this, ActiveCalculatorActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setTitle(R.string.activity_passive_calculator)
        if (savedInstanceState == null) {
            if (Storage.getString(Storage.LAST_ACTIVITY_CLASSNAME, this::class.java.name) != this::class.java.name) swapCalculator()
            else supportFragmentManager.beginTransaction()
                .replace(R.id.display, CalculatorDisplayFragment.newInstance().apply {
                    @Suppress("UNCHECKED_CAST")
                    modelClass = PassiveCalculatorViewModel::class.java as Class<CalculatorViewModel>
                    arguments = bundleOf(
                        CalculatorDisplayFragment.RESID_BACKGROUND_COLOR to R.color.bgPassiveCalculatorDisplay,
                        CalculatorDisplayFragment.RESID_FOREGROUND_COLOR to R.color.fgPassiveCalculatorDisplay
                    )
                })
                .replace(R.id.panel, CalculatorPanelFragment.newInstance().apply {
                    @Suppress("UNCHECKED_CAST")
                    modelClass = PassiveCalculatorViewModel::class.java as Class<CalculatorViewModel>
                })
                .commitNow()
        }
    }

}
