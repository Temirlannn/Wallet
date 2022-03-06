package com.it.wallet.ui.service_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.it.wallet.R
import com.it.wallet.data.storage.db.ServiceEntity
import com.it.wallet.databinding.FragmentHomeBinding
import com.it.wallet.databinding.FragmentServiceListBinding
import com.it.wallet.ui.home.HomeViewModel
import com.it.wallet.ui.service_list.adapter.ServiceListAdapter


class ServiceListFragment : Fragment() {

    private var _binding: FragmentServiceListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var categoryId: Int? = null
    lateinit var serviceViewModel:ServiceViewModel

    private val adapter: ServiceListAdapter = ServiceListAdapter { v, id -> onClick(v, id) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        serviceViewModel =
            ViewModelProvider(this)[ServiceViewModel::class.java]

        _binding = FragmentServiceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            categoryId = requireArguments().getInt("id")
        }
        binding.serviceRecyler.adapter = adapter
        categoryId?.let { serviceViewModel.getServices(it) }
        serviceViewModel.services.observe(viewLifecycleOwner) { services ->
            adapter.submitServices(services)
        }
    }

    private fun onClick(v: View, id: Int) {
        val bundle = Bundle()
        bundle.putInt("serviceId", id)
        categoryId?.let { bundle.putInt("categoryId", it) }
        findNavController().navigate(R.id.paymentFragment, bundle)
    }
}