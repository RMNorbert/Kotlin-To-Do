package com.example.to_do
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.databinding.ItemCellBinding

class ItemAdapter(
        private val items: List<Item>,
        private val clickListener: ItemClickListener
    ): RecyclerView.Adapter<ItemViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val from = LayoutInflater.from(parent.context)
            val binding = ItemCellBinding.inflate(from, parent, false)
            return ItemViewHolder(parent.context, binding, clickListener)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bindTaskItem(items[position])
        }

        override fun getItemCount(): Int = items.size
    }
