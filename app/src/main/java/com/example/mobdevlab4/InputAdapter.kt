package com.example.mobdevlab4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdevlab4.databinding.ItemInputBinding

class InputAdapter : ListAdapter<Member, InputAdapter.ViewHolder>(diffUtils) {
    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<Member>() {
            override fun areItemsTheSame(oldItem: Member, newItem: Member) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Member, newItem: Member) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.adapter = this
        holder.binding.item = getItem(position)
    }

    fun onClick(item: Member) {
        item.checked = !item.checked
        notifyItemChanged(currentList.indexOf(item))
    }

    inner class ViewHolder(val binding: ItemInputBinding) :
        RecyclerView.ViewHolder(binding.root)
}