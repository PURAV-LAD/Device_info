package com.example.mad_proj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class check_arp extends AppCompatActivity {

    private Button buttonCheck;
    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_arp);

        buttonCheck = findViewById(R.id.buttonCheck);
        textViewInfo = findViewById(R.id.textViewInfo);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInformation();
            }
        });
    }

    private void checkInformation() {
        try {
            // IP Address
            String ip = InetAddress.getLocalHost().getHostAddress();

            // MAC Address
            String mac = getMACAddress();

            // Network Interfaces
            String interfaces = getNetworkInterfaces();

            // Cache Directory
            String cacheDir = getCacheDir().getAbsolutePath();

            // Display Information
            String info = "IP Address: " + ip + "\n" +
                    "MAC Address: " + mac + "\n" +
                    "Network Interfaces: \n" + interfaces + "\n" +
                    "Cache Directory: " + cacheDir;

            textViewInfo.setText(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMACAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte aMac : mac) {
                        stringBuilder.append(String.format("%02X:", aMac));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "Not Found";
    }

    private String getNetworkInterfaces() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                stringBuilder.append(networkInterface.toString()).append("\n");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
