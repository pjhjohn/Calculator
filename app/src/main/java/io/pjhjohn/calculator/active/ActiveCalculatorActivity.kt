package io.pjhjohn.calculator.active

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.base.CalculatorActivity
import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.common.CalculatorDisplayFragment
import io.pjhjohn.calculator.common.CalculatorPanelFragment
import io.pjhjohn.calculator.passive.PassiveCalculatorActivity
import io.pjhjohn.calculator.util.Storage

class ActiveCalculatorActivity : CalculatorActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setTitle(R.string.activity_active_calculator)
        if (savedInstanceState == null) {
            if (Storage.getString(Storage.LAST_ACTIVITY_CLASSNAME, this::class.java.name) == this::class.java.name) initCalculator()
            else swapCalculator()
        } else initCalculator()
    }

    override fun initCalculator() {
        ViewModelProviders.of(this).get(ActiveCalculatorViewModel::class.java).init()
        supportFragmentManager.beginTransaction()
            .replace(R.id.display, CalculatorDisplayFragment.newInstance().apply {
                @Suppress("UNCHECKED_CAST")
                modelClass = ActiveCalculatorViewModel::class.java as Class<CalculatorViewModel>
                arguments = bundleOf(
                    CalculatorDisplayFragment.RESID_BACKGROUND_COLOR to R.color.bgActiveCalculatorDisplay,
                    CalculatorDisplayFragment.RESID_FOREGROUND_COLOR to R.color.fgActiveCalculatorDisplay
                )
            })
            .replace(R.id.panel, CalculatorPanelFragment.newInstance().apply {
                @Suppress("UNCHECKED_CAST")
                modelClass = ActiveCalculatorViewModel::class.java as Class<CalculatorViewModel>
            })
            .commitNow()
    }

    override fun swapCalculator() {
        Storage.put(PassiveCalculatorActivity::class.java.name to Storage.LAST_ACTIVITY_CLASSNAME)
        startActivity(Intent(this, PassiveCalculatorActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }

}
