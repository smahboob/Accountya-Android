package com.fandm.saad.accountya;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class FormulaSolverActivity extends AppCompatActivity {
    private final String [] cost_accounting_formula_list = {"Breakeven", "Gross Margin", "Target Net Income",
            "Contribution Margin", "Profit Margin", "Price Variance",
            "Efficiency Variance", "Variable Overhead Variance", "Ending Inventory" };

    private final String [] financial_accounting_formula_list = {"Net Income", "Return on Asset", "Return on Equity",
            "Debt to Asset", "Straight-line depreciation", "Double Declining Balance",
            "Asset Turnover Ratio", "Debt service coverage ratio", "Acid Test Ratio" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula_solver_activity);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        String formula_type = getIntent().getStringExtra("formula_type");
        String formula_tag = getIntent().getStringExtra("formula_tag");

        if(formula_type.equals("cost_accounting")){
            actionBar.setTitle(cost_accounting_formula_list[Integer.parseInt(formula_tag)]);
        }
        else {
            actionBar.setTitle(financial_accounting_formula_list[Integer.parseInt(formula_tag)]);
        }
    }
}