package com.example.cooking_app_v3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddRecipeActivity : AppCompatActivity(){
    lateinit var spinnerAdapter:SpinnerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val icons: Array<Int> = arrayOf(R.drawable.cherries,R.drawable.chicken_leg,R.drawable.cooking,+
        R.drawable.harvest,R.drawable.hot_dog,R.drawable.ice_cream,R.drawable.pizza,+
        R.drawable.spaguetti,R.drawable.steak)
        spinnerAdapter= SpinnerAdapter(this,icons)

        var spinner:Spinner = findViewById(R.id.spinner_id)
        spinner.adapter =spinnerAdapter


        val editMode = intent?.extras?.getBoolean("edit")
        val title:String? = intent.getExtras()?.getString("recipeTitle")
        val ingredients:String? = intent.getExtras()?.getString("recipeIngredients")
        val methodTitle:String? = intent.getExtras()?.getString("recipeMethod")
        val recipe:String? = intent.getExtras()?.getString("myRecipe")
        val thumbnail:Int? = intent.getIntExtra("recipeThumbnail",-1)


        if(editMode!=null&&editMode)
        {
            if(title!=null)
            {
                findViewById<EditText>(R.id.recipe_name_edit_id).setText(title)
            }
            if(ingredients!=null)
            {
                findViewById<EditText>(R.id.recipe_ingredients_edit_id).setText(ingredients)
            }
            if(methodTitle!= null)
            {
                findViewById<EditText>(R.id.method_edit_id).setText(methodTitle)
            }
            if(recipe!=null)
            {
                findViewById<EditText>(R.id.recipe_edit_id).setText(recipe)
            }
            if(thumbnail!=-1)
            {
                val spinnerPos:Int =spinnerAdapter.getPosition(thumbnail)
                findViewById<Spinner>(R.id.spinner_id).setPopupBackgroundResource(spinnerPos)
            }

        }
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
            val prepMethodText= findViewById<EditText>(R.id.method_edit_id)
            val recipeText = findViewById<EditText>(R.id.recipe_edit_id)
            val recipeThumbnail = findViewById<Spinner>(R.id.spinner_id)

            if(titleText.text.isEmpty() || ingredientsText.text.isEmpty() || prepMethodText.text.isEmpty()||recipeText.text.isEmpty())
            {
                Toast.makeText(this, "Įsitikinkite ar visi laukai užpildyti!", Toast.LENGTH_LONG).show()
                return;
            }

            val intent = Intent()
            intent.putExtra("title",titleText.text.toString())
            intent.putExtra("ingredients",ingredientsText.text.toString())
            intent.putExtra("method",prepMethodText.text.toString())
            intent.putExtra("recipe", recipeText.text.toString())
            intent.putExtra("thumbnail",recipeThumbnail.selectedItem.toString())
            val pos = this.intent?.extras?.getInt("position",-1)
            if (pos!=-1)
            {
                intent.putExtra("position",pos)
            }

            setResult(RESULT_OK,intent)
            finish()

        }

    }

}