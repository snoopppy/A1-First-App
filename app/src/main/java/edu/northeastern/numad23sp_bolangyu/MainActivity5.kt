package edu.northeastern.numad23sp_bolangyu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity5 : AppCompatActivity() {
    private lateinit var link:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)

        link = findViewById(R.id.URL)

        link.setOnClickListener{
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)

            val open = Intent(Intent.ACTION_VIEW)
            open.data = Uri.parse("https://www.google.com/")
            startActivity(open)

        }
    }
}