package com.foltran.recyclerviewforlistandgridsample.presentation.adapter

import com.foltran.recyclerviewforlistandgridsample.presentation.Item

interface ItemClickListener {
    fun onClickItem(item: Item)
}