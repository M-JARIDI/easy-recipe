package com.tpandroid.easyrecipe.util

import com.tpandroid.easyrecipe.BuildConfig

class Constants {

    companion object {
        val BASE_URL = "https://api.spoonacular.com/"
        val API_KEY = BuildConfig.API_KEY

        // API query keys
        const val QUERY_NUMBER = "number"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_API_KEY = "apiKey"
    }
}