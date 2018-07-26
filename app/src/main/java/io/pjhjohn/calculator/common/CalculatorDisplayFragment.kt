package io.pjhjohn.calculator.common

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

class CalculatorDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = CalculatorDisplayFragment()
        const val RESID_FOREGROUND_COLOR = "argument_foreground_color"
        const val RESID_BACKGROUND_COLOR = "argument_background_color"
    }

    private lateinit var binding: FragmentCalculatorDisplayBinding
    private lateinit var viewModel: CalculatorViewModel
    lateinit var modelClass: Class<CalculatorViewModel>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(modelClass)
            .also {
                binding.vm = it
                it.initialize()
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator_display, container, false)
        binding.setLifecycleOwner(this)
        arguments?.let { args ->
            binding.calculatorDisplay.setBackgroundColor(ResourcesCompat.getColor(resources, args.getInt(RESID_BACKGROUND_COLOR), null))
            binding.tvEvaluationResult.setTextColor(ResourcesCompat.getColor(resources, args.getInt(RESID_FOREGROUND_COLOR), null))
        }
        return binding.root
    }

}
