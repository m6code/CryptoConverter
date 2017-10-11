package com.m6code.cryptocurrencyconverter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    // create the log tag to log errors to console
    public static final String LOG_TAG = MainActivity.class.getName();


    /**
     * URL for coin data from Cryptocompare API
     */
    private static final String COIN_API_URL_MULTI =
            "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD," +
                    "EUR,NGN,JPY,GPB,CHF,CAD,AUD,HKD,BRL,INR,KRW,ILS,RUB,ZAR,SEK,PHP,CNY,TRY";

    //private static final String API_URL = "https://min-api.cryptocompare.com";

    /**
     * Base url for getting images of coins
     */
    private static final String COIN_BASE_URL = "https://cryptocompare.com";

    ArrayList<Coins> coins = new ArrayList<Coins>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(COIN_API_URL_MULTI);


    }

    private class OkHttpHandler extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... url) {
            Request.Builder builder = new Request.Builder();
            builder.url(url[0]);
            Request request = builder.build();

            try{
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try{
                JSONObject multiCoinDataObject = new JSONObject(data);

                JSONObject btcCoinData = multiCoinDataObject.getJSONObject("BTC");

                JSONObject ethCoinData = multiCoinDataObject.getJSONObject("ETH");


                // Extract conversion rate for BTC to Naira
                String btc2naira = btcCoinData.getString("NGN");
                coins.add(new Coins(R.mipmap.ic_launcher, "1 BTC", btc2naira, "NGN"));

                // Extract conversion rate for ETH to naira
                String eth2naira = ethCoinData.getString("NGN");
                coins.add(new Coins(R.mipmap.ic_launcher, "1 ETH",eth2naira, "NGN"));

                // Find a reference to the {@link ListView} from the activity_main layout
                ListView userListView = (ListView)findViewById(R.id.list);

                // Create a new adapter that takes an empty list of users as input
                CoinsAdapter adapter = new CoinsAdapter(MainActivity.this, coins);

                // Set the adapter on the {@link ListView}
                // so the list can be populated in the user interface
                userListView.setAdapter(adapter);

            }catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
