package com.example.cooking_app_v3

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RecipeDao {
    @Insert
    fun insert(recipe:Recipe):Single<Long>

    @Delete
    fun delete(recipe:Recipe) :Completable

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Single<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE recipeId IN (:recipeID)")
    fun getRecipeByID(recipeID:Int) :Single<Recipe>
    @Update
    fun update(recipe:Recipe):Completable
}