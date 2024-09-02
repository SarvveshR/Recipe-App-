package com.example.my_recipe_app

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private  var retrofit=Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
.addConverterFactory(GsonConverterFactory.create())
.build()


val recipeService= retrofit.create(ApiService::class.java)//retrofit that uses the interface

interface ApiService {
    //contains the http request or API ccall that gets appended to the base URL in terms of process gets sent to the base URl throught Heeo
    @GET("categories.php")//The app makes a URL that is the address of the api of that server ."categroies .php.is the end point of the URL  
    suspend fun getCategories(): CategoriesResponse// suspend keyword is used to stop the fucntion in between and can resume when needed


}
