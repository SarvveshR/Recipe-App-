package com.example.my_recipe_app

//sealed classes are exactly like normal classes except we can define the objects and subclasses (the classses that inherits this sealed class)

 sealed class Screen(val route:String) {//here we use sealed class to bassically have the routes of the navation

    object Homescreen:Screen("homescreen")
    object  Recipescreen:Screen("firstscreen")
    object Descriptionscreen:Screen("secondscreen" )


}