package com.m6code.cryptocurrencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ConvertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
    }

    // Add menu item to the BTC Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handles on clicking menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btc_view:
                launchBTC();
                return true;
            case R.id.eth_view:
                launchETH();
                return true;
            case R.id.convert:
                Toast.makeText(this,"Already on Convert", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Launch the Covert Activity
    private void launchBTC() {
        Intent launchConvert = new Intent(this, BtcActivity.class);
        startActivity(launchConvert);
    }

    // Launch The ETH Activity
    private void launchETH() {
        Intent ethLaunch = new Intent(this, EthActivity.class);
        startActivity(ethLaunch);
    }
}
