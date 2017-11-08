package com.m6code.cryptocurrencyconverter;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Benjamin on 11/10/2017.
 * * An ArrayAdapter used to populate the ListView
 * {@link CoinsAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Coins} objects.
 */

public class CoinsAdapter extends ArrayAdapter<Coins>  implements OnItemSelectedListener {

    TextView tvRate;
    Coins currentCoin;

    public CoinsAdapter(Context context, ArrayList<Coins> coins){
        super(context, 0, coins);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being resused, otherwise inflate the view

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        // Ge the {@link Coins} object located at this position in the list
        currentCoin = getItem(position);

        // Find the coinImage imageView
        ImageView coinImage = (ImageView)listItemView.findViewById(R.id.coin_image);
        // Set the Image for the current coin
        coinImage.setImageResource(currentCoin.getFromCoinImageID());

        // Find the converting from coin textView
        TextView tvFromCoin = (TextView)listItemView.findViewById(R.id.tv_from_coin_name);
        // Set the Text for the current coin
        tvFromCoin.setText(currentCoin.getFromCoinName());

        // Find the converting to coin TextView
        TextView tvToCoin = (TextView)listItemView.findViewById(R.id.tv_to_coin_name);
        // Set the Text for the current coin
        tvToCoin.setText(currentCoin.getToCoinName());

        // Find the rate TextView
        tvRate = (TextView)listItemView.findViewById(R.id.tv_rate);
        // Set the text for the rate
        tvRate.setText(currentCoin.getRate());

        // Find the spinner
        Spinner spinner = (Spinner) listItemView.findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> currencies = new ArrayList<String>();
        currencies.add("USD");
        currencies.add("EUR");
        currencies.add("NGN");
        currencies.add("JPY");

        // Create an array adapter for the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, currencies);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        // Return the ListView layout
        return listItemView;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: "+ item, Toast.LENGTH_SHORT).show();

        // query string
        String queryURL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=";

        OkHttpHandler okHttpHandler = new OkHttpHandler();

        // TODO: Query the CryptoCompare api here and return the data for each currency
        switch (item) {
            case "NGN":
                okHttpHandler.execute(queryURL + item);
                break;
            case "USD":
                okHttpHandler.execute(queryURL + item);
                break;
            case "EUR":
                okHttpHandler.execute(queryURL + item);
                break;
            case "JPY":
                okHttpHandler.execute(queryURL + item);
                break;
        }

    }

    public void onNothingSelected(AdapterView<?> arg0){
        // TODO: set default behaviour if nothing is selected
    }

    private class OkHttpHandler extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... url) {
            Request.Builder builder = new Request.Builder();
            builder.url(url[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try {
                JSONObject coinDataObject = new JSONObject(data);

                if(coinDataObject.has("NGN")){
                   String btc2NGN = coinDataObject.getString("NGN");
                    // Update the textView of the list.
                    tvRate.setText("N"+btc2NGN);
                }else if(coinDataObject.has("USD")){
                    String btc2USD = coinDataObject.getString("USD");
                    // Update the textView of the list.
                    tvRate.setText("$"+btc2USD);
                }else if(coinDataObject.has("EUR")){
                    String btc2EUR = coinDataObject.getString("EUR");
                    // Update the textView of the list.
                    tvRate.setText("E"+btc2EUR);
                }else if(coinDataObject.has("JPY")){
                    String btc2JPY = coinDataObject.getString("JPY");
                    // Update the textView of the list.
                    tvRate.setText("J"+btc2JPY);
                }else {
                    tvRate.setText("No Data");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
