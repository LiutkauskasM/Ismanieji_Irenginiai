package com.example.cooking_app_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RecipeActivity : AppCompatActivity() {
    lateinit var db:AppDatabase
    var  myRecipeName : TextView? = null
    var myRecipeIngredients: TextView? = null
    var  myRecipeMethodTitle : TextView? = null
    var  myRecipe : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

         myRecipeName = findViewById<TextView>(R.id.recipe_title_id)
         myRecipeIngredients = findViewById<TextView>(R.id.recipe_ingredients_id)
         myRecipeMethodTitle = findViewById<TextView>(R.id.recipe_preparation_method_id)
         myRecipe = findViewById<TextView>(R.id.recipe_text_id)

        var title:String? = intent.getExtras()?.getString("title")
        var ingredients:String? = intent.getExtras()?.getString("ngredients")
        var methodTitle:String? = intent.getExtras()?.getString("Method")
        var recipe:String? = intent.getExtras()?.getString("Recipe")

        if (title != null)
        {
            findViewById<EditText>(R.id.recipe_title_id).setText(title)
        }
        if(ingredients != null)
        {
            findViewById<EditText>(R.id.recipe_ingredients_id).setText(ingredients)
        }
        if(methodTitle != null)
        {
            findViewById<EditText>(R.id.recipe_preparation_method_id).setText(methodTitle)
        }
        if(recipe != null)
        {
            findViewById<EditText>(R.id.recipe_text_id).setText(recipe)
        }

    }


    }

