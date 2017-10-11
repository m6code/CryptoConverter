package com.m6code.cryptocurrencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // create the log tag to log errors to console
    public static final String LOG_TAG = MainActivity.class.getName();


//    /**
//     * URL for coin data from Cryptocompare API
//     */
//    private static final String COIN_DATA_REQUEST_URL =
//            "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Coins> coins = new ArrayList<Coins>();
        coins.add(new Coins(R.mipmap.ic_launcher, "BTC", "4524", "NGR"));
        coins.add(new Coins(R.mipmap.ic_launcher_round, "ETH", "453", "USD"));
        coins.add(new Coins(R.mipmap.ic_launcher, "BTC", "987", "USD"));
        coins.add(new Coins(R.mipmap.ic_launcher_round, "ETH","768", "NGR"));
        coins.add(new Coins(R.mipmap.ic_launcher, "BTC", "123", "INR"));
        coins.add(new Coins(R.mipmap.ic_launcher_round, "ETH", "445", "JPY"));


        // Find a reference to the {@link ListView} from the activity_main layout
        ListView userListView = (ListView)findViewById(R.id.list);

        // Create a new adapter that takes an empty list of users as input
        CoinsAdapter adapter = new CoinsAdapter(this, coins);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        userListView.setAdapter(adapter);
    }
}
