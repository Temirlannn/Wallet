package com.it.wallet.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.it.wallet.databinding.FragmentDashboardBinding
import com.it.wallet.ui.app.App
import com.it.wallet.ui.dashboard.adapter.LossesAdapter

class LossesFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val adapter = LossesAdapter{v,id ->onClick(v,id)}

    private val binding get() = _binding!!
    lateinit var lossesViewModel:LossesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lossesViewModel =
            ViewModelProvider(this)[LossesViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onClick(v:View,id:Int){
        lossesViewModel.deletePayment(id)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dashRecyler.adapter = adapter

        //let nullga teksheret
        App.instance?.getPrefs()?.username?.let { adapter.setSender(it) }
        lossesViewModel.getPayments()
        lossesViewModel.payments.observe(viewLifecycleOwner) {payments->
            adapter.submitPayments(payments)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}