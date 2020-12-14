package com.example.cookingapp

class Recipes (name:String?,ingredients:String?,method:String?,recipe:String?, thumbnail: Integer?) {
    var RecipeName:String? =null
    var RecipeIngriedients:String? =null
    var RecipeMethodTitle:String? =null
    var Recipe:String? =null
    var Thumbnail:Integer?=null

    init {
        RecipeName = name
        RecipeIngriedients=ingredients
        RecipeMethodTitle=method
        Recipe = recipe
        Thumbnail=thumbnail
    }


}