package edu.northeastern.numad23sp_bolangyu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class PrimeDirective extends AppCompatActivity {

    private Button find;
    private Button terminate;
    private CheckBox Switch;
    private TextView current;
    private TextView latest;
    private Button back;
    private String primeList;
    private boolean shouldContinue = true;
    private runableThread findthread;


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



        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findthread = new runableThread();
                shouldContinue = true;
            }
        });
        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    shouldContinue = false;
                    findthread.stop();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findthread.alive()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PrimeDirective.this);
                    builder.setTitle("Do you want to go back?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

                        openMain();
                    });
                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

                        dialog.cancel();
                    });

                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }
                else {
                    openMain();
                }
            }
        });
    }

    private boolean isPrime(int num) {
        if(num<=1)
        {
            return false;
        }
        for(int i=2;i<=num/2;i++)
        {
            if((num%i)==0)
                return  false;
        }
        return true;
    }

    private void primeNumber () throws InterruptedException {
        int number = 3;
        while (shouldContinue) {
            // Check if the number is prime
            if (isPrime(number)) {
               int latestPrime = number;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update the latest prime found in the UI
                        latest.setText("Latest:"+latestPrime);
                    }
                });
            }
            int currentN = number;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Update the current number being checked in the UI
                    current.setText("Current:"+currentN);
                }
            });
            number += 2;
            Thread.sleep(1000);
        }

    }
    public void openMain(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class runableThread implements Runnable{
        private boolean exit;
        private Thread t;
        runableThread()
        {
            t = new Thread(this);
            exit = false;
            t.start();
        }
        public void run() {
            try {
                primeNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void stop(){
            exit = true;
        }

        public boolean alive()
        {
            return t.isAlive();
        }
    }
}