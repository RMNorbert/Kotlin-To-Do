package com.example.to_do

interface ItemClickListener
{
    fun editTaskItem(item: Item)
    fun completeTaskItem(item: Item)
}