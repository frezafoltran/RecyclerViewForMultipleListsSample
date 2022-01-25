package com.foltran.recyclerviewforlistandgridsample.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foltran.recyclerviewforlistandgridsample.databinding.GridItemBeerBinding
import com.foltran.recyclerviewforlistandgridsample.databinding.GridItemCoffeeBinding
import com.foltran.recyclerviewforlistandgridsample.databinding.ListItemBeerBinding
import com.foltran.recyclerviewforlistandgridsample.databinding.ListItemCoffeeBinding
import com.foltran.recyclerviewforlistandgridsample.presentation.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

class Adapter(private val config: DisplayConfig, val clickListener: ItemClickListener) :
    ListAdapter<Item, RecyclerView.ViewHolder>(SampleDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun submitListInScope(list: List<Item>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType){
        BEER_ITEM_TYPE -> BeerItemViewHolder.from(parent, config)
        COFFEE_ITEM_TYPE -> CoffeeItemViewHolder.from(parent, config)
        else -> throw ClassCastException("Unknown viewType: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is BeerItemViewHolder -> holder.bind(getItem(position) as Item.BeerItem, clickListener)
            is CoffeeItemViewHolder -> holder.bind(getItem(position) as Item.CoffeeItem, clickListener)
        }
    }

    override fun getItemViewType(position: Int) = when(getItem(position)){
        is Item.BeerItem -> BEER_ITEM_TYPE
        is Item.CoffeeItem -> COFFEE_ITEM_TYPE
    }

    class BeerItemViewHolder private constructor(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item.BeerItem, clickListener: ItemClickListener) {

            when(binding){
                is ListItemBeerBinding -> {
                    binding.item = item
                    binding.clickListener = clickListener
                }
                is GridItemBeerBinding -> {
                    binding.item = item
                    binding.clickListener = clickListener
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, config: DisplayConfig): BeerItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)

                return BeerItemViewHolder(
                    if (config.isGrid) GridItemBeerBinding.inflate(
                        inflater,
                        parent,
                        false
                    ) else ListItemBeerBinding.inflate(inflater, parent, false)
                )
            }
        }
    }

    class CoffeeItemViewHolder private constructor(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item.CoffeeItem, clickListener: ItemClickListener) {

            when(binding){
                is ListItemCoffeeBinding -> {
                    binding.item = item
                    binding.clickListener = clickListener
                }
                is GridItemCoffeeBinding -> {
                    binding.item = item
                    binding.clickListener = clickListener
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, config: DisplayConfig): CoffeeItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)

                return CoffeeItemViewHolder(
                    if (config.isGrid) GridItemCoffeeBinding.inflate(
                        inflater,
                        parent,
                        false
                    ) else ListItemCoffeeBinding.inflate(inflater, parent, false)
                )
            }
        }
    }

    companion object {
        const val COFFEE_ITEM_TYPE = 0
        const val BEER_ITEM_TYPE = 1
    }

}