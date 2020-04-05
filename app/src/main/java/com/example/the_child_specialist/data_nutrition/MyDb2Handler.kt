package com.example.the_child_specialist.data_nutrition

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.the_child_specialist.model.Db_Disease
import com.example.the_child_specialist.model_nutrition.DB_Nutrition
import com.example.the_child_specialist.params.Params
import com.example.the_child_specialist.params_nutrition.Params2

class MyDb2Handler(context : Context) : SQLiteOpenHelper(context, Params2.DB_NAME2, null, Params2.NT_VERSION) {


    // db created
    override fun onCreate(db2: SQLiteDatabase?) {
        val create = ("CREATE TABLE " + Params2.TABLE_NAME2
                + "("
                + Params2.KEY_ID2 + " INTEGER PRIMARY KEY,"
                + Params2.KEY_NAME2
                + " TEXT, " + Params2.KEY_DESCRIPTION2 + " TEXT" + ")")


        Log.d("dbshoaib", "Query being run is : $create")
        db2?.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    // added in db
    fun addContact2(dbNutrition: DB_Nutrition) {
        val db2 = this.writableDatabase

        val values = ContentValues()
        values.put(Params2.KEY_NAME2, dbNutrition.name2)
        values.put(Params2.KEY_DESCRIPTION2, dbNutrition.description2)


        db2.insert(Params2.TABLE_NAME2, null, values)
        Log.d("dbshoaib", "Successfully inserted")
        db2.close()
    }

    // fetch our data for display

    val allNutrition: List<DB_Nutrition> get() {
        val nutritiontList = ArrayList<DB_Nutrition>()
        val db2 = this.readableDatabase
        val select = "SELECT * FROM " + Params2.TABLE_NAME2
        val cursor = db2.rawQuery(select, null)

        if (cursor.moveToFirst()) {
            do {
                val nutrition = DB_Nutrition()
                nutrition.id2 = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Params2.KEY_ID2)))
                nutrition.name2 = cursor.getString(cursor.getColumnIndex(Params2.KEY_NAME2))
                nutrition.description2 = cursor.getString(cursor.getColumnIndex(Params2.KEY_DESCRIPTION2))
                nutritiontList.add(nutrition)
            } while (cursor.moveToNext())
        }
        db2.close()
        return nutritiontList
    }
    fun deleteContactById2(id: Int) {
        val db2 = this.writableDatabase
        db2.delete(Params2.TABLE_NAME2, Params2.KEY_ID2 + "=?", arrayOf(id.toString()))
        db2.close()
    }

    val count: Int get() {
        val query = "SELECT * FROM " + Params2.TABLE_NAME2
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        return cursor.count
    }
}