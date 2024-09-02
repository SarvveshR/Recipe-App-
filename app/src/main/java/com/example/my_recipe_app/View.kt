package com.example.my_recipe_app

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

//Fucntions are in the order of being called

//category->Categoryresponse->getcategories returns Categoryresponse->categorystate that holds an instance of Recipe state ->data is being sent to grid
//The lamda function for anviagtion is given to all the fucntions because the if I can;t just call the categoryitems fucntions for naviation as the previous Category scrren is also dependent on it so I bacaically careate a lambda function in every fucntion as they go hand in hand .
//And I call the main recipe function for navigation and send arguemnt to the gotodesc which say ton anviage to the desc screen and it get  passed on to ccategory screen and caegory items eventually and it get fincally called when the user clicks on the column as the clickable ffunc is used
@Composable
fun RecipeScreen(modifier: Modifier = Modifier, gotodesc: (Category) -> Unit) {
    val recipe_viewmodel: MainViewModel = viewModel()
    val viewstate by recipe_viewmodel.categoriesstate

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewstate.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            viewstate.error != null -> {
                Text(text = "ERROR OCCURED: ${viewstate.error}")
            }
            viewstate.list.isNullOrEmpty() -> {
                Text(text = "No categories available", modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                Categoryscreen(categories = viewstate.list, gotodesc2 = gotodesc)
            }
        }
    }
}

@Composable
fun Categoryscreen(categories: List<Category>, gotodesc2: (Category) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(category = category, gotodesc3 = gotodesc2)
        }
    }
}

@Composable
fun CategoryItem(category: Category, gotodesc3: (Category) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { gotodesc3(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

    }
}
