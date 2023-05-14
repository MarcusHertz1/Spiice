package com.example.spiice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spiice.databinding.RecyclerViewItemLayoutBinding

class Adapter(private var itemsList: List<AdapterItem>, private var callDeleteDialog: (id: Int) -> Unit ) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(val binding: RecyclerViewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(itemsList[position]) {
                binding.deleteBt.setOnClickListener {
                    callDeleteDialog(this.id)
                }
                binding.titleTV.text = this.title
                binding.messageTV.text = this.message
                binding.dateTV.text = this.date
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}