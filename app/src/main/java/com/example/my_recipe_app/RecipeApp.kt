package com.example.my_recipe_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun RecipeApp(modifier:Modifier=Modifier,navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screen.Homescreen.route) {
        composable(Screen.Homescreen.route) {
            homepage(
                gotofirst = { navController.navigate(Screen.Recipescreen.route)}
                )



        }
        composable(Screen.Recipescreen.route){
            RecipeScreen( gotodesc = {
                navController.currentBackStackEntry?.savedStateHandle?.set("categoryitem",it)
                navController.navigate(Screen.Descriptionscreen.route)})
        }



        composable(Screen.Descriptionscreen.route){
            val category=navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("categoryitem")?:Category(idCategory = "", strCategory = "", strCategoryThumb = "", strCategoryDescription = "")  //we use ?. to basicallt say that something could be a null to avoid the null point exception and elvis operator (?:) is an if else (in this case if category is null then make an empty category object)
            description_screen(category)
        }


    }
}