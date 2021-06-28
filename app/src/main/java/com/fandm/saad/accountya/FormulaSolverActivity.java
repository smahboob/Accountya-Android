package com.fandm.saad.accountya;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    private List<List<String>> cost_accounting_formula_label_list = new ArrayList<List<String>>() {{ add(Arrays.asList("Fixed Cost", "Sales Price Per Unit", "Variable Cost Per Unit"));
                                                                                                     add(Arrays.asList("Total Revenue", "Total Expenses"));
    }};

    private List<List<String>> financial_accounting_formula_label_list = new ArrayList<List<String>>() {{ add(Arrays.asList("Fixed Cost", "Sales Price Per Unit", "Variable Cost Per Unit"));
        add(Arrays.asList("Total Revenue", "Total Expenses"));
    }};

    private boolean cost_accounting_selected = true;
    private int formula_tag_int;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formula_solver_activity);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        String formula_type = getIntent().getStringExtra("formula_type");
        String formula_tag = getIntent().getStringExtra("formula_tag");
        formula_tag_int = Integer.parseInt(formula_tag);

        LinearLayout layout3 = findViewById(R.id.label_three_linear_layout);

        int total_required_labels = 0;

        if(formula_type.equals("cost_accounting")){
            String formula_name = cost_accounting_formula_list[formula_tag_int];
            actionBar.setTitle(formula_name);
            total_required_labels = cost_accounting_formula_map.getOrDefault(formula_name, 0);
            cost_accounting_selected = true;
            configureLabels(total_required_labels, cost_accounting_formula_label_list);
        }
        else {
            String formula_name = financial_accounting_formula_list[formula_tag_int];
            actionBar.setTitle(formula_name);
            total_required_labels = financial_accounting_formula_map.getOrDefault(formula_name,0);
            cost_accounting_selected = false;
            configureLabels(total_required_labels, financial_accounting_formula_label_list);
        }

        if(total_required_labels == 2){
            layout3.setVisibility(View.INVISIBLE);
        }

    }

    private void configureLabels(int total_labels, List<List<String>> label_list){

        TextView one_tv = findViewById(R.id.label_one_tv), two_tv = findViewById(R.id.label_two_tv), three_tv = findViewById(R.id.label_three_tv);
        //EditText one_et = findViewById(R.id.label_one_et), two_et = findViewById(R.id.label_two_et), three_et = findViewById(R.id.label_three_et);

        one_tv.setText(label_list.get(formula_tag_int).get(0));
        two_tv.setText(label_list.get(formula_tag_int).get(1));
        if(total_labels == 3){
            three_tv.setText(label_list.get(formula_tag_int).get(2));
        }

    }
}