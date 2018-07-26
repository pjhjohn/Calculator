package io.pjhjohn.calculator.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.databinding.FragmentCalculatorPanelBinding

class CalculatorPanelFragment : Fragment() {

    companion object {
        fun newInstance() = CalculatorPanelFragment()
    }

    private lateinit var binding: FragmentCalculatorPanelBinding
    private lateinit var viewModel: CalculatorViewModel
    lateinit var modelClass: Class<CalculatorViewModel>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(modelClass)
            .also { binding.vm = it }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator_panel, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

}
