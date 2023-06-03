package com.tpandroid.easyrecipe

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tpandroid.easyrecipe.data.Repository
import com.tpandroid.easyrecipe.models.FoodRecipe
import com.tpandroid.easyrecipe.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

// Extend AndroidViewModel because we need application reference in this viewModel
@HiltViewModel
class MainViewModel @Inject constructor(
    private  val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    private var recipesResponeMutableLiveData: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    val recipesRespone = recipesResponeMutableLiveData

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponeMutableLiveData.value = NetworkResult.Loading()

        if(!hasInternetConnection()) {
            recipesResponeMutableLiveData.value = NetworkResult.Error("No Internet Connection")
        }

        try {
            val response = repository.remote.getRecipes(queries)
            recipesResponeMutableLiveData.value = handleFoodRecipesResponse(response)
        }catch (e: Exception){
            recipesResponeMutableLiveData.value = NetworkResult.Error("Recipes not found")
        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("Api key limited")
            }
            response.body()?.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false

        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            listOf(
                NetworkCapabilities.TRANSPORT_WIFI,
                NetworkCapabilities.TRANSPORT_CELLULAR,
                NetworkCapabilities.TRANSPORT_ETHERNET
            ).any { capabilities.hasTransport(it) } -> true
            else -> false

        }
    }
}