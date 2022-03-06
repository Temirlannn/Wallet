package com.it.wallet.ui.dashboard.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.wallet.data.storage.db.PaymentEntity
import com.it.wallet.databinding.LossesItemBinding

class LossesAdapter (private val onClick: (v: View, id:Int) -> Unit):
    RecyclerView.Adapter<LossesAdapter.ViewHolder>() {

    private var payments: List<PaymentEntity?> = listOf()
    private var name:String ="hello"

    fun submitPayments(payments: List<PaymentEntity?>?) {
        if (payments != null && payments.isNotEmpty()) {
            this.payments = payments
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent,onClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        payments[position]?.let { holder.bind(it,name) }
    }

    override fun getItemCount(): Int = payments.size
    fun setSender(username: String) {
        name = username
    }

    class ViewHolder(private val vb: LossesItemBinding,val onClick: (v: View, id: Int) -> Unit) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(payment: PaymentEntity, name: String) {
            vb.senderName.text = name
            vb.amounts.text = payment.amount.toString()
            vb.comments.text = payment.comment
            vb.data.text = payment.time
            vb.recipient.text = payment.account

            //nazhmi dolgo chtoby udalit
//            vb.root.setOnLongClickListener {
//
//                val builder = AlertDialog.Builder(context)
//                builder.setTitle("Delete?")
//                builder.setMessage("it will be deleted from database as well!!!")
//
//                builder.setPositiveButton(android.R.string.ok) { dialog, which ->
//                    onClick(vb.root,payment.id)
//                }
//
//                builder.setNegativeButton(android.R.string.no) { dialog, which -> }
//                return@setOnLongClickListener true
//            }
        }

        companion object {
            fun from(parent: ViewGroup, onClick: (v: View, id: Int) -> Unit): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val vb = LossesItemBinding.inflate(inflater, parent, false)
                return ViewHolder(vb,onClick)
            }
        }
    }
}