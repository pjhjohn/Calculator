package io.pjhjohn.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.pjhjohn.calculator.view.active.ActiveCalculatorDisplayFragment
import io.pjhjohn.calculator.view.active.ActiveCalculatorPanelFragment

class ActiveCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_calculator)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.display, ActiveCalculatorDisplayFragment.newInstance())
                .replace(R.id.panel, ActiveCalculatorPanelFragment.newInstance())
                .commitNow()
        }
    }

}
