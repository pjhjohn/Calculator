package io.pjhjohn.calculator.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.base.CalculatorViewModel
import io.pjhjohn.calculator.databinding.FragmentCalculatorDisplayBinding
import io.pjhjohn.calculator.model.Expression
import io.pjhjohn.calculator.model.Operand
import io.pjhjohn.calculator.util.Storage

class ActiveCalculatorDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveCalculatorDisplayFragment()
    }

    private lateinit var binding: FragmentCalculatorDisplayBinding
    private lateinit var viewModel: CalculatorViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(ActiveCalculatorViewModel::class.java)
            .also { vm ->
                binding.vm = vm

                val key = Storage.LAST_EVALUATION_FORMAT.format(vm::class.java.simpleName)
                val value = Storage.getStringNullable(key)?.toFloatOrNull()
                val operand = if (value != null) Operand.Fresh(value) else Operand.Empty
                vm.calculator.expr = Expression(operand)
                vm.calculator.eval = operand
                vm.evaluationResult.value = operand.toString()
                vm.evaluationResult.observe(requireActivity(), Observer { it ->
                    Storage.put(it to key)
                })
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
