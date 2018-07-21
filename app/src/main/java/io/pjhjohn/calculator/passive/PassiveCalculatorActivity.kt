package io.pjhjohn.calculator.passive

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.active.ActiveCalculatorActivity

class PassiveCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passive_calculator)
        setTitle(R.string.activity_passive_calculator)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.display, PassiveCalculatorDisplayFragment.newInstance())
                .replace(R.id.panel, PassiveCalculatorPanelFragment.newInstance())
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
            R.id.calculator_swap -> startActivity(Intent(this, ActiveCalculatorActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
        }
        return true
    }

}
