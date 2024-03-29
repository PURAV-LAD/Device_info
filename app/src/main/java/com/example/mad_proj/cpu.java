package com.example.mad_proj;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class cpu extends Activity {

    private TextView cpuTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_gpu);

        cpuTextView = findViewById(R.id.cpuTextView);

        // Retrieve CPU information
        String cpuInfo = getCPUInfo();

        // Display CPU information
        cpuTextView.setText("CPU Info: " + cpuInfo);
    }

    // get CPU information
    private String getCPUInfo() {
        String arch = System.getProperty("os.arch");
        String name = System.getProperty("os.name");
        String version = System.getProperty("os.version");
        String cores = String.valueOf(Runtime.getRuntime().availableProcessors());

        return "Architecture: " + arch + "\n" +
                "Name: " + name + "\n" +
                "Version: " + version + "\n" +
                "Number of Cores: " + cores;
    }

}
