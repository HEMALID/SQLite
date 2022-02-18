package com.example.sqlite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var enterName = findViewById<EditText>(R.id.enterName)
        var enterAge = findViewById<EditText>(R.id.enterAge)

        var addName = findViewById<Button>(R.id.addName)
        var printName = findViewById<Button>(R.id.printName)

        var Name = findViewById<TextView>(R.id.Name)
        var Age = findViewById<TextView>(R.id.Age)

        addName.setOnClickListener{
            val db = DBHelper(this, null)
            val name = enterName.text.toString()
            val age = enterAge.text.toString()
            db.addName(name, age)

            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            enterName.text.clear()
            enterAge.text.clear()
        }

        printName.setOnClickListener{
            val db = DBHelper(this, null)
            val cursor = db.getName()
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            cursor.close()
        }
    }
}
