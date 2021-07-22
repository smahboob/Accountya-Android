package com.fandm.saad.accountya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaseFormulaActivity extends AppCompatActivity {

    private final String [] cost_accounting_formula_list = {"Breakeven", "Gross Margin", "Target Net Income",
            "Contribution Margin", "Profit Margin", "Price Variance",
            "Efficiency Variance", "Variable Overhead Variance", "Ending Inventory" };

    private final String [] financial_accounting_formula_list = {"Net Income", "Return on Asset", "Return on Equity",
            "Debt to Asset", "Straight-line depreciation", "Double Declining Balance",
            "Asset Turnover Ratio", "Debt service coverage ratio", "Acid Test Ratio" };

    Button formula1,formula2, formula3, formula4, formula5, formula6, formula7, formula8, formula9;
    private String formula_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_accounting_activity);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        formula1 = findViewById(R.id.formula_button_1); formula2 = findViewById(R.id.formula_button_2);
        formula3 = findViewById(R.id.formula_button_3); formula4 = findViewById(R.id.formula_button_4);
        formula5 = findViewById(R.id.formula_button_5); formula6 = findViewById(R.id.formula_button_6);
        formula7 = findViewById(R.id.formula_button_7); formula8 = findViewById(R.id.formula_button_8);
        formula9 = findViewById(R.id.formula_button_9);

        String relevant_list = getIntent().getStringExtra("formula_list");

        if(!relevant_list.isEmpty() & relevant_list.equals("cost_accounting")){
            display_formula_list(cost_accounting_formula_list);
            actionBar.setTitle("Cost Accounting");
            formula_type = "cost_accounting";
        }
        else if(!relevant_list.isEmpty() & relevant_list.equals("financial_accounting")){
            display_formula_list(financial_accounting_formula_list);
            actionBar.setTitle("Financial Accounting");
            formula_type = "financial_accounting";
        }
    }

    private void display_formula_list(String [] formula_list){
        formula1.setText(formula_list[0]);
        formula2.setText(formula_list[1]);
        formula3.setText(formula_list[2]);
        formula4.setText(formula_list[3]);
        formula5.setText(formula_list[4]);
        formula6.setText(formula_list[5]);
        formula7.setText(formula_list[6]);
        formula8.setText(formula_list[7]);
        formula9.setText(formula_list[8]);
    }

    public void show_formula_details(View view) {
        Intent open_formula_solver = new Intent(BaseFormulaActivity.this, FormulaSolverActivity.class);
        open_formula_solver.putExtra("formula_tag", String.valueOf(view.getTag()));
        open_formula_solver.putExtra("formula_type", formula_type);
        open_formula_solver.putExtra("toast_message", "");
        startActivity(open_formula_solver);
    }
}