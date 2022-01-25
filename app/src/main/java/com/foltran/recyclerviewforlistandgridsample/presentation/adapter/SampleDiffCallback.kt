package com.foltran.recyclerviewforlistandgridsample.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.foltran.recyclerviewforlistandgridsample.presentation.Item

class SampleDiffCallback: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = when {
        newItem is Item.BeerItem && oldItem is Item.BeerItem -> oldItem.id == newItem.id
        newItem is Item.CoffeeItem && oldItem is Item.CoffeeItem -> oldItem.id == newItem.id
        else -> false
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}