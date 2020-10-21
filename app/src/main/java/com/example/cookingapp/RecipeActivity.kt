package com.example.cookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {

    var  myRecipeName : TextView? = null
    var myRecipeIngredients: TextView? = null
    var  myRecipeMethodTitle : TextView? = null
    var  myRecipe : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        myRecipeName = findViewById(R.id.title_recipe)
        myRecipeIngredients = findViewById(R.id.ingredients)
        myRecipeMethodTitle = findViewById(R.id.method)
        myRecipe = findViewById(R.id.recipe)

        var intent = getIntent()
        var Title:String? = intent.getExtras()?.getString("RecipeName")
        var Ingredients:String? = intent.getExtras()?.getString("Ingrdients")
        var MethodTitle:String? = intent.getExtras()?.getString("RecipeMethodTitle")
        var Recipe:String? = intent.getExtras()?.getString("Recipe")
    }
}