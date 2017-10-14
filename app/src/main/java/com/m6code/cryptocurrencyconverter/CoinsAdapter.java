package com.m6code.cryptocurrencyconverter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 11/10/2017.
 * * An ArrayAdapter used to populate the ListView
 * {@link CoinsAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Coins} objects.
 */

public class CoinsAdapter extends ArrayAdapter<Coins>  implements OnItemSelectedListener {

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
        final Coins currentCoin = getItem(position);

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
        TextView tvRate = (TextView)listItemView.findViewById(R.id.tv_rate);
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, currencies);

        // attaching data adapter to spinner
        try{
            spinner.setAdapter(dataAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }


        // Return the ListView layout
        return listItemView;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: "+ item, Toast.LENGTH_LONG).show();
        // TODO: Query the CryptoCompare api here and return the data for each currency

    }

    public void onNothingSelected(AdapterView<?> arg0){
        // TODO: set default behaviour if nothing is selected
    }
}
