package com.example.cooking_app_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openRecipeListActivity(view: View) {
        val intent =  Intent(this, RecipeListActivity::class.java)
        startActivity(intent)
    }

    fun OpenAddRecipyActivity(view: View) {
        val intent = Intent(this, AddRecipeActivity::class.java)
        startActivity(intent)
    }

    fun openAboutActivity(view: View) {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}