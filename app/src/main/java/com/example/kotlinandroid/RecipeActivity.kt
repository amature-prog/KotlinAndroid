package com.example.kotlinandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeActivity : AppCompatActivity() {

    companion object{
        lateinit var dbHandler: DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        dbHandler = DBHandler(this, null, null, 1)

        viewRecipes()

        val actionBar = supportActionBar

        actionBar!!.title = "Recipe List"

        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    @Suppress("SpellCheckingInspection")
    private fun viewRecipes(){
        val recipeslist = dbHandler.getRecipes(this)
        val adapter = RecipeAdapter(this, recipeslist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    override fun onResume(){
        viewRecipes()
        super.onResume()
    }
}