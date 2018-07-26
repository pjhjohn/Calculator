package io.pjhjohn.calculator.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.databinding.FragmentCalculatorDisplayBinding

class ActiveCalculatorDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveCalculatorDisplayFragment()
    }

    private lateinit var binding: FragmentCalculatorDisplayBinding
    private lateinit var viewModel: CalculatorViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(ActiveCalculatorViewModel::class.java)
            .also {
                binding.vm = it
                it.initialize()
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator_display, container, false)
        binding.setLifecycleOwner(this)
        binding.calculatorDisplay.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.bgActiveCalculatorDisplay, null))
        binding.tvEvaluationResult.setTextColor(ResourcesCompat.getColor(resources, R.color.fgActiveCalculatorDisplay, null))
        return binding.root
    }

}
