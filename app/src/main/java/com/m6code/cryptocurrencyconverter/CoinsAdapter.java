package com.m6code.cryptocurrencyconverter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Benjamin on 11/10/2017.
 * * An ArrayAdapter used to populate the ListView
 * {@link CoinsAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Coins} objects.
 */

public class CoinsAdapter extends ArrayAdapter<Coins> {

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

        // Return the ListView layout
        return listItemView;
    }
}
