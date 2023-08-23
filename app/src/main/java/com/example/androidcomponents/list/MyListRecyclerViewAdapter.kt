package com.example.androidcomponents.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcomponents.databinding.ListItemElementBinding
import com.example.androidcomponents.placeholder.PlaceholderContent.PlaceholderItem


class MyListRecyclerViewAdapter(
    private val onClickListener: (String) -> Unit
) : RecyclerView.Adapter<MyListRecyclerViewAdapter.ViewHolder>() {

    private lateinit var values: List<PlaceholderItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.content
        holder.itemView.setOnClickListener {
            onClickListener(item.id)
        }

    }

    fun setData(values: List<PlaceholderItem>) {
        this.values = values
        notifyItemRangeChanged(0,19)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ListItemElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}