package io.pjhjohn.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.pjhjohn.calculator.view.main.ActiveCalculatorFragment

class ActiveCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_calculator)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ActiveCalculatorFragment.newInstance())
                .commitNow()
        }
    }

}
