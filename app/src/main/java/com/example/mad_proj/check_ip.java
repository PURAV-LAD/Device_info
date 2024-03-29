package com.example.mad_proj;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_proj.R;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

//Can show localhost's ip if connected with mobile hotspot
public class check_ip extends AppCompatActivity {

    private TextView ipv4TextView;
    private TextView ipv6TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ip);

        ipv4TextView = findViewById(R.id.ipv4TextView);
        ipv6TextView = findViewById(R.id.ipv6TextView);

        displayIPAddress();
    }

    private void displayIPAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        if (addr instanceof Inet4Address) {
                            ipv4TextView.setText("IPv4 Address: " + addr.getHostAddress());
                        } else if (addr instanceof Inet6Address) {
                            ipv6TextView.setText("IPv6 Address: " + addr.getHostAddress());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
