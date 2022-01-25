package com.foltran.recyclerviewforlistandgridsample.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foltran.recyclerviewforlistandgridsample.R
import com.foltran.recyclerviewforlistandgridsample.presentation.Item
import com.squareup.picasso.Picasso

@BindingAdapter("items", "itemClick", "curDisplayConfig")
internal fun RecyclerView.setSampleRecyclerView(
    items: List<Item>,
    itemClick: ItemClickListener,
    curDisplayConfig: DisplayConfig
) {

    layoutManager =
        if (curDisplayConfig.isGrid) GridLayoutManager(context, curDisplayConfig.numColumns)
        else LinearLayoutManager(context)

    adapter = Adapter(curDisplayConfig, itemClick)
    (adapter as Adapter).submitListInScope(items)
}

@BindingAdapter("imageUrl")
fun ImageView.loadImageUrl(imageUrl: String?){
    imageUrl?.let{ url ->
        Picasso.with(context)
            .load(url).also{
            context.getDrawable(R.drawable.ic_hourglass_empty)?.let{ drawable ->
                it.placeholder(drawable)
            }
            }
            .into(this)
    }

}