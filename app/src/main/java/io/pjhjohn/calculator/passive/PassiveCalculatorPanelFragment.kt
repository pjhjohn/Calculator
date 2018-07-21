package io.pjhjohn.calculator.passive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.pjhjohn.calculator.R
import io.pjhjohn.calculator.databinding.FragmentPassiveCalculatorPanelBinding

class PassiveCalculatorPanelFragment : Fragment() {

    companion object {
        fun newInstance() = PassiveCalculatorPanelFragment()
    }

    private lateinit var binding: FragmentPassiveCalculatorPanelBinding
    private lateinit var viewModel: PassiveCalculatorViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(PassiveCalculatorViewModel::class.java)
            .also { binding.vm = it }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passive_calculator_panel, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

}
