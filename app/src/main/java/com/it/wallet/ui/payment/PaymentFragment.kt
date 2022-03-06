package com.it.wallet.ui.payment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.it.wallet.R
import com.it.wallet.data.storage.db.PaymentEntity
import com.it.wallet.databinding.FragmentPaymentBinding
import com.it.wallet.ui.app.App
import com.it.wallet.util.getFormattedTime
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null

    private val binding get() = _binding!!

    private var serviceId: Int? = null
    private var categoryId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            serviceId = requireArguments().getInt("serviceId")
            categoryId = requireArguments().getInt("categoryId")
        }
        binding.btnPay.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                categoryId?.let { it1 ->
                    serviceId?.let { it2 ->
                        context?.getFormattedTime(fetchPassedSeconds())?.let { it3 ->
                            PaymentEntity(
                                0,
                                binding.edtAccount.text.toString(),
                                Integer.parseInt(binding.edtSumma.text.toString()),
                                it3,
                                binding.EdtComment.text.toString(),
                                it1,
                                it2
                            )
                        }
                    }
                }?.let { it2 ->
                    App.instance
                        ?.getDatabase()
                        ?.PaymentDao()
                        ?.addPayment(it2)
                }
            }
        }
    }

    private fun fetchPassedSeconds(): Int {
        val calendar = Calendar.getInstance()
        val isDaylightSavingActive = TimeZone.getDefault().inDaylightTime(Date())
        var offset = calendar.timeZone.rawOffset
        if (isDaylightSavingActive) {
            offset += TimeZone.getDefault().dstSavings
        }
        return ((calendar.timeInMillis + offset) / 1000).toInt()
    }
}