package com.example.mobdevlab4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdevlab4.databinding.ItemTeamBinding

class TeamsAdapter : ListAdapter<Member, TeamsAdapter.ViewHolder>(diffUtils) {
    companion object {
        private val diffUtils = object : DiffUtil.ItemCallback<Member>() {
            override fun areItemsTheSame(oldItem: Member, newItem: Member) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Member, newItem: Member) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = getItem(position)
    }

    inner class ViewHolder(val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root)
}