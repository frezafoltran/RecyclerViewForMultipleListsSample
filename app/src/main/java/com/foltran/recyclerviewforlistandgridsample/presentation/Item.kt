package com.foltran.recyclerviewforlistandgridsample.presentation

import com.foltran.recyclerviewforlistandgridsample.beerImageUrls
import com.foltran.recyclerviewforlistandgridsample.beerLabels
import com.foltran.recyclerviewforlistandgridsample.coffeeImageUrls
import com.foltran.recyclerviewforlistandgridsample.coffeeLabels

sealed class Item {
    data class CoffeeItem (val id: Int, val coffeeUrl: String, val coffeeLabel: String): Item()
    data class BeerItem (val id: Int, val beerUrl: String, val beerLabel: String): Item()
}


val DEFAULT_COFFEE_ITEMS: List<Item.CoffeeItem> = coffeeImageUrls.mapIndexed { i, url ->
    Item.CoffeeItem(i, url, coffeeLabels[i])
}

val DEFAULT_BEER_ITEMS: List<Item.BeerItem> = beerImageUrls.mapIndexed { i, url ->
    Item.BeerItem(i, url, beerLabels[i])
}


