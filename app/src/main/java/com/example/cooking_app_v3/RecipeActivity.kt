package com.example.cooking_app_v3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeActivity : AppCompatActivity() {
    var  myRecipeName : TextView? = null
    var myRecipeIngredients: TextView? = null
    var  myRecipeMethodTitle : TextView? = null
    var  myRecipe : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        myRecipeName =findViewById(R.id.recipe_title_id)
        myRecipeIngredients=findViewById(R.id.recipe_ingredients_id)
        myRecipeMethodTitle=findViewById(R.id.recipe_preparation_method_id)
        myRecipe=findViewById(R.id.recipe_text_id)

        myRecipeName?.text= intent.getExtras()?.getString("recipeTitle")
        myRecipeIngredients?.text = intent.getExtras()?.getString("recipeIngredients")
        myRecipeMethodTitle?.text = intent.getExtras()?.getString("recipeMethod")
        myRecipe?.text = intent.getExtras()?.getString("myRecipe")



    }


}

