package com.tpandroid.easyrecipe.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tpandroid.easyrecipe.viewmodels.MainViewModel
import com.tpandroid.easyrecipe.adapters.RecipesAdapter
import com.tpandroid.easyrecipe.databinding.FragmentRecipesBinding
import com.tpandroid.easyrecipe.util.Constants.Companion.API_KEY
import com.tpandroid.easyrecipe.util.NetworkResult
import com.tpandroid.easyrecipe.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel

    private val recipesAdapter by lazy { RecipesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater)


        setupRecyclerView()

        requestApiData()

        mainViewModel.recipesRespone.observe(viewLifecycleOwner){ response ->
            when(response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { recipesAdapter.setRecipes(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }


        return binding.root
    }

    private fun requestApiData(){
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
    }



    private fun setupRecyclerView(){
        binding.recyclerView.adapter = recipesAdapter
        showShimmerEffect()
    }

    private fun showShimmerEffect(){
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect(){
        binding.recyclerView.hideShimmer()
    }
}