package com.example.kotlinandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinandroid.R.layout.*

class AddRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        var btnSave = findViewById<Button>(R.id.btnSave)
        var btnCancel = findViewById<Button>(R.id.btnCancel)

        btnSave.setOnClickListener {
            if(editRecipeName.text.isEmpty()){
                Toast.makeText(this,"Enter Recipe Name", Toast.LENGTH_SHORT).show()
                    editRecipeName.requestFocus()
                }else
            {
                val recipe = Recipe()
                recipe.recipeName = editRecipeName.text.toString()
                RecipeActivity.dbHandler.addRecipe(this, recipe)
                clearEdits()
                editRecipeName.requestFocus()
            }


        }

        btnCancel.setOnClickListener {
            clearEdits()
            finish()

        }


    }

    val editRecipeName: TextView = findViewById<TextView>(R.id.editRecipeName)
    private fun clearEdits(){
        editRecipeName.text = "";
    }



}



