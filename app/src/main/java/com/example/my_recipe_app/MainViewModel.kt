package com.example.my_recipe_app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private val _categoryState= mutableStateOf(RecipeState())//holds an instrance of the data class ReicipeState
    val categoriesstate: State<RecipeState> = _categoryState
    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {//this uses suspend function that can be paused and resumed anytime
            try {
                val response = recipeService.getCategories()//is the a variable that holds the fucntion getCategories whihc returns ab object of category response which makes this varaible an instance of categoryresponse object
                _categoryState.value = _categoryState.value.copy(//THe immutable list is being modified into a new one with the category objects .old list is discarded and a new one is created
                    list = response.categories,//getCategoires->Categoryresponse obj(Returned an object ,which is now actually the function itself ) response .categories
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error fetching categories: ${e.message}"
                )
                e.printStackTrace() // Add this line to print the stack trace
            }
        }
    }



    data class RecipeState(
        val loading:Boolean =true,
        val list:List<Category> = emptyList(),
        val error:String?=null

    )

}