package com.example.kotlinandroid

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception
import java.util.prefs.PreferencesFactory

class DBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "MyData.db"
        private const val DATABASE_VERSION = 1

        const val RECIPES_TABLE_NAME = "Recipes"
        private final const val COLUMN_RECIPEID = "recipeId"
        const val COLUMN_RECIPENAME = "recipeName"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_RECIPES_TABLE : String=("CREATE TABLE $RECIPES_TABLE_NAME(" +
                "COLUMN_RECIPEID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COLUMN_RECIPENAME TEXT)")
        db?.execSQL(CREATE_RECIPES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getRecipes(mCtx : Context) : ArrayList<Recipe> {
        val qry = "Select * From $RECIPES_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val recipes = ArrayList<Recipe>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found", Toast.LENGTH_SHORT).show() else {
            while (cursor.moveToNext()) {
                val recipe = Recipe()
                recipe.recipeId = cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPEID))
                recipe.recipeName = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPENAME))
                recipes.add(recipe)
            }
            Toast.makeText(mCtx, "${cursor.count.toString()} Records Found", Toast.LENGTH_SHORT)
                .show()
        }
        cursor.close()
        db.close()
        return recipes
    }

    fun addRecipe(mCtx: Context, recipe: Recipe){
        val values = ContentValues()
        values.put(COLUMN_RECIPENAME, recipe.recipeName)
        val db = this.writableDatabase
        try{
            db.insert(RECIPES_TABLE_NAME, null, values)
            Toast.makeText(mCtx, "Recipe Added", Toast.LENGTH_SHORT).show()
        } catch (e : Exception){
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

}