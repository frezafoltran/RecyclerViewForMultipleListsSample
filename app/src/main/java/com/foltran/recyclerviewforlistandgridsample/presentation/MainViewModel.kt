package com.foltran.recyclerviewforlistandgridsample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.foltran.recyclerviewforlistandgridsample.*
import com.foltran.recyclerviewforlistandgridsample.presentation.adapter.DisplayConfig
import com.foltran.recyclerviewforlistandgridsample.presentation.adapter.ItemClickListener

class MainViewModel: ViewModel(), ItemClickListener {

    private val _items = MutableLiveData<List<Item>>(DEFAULT_COFFEE_ITEMS)
    val items: LiveData<List<Item>> get() = _items

    val isCoffeeItem: LiveData<Boolean> = Transformations.map(_items){
        return@map it[0] is Item.CoffeeItem
    }

    private val _curDisplayConfig = MutableLiveData(DisplayConfig(false, 1))
    val curDisplayConfig: LiveData<DisplayConfig> get() = _curDisplayConfig

    val itemClickEvent = MutableLiveData<Boolean?>(null)


    fun toggleConfig(){
        _curDisplayConfig.value = if (_curDisplayConfig.value?.isGrid == true) {
            DisplayConfig(false, 1)
        } else {
            DisplayConfig(true, if(isCoffeeItem.value == true) 3 else 2)
        }
    }

    fun toggleItems(){
        _items.value = if (isCoffeeItem.value == true) DEFAULT_BEER_ITEMS else DEFAULT_COFFEE_ITEMS
        if (_curDisplayConfig.value?.isGrid == true){
            _curDisplayConfig.value = DisplayConfig(true, if(isCoffeeItem.value == true) 3 else 2)
        }
    }

    override fun onClickItem(item: Item) {
        itemClickEvent.value = itemClickEvent.value != true
    }
}