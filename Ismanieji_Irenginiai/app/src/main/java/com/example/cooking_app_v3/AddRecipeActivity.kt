package com.example.cooking_app_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AddRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
    }
    fun onClick(v: View?)
    {
        if(v?.id==R.id.cancel_btn)
        {
            setResult(RESULT_CANCELED)
            finish()
        }
        else if(v?.id==R.id.submit_btn)
        {
            val titleText = findViewById<EditText>(R.id.recipe_name_edit_id)
            val ingredientsText = findViewById<EditText>(R.id.recipe_ingredients_edit_id)
            val prepMethodText= findViewById<EditText>(R.id.preparation_method_id)
            val recipeText = findViewById<EditText>(R.id.recipe_edit_id)

            if(titleText.text.isEmpty() || ingredientsText.text.isEmpty() || prepMethodText.text.isEmpty()||recipeText.text.isEmpty())
            {
                Toast.makeText(this, "įsitikinkite ar visi laukai užpildyti!", Toast.LENGTH_LONG).show()
                return;
            }

            val intent = Intent()
            intent.putExtra("title",titleText.text.toString())
            intent.putExtra("ingredients",ingredientsText.text.toString())
            intent.putExtra("Method",prepMethodText.text.toString())
            intent.putExtra("Recipe", recipeText.text.toString())
            val pos = this.intent?.extras?.getInt("Position",-1)
            if (pos!=-1)
            {
                intent.putExtra("position",pos)
            }

            setResult(RESULT_OK,intent)
            finish()

        }

    }
}