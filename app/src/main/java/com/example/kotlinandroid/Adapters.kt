package com.example.kotlinandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(mCtx : Context, val recipes : ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>(){

    val mCtx = mCtx

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(parent: RecipeAdapter.ViewHolder, position: Int) {
        val recipe : Recipe = recipes[position]
        parent.txtRecipeName.text = recipe.recipeName

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtRecipeName: TextView = itemView.findViewById(R.id.txtRecipeName)
        var btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        var btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        var btnRecipe: Button = itemView.findViewById(R.id.btnRecipe)

    }

}