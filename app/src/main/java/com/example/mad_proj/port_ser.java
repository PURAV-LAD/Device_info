package com.example.mad_proj;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_proj.R;

import java.util.List;

public class port_ser extends AppCompatActivity {

    Button buttonCheckServices;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_ser);

        buttonCheckServices = findViewById(R.id.button_check_services);
        textViewResult = findViewById(R.id.textView_result);

        buttonCheckServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRunningServices();
            }
        });
    }

    private void checkRunningServices() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(Integer.MAX_VALUE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Running services:\n\n");
        for (ActivityManager.RunningServiceInfo service : runningServices) {
            stringBuilder.append(service.service.getClassName()).append(" - PID: ").append(service.pid).append("\n");
        }
        textViewResult.setText(stringBuilder.toString());
    }
}
