package com.it.wallet.ui.histories.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.wallet.data.storage.db.PaymentEntity
import com.it.wallet.data.storage.db.PaymentWithServiceAndCategory
import com.it.wallet.databinding.HistoryItemBinding
import com.it.wallet.databinding.LossesItemBinding

class HistoryAdapter :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var payments: List<PaymentWithServiceAndCategory?> = listOf()
    private var name: String = "hello"

    fun submitPayments(payments: List<PaymentWithServiceAndCategory?>?) {
        if (payments != null && payments.isNotEmpty()) {
            this.payments = payments
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        payments[position]?.let { holder.bind(it, name) }
    }

    override fun getItemCount(): Int = payments.size


    fun setSender(username: String) {
        name = username
    }

    class ViewHolder(private val vb: HistoryItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(payment: PaymentWithServiceAndCategory, name: String) {
            vb.path.text = "${payment.category.name} ---> ${payment.service.name}"
            vb.amounts.text = payment.paymentTO.amount.toString()
            vb.data.text = payment.paymentTO.time
            vb.senderName.text = name
            vb.recipient.text = payment.paymentTO.account
            vb.comments.text = payment.paymentTO.comment
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val vb = HistoryItemBinding.inflate(inflater, parent, false)
                return ViewHolder(vb)
            }
        }
    }
}