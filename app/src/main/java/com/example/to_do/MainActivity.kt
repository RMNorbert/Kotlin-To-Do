package com.example.to_do

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity(), ItemClickListener
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding.newTaskButton.setOnClickListener {
            NewItemSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.items.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = it?.let { it1 -> ItemAdapter(it1, mainActivity) }
            }
        }
    }

    override fun editTaskItem(item: Item)
    {
        NewItemSheet(item).show(supportFragmentManager,"newItemTag")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun completeTaskItem(item: Item)
    {
        taskViewModel.setCompleted(item)
    }
}