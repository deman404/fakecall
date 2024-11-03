package com.relapolires.ayaap;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Activitysplashing extends AppCompatActivity {


    String v1 = "com.";
    String v2 = "relapolires.";
    String v3 = "ayaap";

    protected boolean isLoaded = true;
    @Override
    protected void onCreate(Bundle splash){
        super.onCreate(splash);
        setContentView(R.layout.activitysplashing);
        splasher();
        if(getPackageName().compareTo(v1+v2+v3) != 0) {
            String error = null;
            error.getBytes();
        }


    }

    public void splasher(){
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (isLoaded && (waited < 2000)) {
                        sleep(finalsapi.timesplash);

                        if (isLoaded) {
                            waited += finalsapi.timesplash;
                        }
                    }
                } catch (InterruptedException ignored) {

                }
                    startActivity(new Intent(getApplicationContext() , MainActivity.class));
                    finish();

            }

        };
        splashTread.start();
    }




}
