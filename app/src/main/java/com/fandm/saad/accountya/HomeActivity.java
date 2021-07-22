package com.fandm.saad.accountya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    Button cost_accounting_button;
    Button financial_accounting_button;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        cost_accounting_button = findViewById(R.id.home_cost_button);
        financial_accounting_button = findViewById(R.id.home_financial_icon);
        adView = findViewById(R.id.adViewHomeActivity);

        cost_accounting_button.setOnClickListener(v -> show_cost_accounting_formula_list());
        financial_accounting_button.setOnClickListener(v -> show_financial_accounting_formula_list());

        //AdMob
        //initialize ads, do this only once
        MobileAds.initialize(this, initializationStatus -> {
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    public void show_cost_accounting_formula_list() {
        Intent cost_accounting_intent = new Intent(HomeActivity.this, BaseFormulaActivity.class);
        cost_accounting_intent.putExtra("formula_list", "cost_accounting");
        startActivity(cost_accounting_intent);
    }

    public void show_financial_accounting_formula_list() {
        Intent financial_accounting_intent = new Intent(HomeActivity.this, BaseFormulaActivity.class);
        financial_accounting_intent.putExtra("formula_list", "financial_accounting");
        startActivity(financial_accounting_intent);
    }
}