package com.it.wallet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.it.wallet.R
import com.it.wallet.databinding.FragmentHomeBinding
import com.it.wallet.ui.home.adapter.HomeAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val adapter= HomeAdapter{v,id -> onItemClick(v,id)}

    lateinit var homeViewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         homeViewModel =
             ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recylerv.adapter =adapter
        homeViewModel.getCategories()
        homeViewModel.categories.observe(viewLifecycleOwner) { categories->
            adapter.submitCategories(categories)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(v:View, id: Int) {
        val b = Bundle()
        b.putInt("id",id)
        findNavController().navigate(R.id.serviceListFragment,b)
    }
}