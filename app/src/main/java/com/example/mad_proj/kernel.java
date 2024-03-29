package com.example.mad_proj;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class kernel extends AppCompatActivity {

    private TextView kernelVersionTextView, architectureTextView, rootAccessTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kernel);

        kernelVersionTextView = findViewById(R.id.kernelVersionTextView);
        architectureTextView = findViewById(R.id.architectureTextView);
        rootAccessTextView = findViewById(R.id.rootAccessTextView);

        // Check Kernel Version
        String kernelVersion = System.getProperty("os.version");
        kernelVersionTextView.setText("Kernel Version: " + kernelVersion);

        // Check Architecture
        String architecture = System.getProperty("os.arch");
        architectureTextView.setText("Architecture: " + architecture);

        // Check Root Access
        boolean hasRootAccess = isRooted();
        rootAccessTextView.setText("Root Access: " + (hasRootAccess ? "Yes" : "No"));
    }

    // if device is rooted
    private boolean isRooted() {
        String[] paths = {"/system/bin/su", "/system/xbin/su", "/sbin/su", "/system/su"};
        for (String path : paths) {
            if (new java.io.File(path).exists()) return true;
        }
        return false;
    }
}
