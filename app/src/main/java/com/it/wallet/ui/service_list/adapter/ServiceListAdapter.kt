package com.it.wallet.ui.service_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.wallet.data.storage.db.CategoryEntity
import com.it.wallet.data.storage.db.ServiceEntity
import com.it.wallet.databinding.CategoryItemBinding
import com.it.wallet.databinding.ServiceItemBinding

class ServiceListAdapter(private val onClick: (v: View,id:Int) -> Unit) :
    RecyclerView.Adapter<ServiceListAdapter.ViewHolder>() {

    private var services: List<ServiceEntity?> = listOf()

    fun submitServices(services: List<ServiceEntity?>?) {
        if (services != null && services.isNotEmpty()) {
            this.services = services
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        services[position]?.let { holder.bind(it,onClick) }
    }

    override fun getItemCount(): Int = services.size

    class ViewHolder(private val vb: ServiceItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(service: ServiceEntity,onClick: (v: View,id:Int) -> Unit) {
            vb.serviceName.text = service.name
            vb.serviceName.setOnClickListener{
                onClick(vb.serviceName,service.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val vb = ServiceItemBinding.inflate(inflater, parent, false)
                return ViewHolder(vb)
            }
        }
    }
}