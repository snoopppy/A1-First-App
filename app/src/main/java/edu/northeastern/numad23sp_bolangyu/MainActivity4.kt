package edu.northeastern.numad23sp_bolangyu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import edu.northeastern.numad23sp_bolangyu.model.Userdata
import edu.northeastern.numad23sp_bolangyu.view.UserAdapter

class MainActivity4 : AppCompatActivity() {
    private lateinit var add:FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList:ArrayList<Userdata>
    private lateinit var userAdapter:UserAdapter
    private lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        add =findViewById(R.id.adding)
        recv = findViewById(R.id.recylerview)
        back = findViewById(R.id.backbutton4)

        userList = ArrayList()

        userAdapter = UserAdapter(this, userList)

        add.setOnClickListener{addInfo()}
        back.setOnClickListener{
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)

            val open = Intent(Intent.ACTION_VIEW)
            open.data = Uri.parse("https://www.google.com/")
            startActivity(open)

        }

        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter

    }


    fun onSNACK(view: View){
        //Snackbar(view)
        val snackbar = Snackbar.make(view, "added",
                Snackbar.LENGTH_LONG).setAction("Action", null)

        snackbar.show()
    }
    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item,null)

        val userName = v.findViewById<EditText>(R.id.userName)
        val userURL = v.findViewById<EditText>(R.id.userURL)
        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok")
        {
            dialog, _->
            val names = userName.text.toString()
            val URLs = userURL.text.toString()
            userList.add(Userdata("Name: $names","URL: $URLs"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel")
        {
            dialog, _->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}