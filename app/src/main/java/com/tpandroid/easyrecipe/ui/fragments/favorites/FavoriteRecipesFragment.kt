package com.tpandroid.easyrecipe.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tpandroid.easyrecipe.databinding.FragmentFavoriteRecipesBinding


class FavoriteRecipesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteRecipesBinding.inflate(layoutInflater)
        return binding.root
    }
}