package com.example.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dataViewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCounter()
        loadCats()
        loadOwner()

    }

    private fun loadCounter() {
        lifecycleScope.launchWhenStarted {
            dataViewModel.loadQuantity(this@MainActivity).collect { quantity ->
                binding.textView.text = resources.getString(R.string.quantity) + quantity
            }
        }
    }

    private fun loadCats() {
        lifecycleScope.launchWhenStarted {
            dataViewModel.loadCatList(this@MainActivity).collect { catList ->
                val catsNamesString = catList
                    .joinToString(", ") { it.name }
                binding.tvCats.text = catsNamesString
            }
        }
    }

    private fun loadOwner() {
        lifecycleScope.launchWhenStarted {
            dataViewModel.loadOwner(this@MainActivity).collect { owner ->
                val ownerString = "[${owner.name}, ${owner.address.street}, has ${owner.cats.catListCount} cats]"
                binding.tvOwner.text = ownerString
            }
        }
    }
}