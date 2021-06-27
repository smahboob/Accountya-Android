package com.fandm.saad.accountya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button cost_accounting_button;
    Button financial_accounting_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        cost_accounting_button = findViewById(R.id.home_cost_button);
        financial_accounting_button = findViewById(R.id.home_financial_icon);

        cost_accounting_button.setOnClickListener(v -> show_cost_accounting_formula_list());
        financial_accounting_button.setOnClickListener(v -> show_financial_accounting_formula_list());
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