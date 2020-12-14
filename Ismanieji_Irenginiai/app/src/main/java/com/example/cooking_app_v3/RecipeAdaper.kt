package com.example.cooking_app_v3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RecipeAdapter(
    private val data: MutableList<Recipe>,
    private val itemClickListener:ItemClickListener

): RecyclerView.Adapter<RecipeAdapter.ViewHolder>()
{
    interface ItemClickListener
    {

        fun openRecipeActivity(position: Int)
    }
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        var recipeTitle: TextView? = v.findViewById<TextView>(R.id.recipe_title_id)
        var cardView: CardView? = v.findViewById<CardView>(R.id.cardview_id)
        var img_recipe_thumbnail: ImageView? = v.findViewById<ImageView>(R.id.recipe_img_id)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_recipe,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var cardview =  data!![position]
        holder.recipeTitle?.setText(cardview.recipeTitle)
        holder.img_recipe_thumbnail?.setImageResource(cardview.recipeThumbnail!!)

        holder.cardView?.setOnClickListener {
            itemClickListener.openRecipeActivity(position)
        }


    }

    override fun getItemCount(): Int {
        return data!!.size
    }


}