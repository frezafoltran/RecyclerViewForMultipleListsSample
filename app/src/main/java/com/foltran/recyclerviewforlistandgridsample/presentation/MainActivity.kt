package com.foltran.recyclerviewforlistandgridsample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.foltran.recyclerviewforlistandgridsample.R
import com.foltran.recyclerviewforlistandgridsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.itemClickEvent.observe(this){
            it?.let { Snackbar.make(binding.root, "Item clicked", Snackbar.LENGTH_LONG).show() }
        }

    }

}