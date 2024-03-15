package com.app.employee.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.employee.models.emp

class dbHelper(context: Context): SQLiteOpenHelper(
    context, DBNAME,null, VERSION
) {
    companion object {
        private const val VERSION=1
        private const val DBNAME="emp_db"
        private const val TNAME = "emp"
        private const val ID = "id"
        private const val NAME = "name"
        private const val AGE = "age"
        private const val CITY="city"
        private const val STATES="states"
        private const val ADDRESS ="address"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql="CREATE TABLE $TNAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NAME TEXT," +
                "$AGE INTEGER,"+
                "$CITY TEXT,"+
                "$STATES TEXT,"+
                "$ADDRESS TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TNAME")
        onCreate(db)
    }



    fun getEmp(): List<emp> {
        val empList = ArrayList<emp>()
        val db = writableDatabase
        val columns = arrayOf(ID, NAME, AGE, CITY, STATES, ADDRESS)
        val cursor: Cursor = db.query(TNAME, columns, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val age = cursor.getString(2)
            val city =cursor.getString(3)
            val states =cursor.getString(4)
            val address=cursor.getString(5)

            val employee = emp(id, name, age, city, states, address)
            empList.add(employee)
        }
        cursor.close()
        return empList
    }


    fun searchEmp(searchQuery: String? = null): List<emp> {
        val empList = ArrayList<emp>()
        val db = writableDatabase
        val columns = arrayOf(ID, NAME, AGE, CITY, STATES, ADDRESS)

        // Modify the selection and selectionArgs based on the search query
        val selection = if (searchQuery.isNullOrEmpty()) null else "$NAME LIKE ? OR $CITY LIKE ?"
        val selectionArgs =
            if (searchQuery.isNullOrEmpty()) null else arrayOf("%$searchQuery%", "%$searchQuery%")

        val cursor: Cursor = db.query(TNAME, columns, selection, selectionArgs, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val age = cursor.getString(2)
            val city =cursor.getString(3)
            val states =cursor.getString(4)
            val address=cursor.getString(5)

            val employee = emp(id, name, age, city, states, address)
            empList.add(employee)
        }
        cursor.close()
        return empList
    }



    fun saveEmp(emp:emp){
        val db=writableDatabase
        val cv=ContentValues()
        cv.put(NAME , emp.name)
        cv.put(AGE,emp.age)
        cv.put(CITY,emp.city)
        cv.put(STATES,emp.states)
        cv.put(ADDRESS,emp.address)
        db.insert(TNAME,null,cv)
        db.close()
    }

}