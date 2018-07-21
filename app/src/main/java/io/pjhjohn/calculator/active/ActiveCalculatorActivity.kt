package io.pjhjohn.calculator.active

import android.content.Intent
import android.os.Bundle
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.base.CalculatorActivity
import io.pjhjohn.calculator.passive.PassiveCalculatorActivity
import io.pjhjohn.calculator.util.Storage

class ActiveCalculatorActivity : CalculatorActivity() {

    override fun swapCalculator() {
        Storage.put(PassiveCalculatorActivity::class.java.name to Storage.LAST_ACTIVITY_CLASSNAME)
        startActivity(Intent(this, PassiveCalculatorActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setTitle(R.string.activity_active_calculator)
        if (savedInstanceState == null) {
            if (Storage.getString(Storage.LAST_ACTIVITY_CLASSNAME, this::class.java.name) != this::class.java.name) swapCalculator()
            else supportFragmentManager.beginTransaction()
                .replace(R.id.display, ActiveCalculatorDisplayFragment.newInstance())
                .replace(R.id.panel, ActiveCalculatorPanelFragment.newInstance())
                .commitNow()
        }
    }

}
