package com.app.employee


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.employee.db.dbHelper
import com.app.employee.models.emp
import com.app.teksun.R


class Add_Details : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var age:EditText
    lateinit var city:EditText
    lateinit var address:EditText
    lateinit var stateSpinner: Spinner
    lateinit var btn:Button
    lateinit var plus:Button
    lateinit var minus:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)




        name =findViewById(R.id.nameE)
        age =findViewById(R.id.ageE)
        city= findViewById(R.id.cityE)
        stateSpinner =findViewById(R.id.addStates)
        address=findViewById(R.id.addressE)
        btn=findViewById(R.id.button)

        val stateList = arrayListOf(
            "Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chhattisgarh",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Odisha",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttarakhand",
            "Uttar Pradesh",
            "West Bengal",
            "Andaman and Nicobar Islands",
            "Chandigarh",
            "Dadra and Nagar Haveli",
            "Daman and Diu",
            "Delhi",
            "Lakshadweep",
            "Puducherry"
        )
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stateList)
        stateSpinner.adapter = adapter


        val data = stateSpinner.selectedItem.toString();



        btn.setOnClickListener {
            val states=   stateSpinner.selectedItem.toString()
            if (
                name.text.isNotBlank() && age.text.isNotEmpty() && city.text.isNotEmpty() &&  states.isNotEmpty() && address.text.isNotEmpty()
            ) {
                postData(
                    name = name.text.toString(),
                    age = age.text.toString(),
                    city = city.text.toString(),
                    states = stateSpinner.selectedItem.toString(),
                    address = address.text.toString()
                )
            } else {
                Toast.makeText(this, "few box is empty", Toast.LENGTH_SHORT).show()
            }

        }



        plus=findViewById(R.id.plus)
        minus=findViewById(R.id.minus)
        var minteger=18
        fun increaseInteger(view: View) {
            if(minteger>=70){
                minteger=70
            }else {
                minteger += 1
                display(minteger)
            }
        }
        fun decreaseInteger(view: View) {
            if(minteger<=18){
                minteger=18
            }else {
                minteger -= 1
                display(minteger)
            }
        }
        plus.setOnClickListener {
            minteger=age.text.toString().toInt()
            increaseInteger(plus)
        }
        minus.setOnClickListener {
            minteger=age.text.toString().toInt()
            decreaseInteger(minus)
        }





    }



    private fun display(number: Int) {
        val displayInteger = findViewById<View>(
            R.id.ageE) as TextView
        displayInteger.text = "$number"
    }


    fun postData(
        name:String,
        age:String,
        city:String,
        states: String,
        address:String
    ){
            val db= dbHelper(this)
            val user = emp(
                id =0,
                name = name,
                age = age,
                city = city,
                states = states,
                address = address
            )
            db.saveEmp(user)
        Toast.makeText(this, "data saved!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,MainActivity::class.java))
    }


    }
