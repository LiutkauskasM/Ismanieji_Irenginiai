package com.example.cookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PersonalRecipes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_recipes)
    }
    fun openAddPersonalRecipe(view: View){
        val intent = Intent(this,AddPersonalRecipeActivity::class.java)
        startActivity(intent)
    }
}