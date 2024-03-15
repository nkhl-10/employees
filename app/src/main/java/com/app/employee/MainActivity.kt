package com.app.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.employee.adapter.ItemAdapter
import com.app.employee.db.dbHelper
import com.app.employee.models.emp
import com.app.teksun.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var rv: RecyclerView
    private lateinit var empList: List<emp>
    private lateinit var notfound: TextView
    private lateinit var search:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notfound = findViewById(R.id.notfound)
        fab = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            startActivity(Intent(this, Add_Details::class.java))
        }
        rv = findViewById(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)
        search=findViewById(R.id.search)

        //fetch data
        refresh()

        //search
        search.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    search(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })



    }

    override fun onResume() {
        refresh()
        super.onResume()
    }

    fun refresh(){

        val db = dbHelper(this)
        empList = db.getEmp()
        rv.adapter = ItemAdapter(this, empList)

        if(empList.isEmpty()){
            rv.visibility=View.GONE
            notfound.visibility=View.VISIBLE
        }else {
            rv.visibility = View.VISIBLE
            notfound.visibility = View.GONE
        }
    }


    fun search(search:String){

        val db = dbHelper(this)
        empList = db.searchEmp(search)
        rv.adapter = ItemAdapter(this, empList)

        if(empList.isEmpty()){
            rv.visibility=View.GONE
            notfound.visibility=View.VISIBLE
        }else {
            rv.visibility = View.VISIBLE
            notfound.visibility = View.GONE
        }
    }
}