package com.example.mad_proj;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //intents
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, check_ip.class));
    }
    public void hwsec(View v) {
        startActivity(new Intent(MainActivity.this, hwinfo.class));
    }
    public void kernel(View v) {
        startActivity(new Intent(MainActivity.this, kernel.class));
    }
    public void pub_ips(View v) {
        startActivity(new Intent(MainActivity.this, pub_ip.class));
    }
    public void ping(View v) {
        startActivity(new Intent(MainActivity.this, check_ping.class));
    }
    public void bat(View v) {
        startActivity(new Intent(MainActivity.this, battery.class));
    }
    public void rams(View v) {
        startActivity(new Intent(MainActivity.this, rams.class));
    }
    public void cgu(View v) {
        startActivity(new Intent(MainActivity.this, cpu.class));
    }
}
