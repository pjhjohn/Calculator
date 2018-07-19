package io.pjhjohn.calculator.view.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.databinding.FragmentActiveCalculatorDisplayBinding

class ActiveCalculatorDisplayFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveCalculatorDisplayFragment()
    }

    private lateinit var binding: FragmentActiveCalculatorDisplayBinding
    private lateinit var viewModel: ActiveCalculatorViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(ActiveCalculatorViewModel::class.java)
            .also { binding.vm = it }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_active_calculator_display, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

}
