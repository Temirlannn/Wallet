package com.it.wallet.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.wallet.data.storage.db.CategoryEntity
import com.it.wallet.databinding.CategoryItemBinding

class HomeAdapter(private val onClick: (v: View,id:Int) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var categories: List<CategoryEntity?> = listOf()

    fun submitCategories(categories: List<CategoryEntity?>?) {
        if (categories != null && categories.isNotEmpty()) {
            this.categories = categories
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent,onClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categories[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = categories.size

    class ViewHolder(private val vb: CategoryItemBinding, private val onClick: (v: View,id:Int) -> Unit) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(category: CategoryEntity) {
            vb.categoryName.text = category.name
            vb.root.setOnClickListener {
                onClick(vb.root,category.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup,onClick: (v: View,id:Int) -> Unit): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val vb = CategoryItemBinding.inflate(inflater, parent, false)
                return ViewHolder(vb,onClick)
            }
        }
    }
}