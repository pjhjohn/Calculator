package io.pjhjohn.calculator.active

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.passive.PassiveCalculatorActivity

class ActiveCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setTitle(R.string.activity_active_calculator)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.display, ActiveCalculatorDisplayFragment.newInstance())
                .replace(R.id.panel, ActiveCalculatorPanelFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculator, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId ?: return super.onOptionsItemSelected(item)
        when (itemId) {
            R.id.calculator_swap -> startActivity(Intent(this, PassiveCalculatorActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
        }
        return true
    }

}
