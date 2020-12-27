package com.example.cooking_app_v3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RecipeListActivity : AppCompatActivity(),
RecipeListAdapter.ItemClickListener,
RecipeListAdapter.EditClickListener,
RecipeListAdapter.DeleteClickListener{
    lateinit var  databaseData : AppDatabase
    var data = mutableListOf<Recipe>()
    private var ADD_ITEM_REQUEST_CODE = 1
    private var EDIT_ITEM_REQUEST_CODE = 2
    lateinit var listView:RecyclerView
    lateinit var listAdapter: RecipeListAdapter
    var disposable :Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        databaseData = AppDatabase.getInstance(this)!!

        listView=findViewById(R.id.recipes_list_Id)
        listView?.layoutManager = GridLayoutManager(this,1)

        listAdapter = RecipeListAdapter(data,this,this,this)
        listView.adapter=listAdapter
        getRecipes()



    }
    fun getRecipes()
    {
        disposable = databaseData?.getRecipeDao()
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
                    listAdapter?.notifyDataSetChanged()
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
            val method = data?.extras?.getString("method")
            val recipeHere = data?.extras?.getString("recipe")
            val thumbnailId = data?.extras?.getString("thumbnail")

            val thumbnail =thumbnailId?.toInt()
            if(requestCode==ADD_ITEM_REQUEST_CODE) {


                val recipeDb = Recipe(0, title, ingredients, method, recipeHere, thumbnail)

                disposable = databaseData
                    ?.getRecipeDao()
                    ?.insert(recipeDb)
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
                        listAdapter?.notifyItemInserted(this.data!!.size)
                        disposable = null
                    }, {
                        Toast.makeText(this, "Nepavyko pridėti recepto!", Toast.LENGTH_LONG)
                            .show()
                    }
                    )
            }
                if(requestCode==EDIT_ITEM_REQUEST_CODE)
                {
                    val position = data?.extras?.getInt("position")
                    val recipe = Recipe(this.data[position!!].recipeId,
                        title,ingredients ,method, recipeHere,thumbnail)
                    disposable = databaseData
                        ?.getRecipeDao()
                        ?.update(recipe)
                        ?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe({
                                    this.data[position] = recipe
                                    listAdapter.notifyItemChanged(position)
                                    disposable = null
                        },{
                            Toast.makeText(this, "Nepavyko tanaujinti reikšmių!", Toast.LENGTH_LONG)
                        })
                }

        }
    }

    override fun openRecipeActivity(position: Int) {

            val intent = Intent(this, RecipeActivity::class.java)

            intent.putExtra("recipeTitle",data[position].recipeTitle)
            intent.putExtra("recipeIngredients",data[position].recipeIngredients)
            intent.putExtra("recipeMethod",data[position].recipeMethodTitle)
            intent.putExtra("myRecipe",data[position].myRecipe)

            startActivity(intent)

    }
    fun openAddRecipeActivity(view: View) {
        val intent = Intent(this, AddRecipeActivity::class.java)
        startActivityForResult(intent,ADD_ITEM_REQUEST_CODE)
    }

    override fun onDeleteClick(position: Int) {
         disposable = databaseData
            ?.getRecipeDao()
            ?.delete(data[position])
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                data.removeAt(position)
                listAdapter.notifyItemRemoved(position)
                listAdapter.notifyDataSetChanged()
                disposable = null
            },
            {
                Toast.makeText(this, "Failed to delete customer!", Toast.LENGTH_LONG).show()

            }
            )

    }


    override fun onEditClick(position: Int) {
        val intent = Intent(this,AddRecipeActivity::class.java)
        intent.putExtra("edit",true)
        intent.putExtra("position",position)
        intent.putExtra("recipeTitle",data[position].recipeTitle)
        intent.putExtra("recipeIngredients",data[position].recipeIngredients)
        intent.putExtra("recipeMethod",data[position].recipeMethodTitle)
        intent.putExtra("myRecipe",data[position].myRecipe)
        intent.putExtra("recipeThumbnail",data[position].recipeThumbnail)

        startActivityForResult(intent,EDIT_ITEM_REQUEST_CODE)
    }



}