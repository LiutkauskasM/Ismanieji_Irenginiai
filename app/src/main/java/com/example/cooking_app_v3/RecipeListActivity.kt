package com.example.cooking_app_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RecipeListActivity : AppCompatActivity(),
RecipeAdapter.ItemClickListener{
    lateinit var  db : AppDatabase
    lateinit var data :MutableList<Recipe>

    lateinit var list:RecyclerView
    lateinit var adapter: RecipeAdapter
    var disposable :Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        db = AppDatabase.getInstance(this)!!
        data = mutableListOf<Recipe>()

        list=findViewById(R.id.recipes_list_Id)
        list?.layoutManager = GridLayoutManager(this,1)

        adapter = RecipeAdapter(data,this)
        list.adapter=adapter
        getRecipes()


    }
    fun getRecipes()
    {
        disposable = db?.getRecipeDao()
            ?.getAllRecipes()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    data?.clear()
                    if(!it.isNullOrEmpty())
                    {
                        data?.addAll(it)
                    }
                    adapter?.notifyDataSetChanged()
                    disposable=null
                },
                {
                    Toast.makeText(this, "Klaida gaunant receptus!", Toast.LENGTH_LONG).show()
                }
            )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK)
        {
            val title = data?.extras?.getString("title")
            val ingredients = data?.extras?.getString("ingredients")
            val method = data?.extras?.getString("Method")
            val recipe =  data?.extras?.getString("Recipe")

            val recipeDb=Recipe(0,title,ingredients,method,recipe,R.drawable.cherries)

            disposable = db?.getRecipeDao()?.insert(recipeDb)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    this.data?.add(
                        Recipe(
                            it.toInt(),
                            recipeDb.recipeTitle,
                            recipeDb.recipeIngredients,
                            recipeDb.recipeMethodTitle,
                            recipeDb.myRecipe,
                            recipeDb.recipeThumbnail
                        )
                    )
                    adapter?.notifyItemInserted(this.data!!.size)
                    disposable = null
                }, {
                        Toast.makeText(this, "Nepavyko pridėti recepto!", Toast.LENGTH_LONG)
                            .show()
                    }
                )


        }
    }

    override fun openRecipeActivity(position: Int) {

            val intent = Intent(this, RecipeActivity::class.java)

            intent.putExtra("Name",data[position].recipeTitle)
            intent.putExtra("Ingredients",data[position].recipeIngredients)
            intent.putExtra("Method",data[position].recipeMethodTitle)
            intent.putExtra("Recipe",data[position].myRecipe)

            startActivity(intent)

        }


}