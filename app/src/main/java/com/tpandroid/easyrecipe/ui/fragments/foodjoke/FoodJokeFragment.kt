package com.tpandroid.easyrecipe.ui.fragments.foodjoke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tpandroid.easyrecipe.databinding.FragmentFoodJokeBinding

class FoodJokeFragment : Fragment() {

    private lateinit var binding: FragmentFoodJokeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodJokeBinding.inflate(layoutInflater)
        return binding.root
    }
}