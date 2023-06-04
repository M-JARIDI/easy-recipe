package com.tpandroid.easyrecipe.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tpandroid.easyrecipe.util.Constants
import com.tpandroid.easyrecipe.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.tpandroid.easyrecipe.util.Constants.Companion.QUERY_API_KEY
import com.tpandroid.easyrecipe.util.Constants.Companion.QUERY_DIET
import com.tpandroid.easyrecipe.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.tpandroid.easyrecipe.util.Constants.Companion.QUERY_NUMBER
import com.tpandroid.easyrecipe.util.Constants.Companion.QUERY_TYPE

class RecipesViewModel(application: Application): AndroidViewModel(application) {



     fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_API_KEY] = Constants.API_KEY

        return queries
    }
}