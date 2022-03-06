package com.it.wallet.ui.histories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.it.wallet.databinding.FragmentNotificationsBinding
import com.it.wallet.ui.app.App
import com.it.wallet.ui.histories.adapter.HistoryAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!
    lateinit var historyViewModel:HistoryViewModel

    private val adapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hisRecycler.adapter = adapter
        adapter.setSender(App.instance?.getPrefs()?.username.toString())
        historyViewModel.getAllData()
        historyViewModel.payments.observe(viewLifecycleOwner) { payments ->
            adapter.submitPayments(payments)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}