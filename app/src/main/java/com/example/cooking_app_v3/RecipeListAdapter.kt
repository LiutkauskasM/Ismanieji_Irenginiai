package com.example.cooking_app_v3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RecipeListAdapter(
    private val data: MutableList<Recipe>,
    private val itemClickListener:ItemClickListener,
    private val deleteClickListener: DeleteClickListener,
    private val editClickListener: EditClickListener

): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>()
{
    interface ItemClickListener
    {
        fun openRecipeActivity(position: Int)
    }

    interface DeleteClickListener
    {
        fun onDeleteClick(position: Int)
    }

    interface EditClickListener {

        fun onEditClick(position: Int)
    }
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        var cardviewRecipeTitle:TextView? = v.findViewById(R.id.cardview_recipe_title_id)
        var cardView:CardView? = v.findViewById(R.id.cardview_id)
        var img_recipe_thumbnail:ImageView? = v.findViewById(R.id.recipe_img_id)
        var recipeIngredients:TextView? = v.findViewById(R.id.recipe_ingredients_id)
        var recipeMethod:TextView? = v.findViewById(R.id.recipe_preparation_method_id)
        var recipeText:TextView? = v.findViewById(R.id.recipe_text_id)
        var editBtn:ImageButton?= v.findViewById(R.id.edit_btn_id)
        var deleteBtn:ImageButton? = v.findViewById(R.id.delete_btn_id)
        var recipeTitle:TextView? = v.findViewById(R.id.recipe_title_id)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_recipe,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var chosenRecipe =  data!![position]
        holder.cardviewRecipeTitle?.text   = chosenRecipe.recipeTitle
        holder.img_recipe_thumbnail?.setImageResource(chosenRecipe.recipeThumbnail!!)
        holder.recipeIngredients?.text = chosenRecipe.recipeIngredients
        holder.recipeMethod?.text = chosenRecipe.recipeMethodTitle
        holder.recipeText?.text = chosenRecipe.myRecipe
        holder.recipeTitle?.text = holder.cardviewRecipeTitle?.text

        holder.cardView?.setOnClickListener {
            itemClickListener.openRecipeActivity(position)
        }
        holder.editBtn?.setOnClickListener({
            editClickListener.onEditClick(position)
        })
        holder.deleteBtn?.setOnClickListener({
            deleteClickListener.onDeleteClick(position)
        })


    }

    override fun getItemCount(): Int {
        return data!!.size
    }


}