package com.example.mad_proj;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class check_ping extends AppCompatActivity {

    private Button btnCheckPing;
    private TextView tvPingResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ping);

        btnCheckPing = findViewById(R.id.btnCheckPing);
        tvPingResult = findViewById(R.id.tvPingResult);

        btnCheckPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PingTask().execute();
            }
        });
    }

    private class PingTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder pingResult = new StringBuilder();
            try {
                //Making handhsake with google.com with 4 packets
                Process process = Runtime.getRuntime().exec("/system/bin/ping -c 4 google.com");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    pingResult.append(line).append("\n");
                }
                process.waitFor();
                reader.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return pingResult.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvPingResult.setText(s);
        }
    }
}
