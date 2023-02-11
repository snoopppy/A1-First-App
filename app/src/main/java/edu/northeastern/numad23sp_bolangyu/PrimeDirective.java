package edu.northeastern.numad23sp_bolangyu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PrimeDirective extends AppCompatActivity {

    private Button find;
    private Button terminate;
    private CheckBox Switch;
    private TextView current;
    private TextView latest;
    private Button back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_directive);

        find = (Button) findViewById(R.id.find);
        terminate = (Button) findViewById(R.id.terminate);
        Switch = (CheckBox) findViewById(R.id.checkBox);
        current = (TextView) findViewById(R.id.current);
        latest = (TextView) findViewById(R.id.latest);
        back = (Button) findViewById(R.id.back);


    }
}