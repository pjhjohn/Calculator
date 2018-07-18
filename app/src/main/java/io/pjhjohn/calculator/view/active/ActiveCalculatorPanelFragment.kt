package io.pjhjohn.calculator.view.active

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.pjhjohn.calculator.R

class ActiveCalculatorPanelFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveCalculatorPanelFragment()
    }

    private lateinit var viewModel: ActiveCalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_active_calculator_panel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActiveCalculatorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
