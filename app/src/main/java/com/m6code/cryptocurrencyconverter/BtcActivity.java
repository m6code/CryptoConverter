package com.m6code.cryptocurrencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BtcActivity extends AppCompatActivity {


    // create the log tag to log errors to console
    public static final String LOG_TAG = BtcActivity.class.getName();

    ArrayList<Coins> coins = new ArrayList<Coins>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OkHttpHandler okHttpHandler = new OkHttpHandler();
        //okHttpHandler.execute(COIN_API_URL_MULTI);

        coins.add(new Coins(R.drawable.btc, "1 BTC", "N"));

        // Find a reference to the {@link ListView} from the activity_main layout
        ListView userListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of users as input
        CoinsAdapter adapter = new CoinsAdapter(BtcActivity.this, coins);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        userListView.setAdapter(adapter);

        FloatingActionButton fab_eth = (FloatingActionButton) findViewById(R.id.fab_eth);
        fab_eth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchETH();
            }
        });


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
                Toast.makeText(this,"Already on BTC", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.eth_view:
                launchETH();
                return true;
            case R.id.convert:
                //TODO: Add the code to launch the convert activity here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Launch The ETH Activity
    private void launchETH() {
        Intent ethLaunch = new Intent(this, EthActivity.class);
        startActivity(ethLaunch);
    }


}
