package com.example.to_do

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
class ItemViewModel : ViewModel() {
        var items = MutableLiveData<MutableList<Item>?>()

        init {
            items.value = mutableListOf()
        }

        fun addItem(newItem: Item)
        {
            val list = items.value
            list!!.add(newItem)
            items.postValue(list)
        }

        fun updateItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
        {
            val list = items.value
            val task = list!!.find { it.id == id }!!
            task.name = name
            task.desc = desc
            task.dueTime = dueTime
            items.postValue(list)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun setCompleted(item: Item)
        {
            val list = items.value
            val task = list!!.find { it.id == item.id }!!
            if (task.completedDate == null)
                task.completedDate = LocalDate.now()
            items.postValue(list)
        }
    }