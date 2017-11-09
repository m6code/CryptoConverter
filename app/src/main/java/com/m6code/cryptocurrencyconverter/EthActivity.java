package com.m6code.cryptocurrencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class EthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_btc);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btcLaunch = new Intent(EthActivity.this, BtcActivity.class);
                startActivity(btcLaunch);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

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
            case R.id.eth_view:
                Toast.makeText(this,"Already on ETH", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.btc_view:
                launchBTC();
                return true;
            case R.id.convert:
                launchConvert();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Launch the Covert Activity
    private void launchConvert() {
        Intent launchConvert = new Intent(this, ConvertActivity.class);
        startActivity(launchConvert);
    }

    // Launch The BTC Activity
    private void launchBTC() {
        Intent ethLaunch = new Intent(this, BtcActivity.class);
        startActivity(ethLaunch);
    }


}
