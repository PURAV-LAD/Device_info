package com.example.mad_proj;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class rams extends AppCompatActivity {

    private TextView ramUsageTextView;
    private TextView freeRamTextView;
    private TextView totalMemTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rams);

        ramUsageTextView = findViewById(R.id.ramUsageTextView);
        freeRamTextView = findViewById(R.id.freeRamTextView);
        totalMemTextView = findViewById(R.id.totalRamTextView);
        Button checkButton = findViewById(R.id.checkButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRAM();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkRAM() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            long totalMemory = memoryInfo.totalMem;
            long availableMemory = memoryInfo.availMem;
            long usedMemory = totalMemory - availableMemory;

            ramUsageTextView.setText("RAM Usage: " + formatSize(usedMemory));
            freeRamTextView.setText("Free RAM: " + formatSize(availableMemory));
            totalMemTextView.setText("Total Memory: " + formatSize(totalMemory));
        }
    }

    private String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MB";
                size /= 1024;
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));
//With comma
//        int commaOffset = resultBuffer.length() - 3;
//        while (commaOffset > 0) {
//            resultBuffer.insert(commaOffset, ',');
//            commaOffset -= 3;
//        }
//
        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }
}
