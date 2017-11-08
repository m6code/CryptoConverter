package com.m6code.cryptocurrencyconverter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{


    // create the log tag to log errors to console
    public static final String LOG_TAG = MainActivity.class.getName();

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
        CoinsAdapter adapter = new CoinsAdapter(MainActivity.this, coins);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        userListView.setAdapter(adapter);


    }

}
