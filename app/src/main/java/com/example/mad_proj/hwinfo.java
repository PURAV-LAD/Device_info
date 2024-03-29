package com.example.mad_proj;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
//device info class
public class hwinfo extends AppCompatActivity {
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hwinfo);

        // display device information
        TextView deviceInfoTextView = findViewById(R.id.deviceInfoTextView);

        String deviceInfo = getDeviceInfo();
        deviceInfoTextView.setText(deviceInfo);
    }

    public String getDeviceInfo() {
        return "Device Model: " + Build.MODEL + "\n" +
                "Manufacturer: " + Build.MANUFACTURER + "\n" +
                "Brand: " + Build.BRAND + "\n" +
                "Device Type: " + Build.TYPE + "\n" +
                "OS Version: " + Build.VERSION.RELEASE + "\n" +
                "SDK Version: " + Build.VERSION.SDK_INT + "\n";
    }
}
