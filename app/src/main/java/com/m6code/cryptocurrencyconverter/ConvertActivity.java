package com.m6code.cryptocurrencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConvertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        // Find the convert from (coin) spinner
        Spinner coinSpinner = (Spinner)findViewById(R.id.coin_spinner);

        // Find the Conversion textView
        TextView resultTextView = (TextView)findViewById(R.id.to_TextView);

        // Find the input EditText View
        EditText input = (EditText)findViewById(R.id.from_EditText);

        // Find the convert to (Currencies) spinner
        Spinner currencySpinner = (Spinner)findViewById(R.id.currency_spinner);

        // Spinner drop down elements for the coins
        List<String> coins = new ArrayList<String>();
        coins.add("BTC - Bitcoin");
        coins.add("ETH - Ethereum");

        // Spinner Drop down elements for currencies
        List<String> currencies = new ArrayList<String>();
        currencies.add("USD - US Dollars");
        currencies.add("EUR - Euros");
        currencies.add("NGN - Nigerian Naira");
        currencies.add("JPY  - Japanese Yen");
        currencies.add("GBP - British Pound");
        currencies.add("CHF - Swiss Franc");
        currencies.add("CAD - Canadian Dollar");
        currencies.add("MXN - Mexican Peso");
        currencies.add("AUD - Australian Dollar");
        currencies.add("HKD - Hong Kong Dollar");
        currencies.add("BRL - Brazilian Real");
        currencies.add("INR - Indian Rupee");
        currencies.add("KRW - Korean Won");
        currencies.add("CHF - Swiss Franc");
        currencies.add("ILS - Israeli New Shekel");
        currencies.add("RUB - Russian Ruble");
        currencies.add("ZAR - South African Rand");
        currencies.add("SEK - Swedish Krona");
        currencies.add("PHP - Philippine Peso");
        currencies.add("CNY - Chinese Yuan Renminbi");
        currencies.add("TRY - Turkish Lira");

        // Create an array adapter for the currency spinner
        ArrayAdapter<String> currencyDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);

        // attaching data adapter to spinner
        currencySpinner.setAdapter(currencyDataAdapter);


        // Create an array adapter for the spinner
        ArrayAdapter<String> coinDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, coins);

        // attaching data adapter to spinner
        coinSpinner.setAdapter(coinDataAdapter);

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
