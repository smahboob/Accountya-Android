package com.fandm.saad.accountya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class FormulaSolverActivity extends AppCompatActivity {
    private final String [] cost_accounting_formula_list = {"Breakeven", "Gross Margin", "Target Net Income",
            "Contribution Margin", "Profit Margin", "Price Variance",
            "Efficiency Variance", "Variable Overhead Variance", "Ending Inventory" };

    private final String [] financial_accounting_formula_list = {"Net Income", "Return on Asset", "Return on Equity",
            "Debt to Asset", "Straight-line depreciation", "Double Declining Balance",
            "Asset Turnover Ratio", "Debt service coverage ratio", "Acid Test Ratio" };

    private final Map<String, Integer> cost_accounting_formula_map = new HashMap<>(
                    Map.ofEntries(Map.entry("Breakeven",3), Map.entry("Gross Margin",2),
                    Map.entry("Target Net Income",3), Map.entry("Contribution Margin",3), Map.entry("Profit Margin",2),
                    Map.entry("Price Variance",3), Map.entry("Efficiency Variance",3), Map.entry("Variable Overhead Variance",2),
                    Map.entry("Ending Inventory",3)
                    ));
    private final Map<String, Integer> financial_accounting_formula_map = new HashMap<>(
            Map.ofEntries(Map.entry("Net Income",2), Map.entry("Return on Asset",2),
                    Map.entry("Return on Equity",2), Map.entry("Debt to Asset",3), Map.entry("Straight-line depreciation",3),
                    Map.entry("Double Declining Balance",3), Map.entry("Asset Turnover Ratio",2), Map.entry("Debt service coverage ratio",2),
                    Map.entry("Acid Test Ratio",3)
            ));

    private final List<List<String>> cost_accounting_formula_label_list = new ArrayList<List<String>>() {{ add(Arrays.asList("Fixed Cost", "Sales Price Per Unit", "Variable Cost Per Unit"));
        add(Arrays.asList("Total Revenue", "Total Expenses"));add(Arrays.asList("Total Revenue", "Variable Cost", "Fixed Cost"));add(Arrays.asList("Total Revenue", "Variable Cost", "Units Sold"));
        add(Arrays.asList("Total Revenue", "Total Expenses"));add(Arrays.asList("Actual cost incurred", "Standard cost", "Actual quantity of units"));
        add(Arrays.asList("Actual unit usage", "Standard unit usage","Standard cost per unit"));add(Arrays.asList("Spending Variance", "Efficiency Variance"));
        add(Arrays.asList("Beginning Inventory", "Purchases", "Cost of Sales"));
    }};

    private final List<List<String>> financial_accounting_formula_label_list = new ArrayList<List<String>>() {{ add(Arrays.asList("Total Revenue", "Total Expenses"));
        add(Arrays.asList("Net Income", "Average Total Assets "));add(Arrays.asList("Net Income", "Average Shareholder's Equity"));add(Arrays.asList("Short Term Debt", "Long Term Debt", "Total Assets"));
        add(Arrays.asList("Initial Cost of Assets", "Salvage Value of Asset:", "Useful Life of Asset"));add(Arrays.asList("Initial Cost of Assets", "Salvage Value of Asset", "Useful Life of Asset"));
        add(Arrays.asList("Net Sales", "Average Total Assets"));add(Arrays.asList("Net Operating Income", "Total Debt Services"));
        add(Arrays.asList("Current Assets", "Current Liabilities", "Ending Inventory"));
    }};

    private int formula_tag_int;

    private final String [] current_label_list = new String[3];

    private EditText label_one_et, label_two_et, label_three_et;

    private String formula_name_global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Tag: ", "Came to teh on create of formula");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula_solver_activity);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Enter values");
        actionBar.setDisplayHomeAsUpEnabled(true);

        label_one_et = findViewById(R.id.label_one_et);
        label_two_et = findViewById(R.id.label_two_et);
        label_three_et = findViewById(R.id.label_three_et);

        String formula_type = getIntent().getStringExtra("formula_type");
        String formula_tag = getIntent().getStringExtra("formula_tag");
        formula_tag_int = Integer.parseInt(formula_tag);


        int total_required_labels = 0;

        //extract the formula type passed from last activity, and determine which and how many label to display.
        if(formula_type.equals("cost_accounting")){
            String formula_name = cost_accounting_formula_list[formula_tag_int];
            formula_name_global = formula_name;
            //actionBar.setTitle(formula_name);
            total_required_labels = cost_accounting_formula_map.getOrDefault(formula_name, 0);
            configureLabels(total_required_labels, cost_accounting_formula_label_list);
        }
        else {
            String formula_name = financial_accounting_formula_list[formula_tag_int];
            formula_name_global = formula_name;
            //actionBar.setTitle(formula_name);
            total_required_labels = financial_accounting_formula_map.getOrDefault(formula_name,0);
            configureLabels(total_required_labels, financial_accounting_formula_label_list);
        }

        Button calculate = findViewById(R.id.calculate_button);
        LinearLayout layout3 = findViewById(R.id.label_three_linear_layout);

        //if the formula only needs two labels, just display two and hide the third one.
        if(total_required_labels == 2){
            layout3.setVisibility(View.INVISIBLE);
        }

        int finalTotal_required_labels = total_required_labels;
        calculate.setOnClickListener(v -> show_solution(finalTotal_required_labels));
    }

    //this method is an onclick listener to take the user to final page, where answer is calculated and displayed.
    private void show_solution(int total_required_labels) {
        String label_val_1 = label_one_et.getText().toString();
        String label_val_2 = label_two_et.getText().toString();

        if(label_val_1.isEmpty() || label_val_2.isEmpty()){
            Snackbar.make(findViewById(R.id.formula_solver_layout), "Missing information required to calculate.", Snackbar.LENGTH_LONG);
            Toast.makeText(this, "Missing information to calculate.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] current_label_values = new String[total_required_labels];
        current_label_values[0] = label_val_1;
        current_label_values[1] = label_val_2;
        if(total_required_labels == 3){
            String label_val_3 = label_three_et.getText().toString();
            current_label_values[2] = label_val_3;
            if(label_val_3.isEmpty()){
                Snackbar.make(findViewById(R.id.formula_solver_layout), "Missing information required to calculate.", Snackbar.LENGTH_LONG);
                Toast.makeText(this, "Missing information to calculate.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Intent show_solution_intent = new Intent(FormulaSolverActivity.this, ShowSolutionActivity.class);
        show_solution_intent.putExtra("formula_labels", current_label_list);
        show_solution_intent.putExtra("formula_labels_values", current_label_values);
        show_solution_intent.putExtra("formula_type", getIntent().getStringExtra("formula_type"));
        show_solution_intent.putExtra("formula_name", formula_name_global);
        show_solution_intent.putExtra("formula_tag", getIntent().getStringExtra("formula_tag"));
        startActivity(show_solution_intent);
    }

    private void configureLabels(int total_labels, List<List<String>> label_list){

        TextView one_tv = findViewById(R.id.label_one_tv), two_tv = findViewById(R.id.label_two_tv), three_tv = findViewById(R.id.label_three_tv);
        //EditText one_et = findViewById(R.id.label_one_et), two_et = findViewById(R.id.label_two_et), three_et = findViewById(R.id.label_three_et);

        one_tv.setText(label_list.get(formula_tag_int).get(0));
        two_tv.setText(label_list.get(formula_tag_int).get(1));
        current_label_list[0] = (label_list.get(formula_tag_int).get(0));
        current_label_list[1] = (label_list.get(formula_tag_int).get(1));
        if(total_labels == 3){
            three_tv.setText(label_list.get(formula_tag_int).get(2));
            current_label_list[2] = (label_list.get(formula_tag_int).get(2));
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Tag: ", "came here to on options in formula solver");
        if (item.getItemId() == android.R.id.home) {
            Intent newIntent = new Intent(FormulaSolverActivity.this, BaseFormulaActivity.class);
            newIntent.putExtra("formula_list", getIntent().getStringExtra("formula_type"));
            startActivity(newIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}