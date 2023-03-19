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

    private Button link;

    private Button prime;
    private Button location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        Clicky = (Button) findViewById(R.id.click);
        link = (Button) findViewById(R.id.linkCollector);
        prime = (Button) findViewById(R.id.prime);
        location = (Button) findViewById(R.id.location);
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

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlink();
            }
        });

        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrime();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocation();
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

    public void openlink(){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void openPrime(){
        Intent intent = new Intent(this, PrimeDirective.class);
        startActivity(intent);
    }
    private void openLocation() {
        Intent intent = new Intent(this, Location.class);
        startActivity(intent);
    }
}