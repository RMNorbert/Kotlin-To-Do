package com.example.to_do
import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.databinding.ItemCellBinding
import java.time.format.DateTimeFormatter

class ItemViewHolder(
    private val context: Context,
    private val binding: ItemCellBinding,
    private val clickListener: ItemClickListener
    ): RecyclerView.ViewHolder(binding.root)
    {
        @RequiresApi(Build.VERSION_CODES.O)
        private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindTaskItem(item: Item)
        {
            binding.name.text = item.name

            if (item.isCompleted()){
                binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.completeButton.setImageResource(item.imageResource())
            binding.completeButton.setColorFilter(item.imageColor(context))

            binding.completeButton.setOnClickListener{
                clickListener.completeTaskItem(item)
            }
            binding.taskCellContainer.setOnClickListener{
                clickListener.editTaskItem(item)
            }

            if(item.dueTime != null)
                binding.dueTime.text = timeFormat.format(item.dueTime)
            else
                binding.dueTime.text = ""
        }
}