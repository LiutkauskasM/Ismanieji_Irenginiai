package com.example.cooking_app_v3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey(autoGenerate = true) val recipeId: Int,
    val recipeTitle:String?,
    val recipeIngredients:String?,
    val recipeMethodTitle:String?,
    val myRecipe:String?,
    val recipeThumbnail: Int?
)