package edu.northeastern.numad23sp_bolangyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button Clicky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        Clicky = (Button) findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Name: Bolang Yu",Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,"Email: yu.bol@northeastern.edu",Toast.LENGTH_LONG).show();
                openAboutMe();
            }
        });

        Clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openclicky();
            }
        });
    }

    public void openAboutMe(){
        Intent intent =new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openclicky(){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}