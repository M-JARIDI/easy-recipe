package com.tpandroid.easyrecipe.data

import com.tpandroid.easyrecipe.data.network.FoodRecipesApi
import com.tpandroid.easyrecipe.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }
}