package com.example.the_child_specialist.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.service.autofill.DateValueSanitizer
import android.util.Log
import com.example.the_child_specialist.Disease
import com.example.the_child_specialist.model.Db_Disease
import com.example.the_child_specialist.params.Params
import com.example.the_child_specialist.params.Params.KEY_NAME

class MyDbHandler(context : Context) : SQLiteOpenHelper(context, Params.DB_NAME, null, Params.DB_VERSION) {


// db created
    override fun onCreate(db: SQLiteDatabase?) {
        val create = ("CREATE TABLE " + Params.TABLE_NAME
                + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_NAME
                + " TEXT, " + Params.KEY_DESCRIPTION + " TEXT" + ")")


        Log.d("dbshoaib", "Query being run is : $create")
        db?.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

// added in db
    fun addContact(dbDisease: Db_Disease) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(Params.KEY_NAME, dbDisease.name)
        values.put(Params.KEY_DESCRIPTION, dbDisease.description)


        db.insert(Params.TABLE_NAME, null, values)
        Log.d("dbshoaib", "Successfully inserted")
        db.close()
    }

    // fetch our data for display

    val allContacts: List<Db_Disease> get() {
        val contactList = ArrayList<Db_Disease>()
        val db = this.readableDatabase
        val select = "SELECT * FROM " + Params.TABLE_NAME
        val cursor = db.rawQuery(select, null)

        if (cursor.moveToFirst()) {
            do {
                val contact = Db_Disease()
                contact.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Params.KEY_ID)))
                contact.name = cursor.getString(cursor.getColumnIndex(Params.KEY_NAME))
                contact.description = cursor.getString(cursor.getColumnIndex(Params.KEY_DESCRIPTION))
                contactList.add(contact)
            } while (cursor.moveToNext())
        }
db.close()
        return contactList
    }


    fun deleteContactById(id: Int) {
        val db = this.writableDatabase
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", arrayOf(id.toString()))
        db.close()
    }
    val count: Int get() {
        val query = "SELECT * FROM " + Params.TABLE_NAME
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        return cursor.count
    }
}