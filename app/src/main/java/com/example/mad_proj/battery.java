package com.example.mad_proj;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class battery extends AppCompatActivity {

    TextView textViewStatus, textViewHealth, textViewVoltage, textViewPercentage, textViewTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        textViewStatus = findViewById(R.id.textViewStatus);
        textViewHealth = findViewById(R.id.textViewHealth);
        textViewVoltage = findViewById(R.id.textViewVoltage);
        textViewPercentage = findViewById(R.id.textViewPercentage);
        textViewTemperature = findViewById(R.id.textViewTemperature);

        checkBatteryStatus();
    }

    private void checkBatteryStatus() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        String healthStatus = "Unknown";
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthStatus = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthStatus = "Overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthStatus = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthStatus = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthStatus = "Unspecified Failure";
                break;
        }

        int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

        // Temperature in °C
        int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale;

        textViewStatus.setText("Status: " + (isCharging ? "Charging" : "Discharging"));
        textViewHealth.setText("Health: " + healthStatus);
        textViewVoltage.setText("Voltage: " + voltage + " mV");
        textViewTemperature.setText("Temperature: " + temperature / 10.0 + " °C");
        textViewPercentage.setText("Percentage: " + (int) (batteryPct * 100) + "%");
    }
}
