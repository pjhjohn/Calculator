package io.pjhjohn.calculator.base

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.pjhjohn.calculator.R

abstract class CalculatorActivity : AppCompatActivity() {

    abstract fun swapCalculator()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculator, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId ?: return super.onOptionsItemSelected(item)
        when (itemId) {
            R.id.calculator_swap -> swapCalculator()
        }
        return true
    }

}
