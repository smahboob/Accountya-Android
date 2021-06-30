package com.fandm.saad.accountya;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowSolutionActivity extends AppCompatActivity {

    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private TextView label1, label2, label3, label_val1, label_val2, label_val3, formula_text;
    private String [] current_labels, current_labels_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_solution);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        current_labels = getIntent().getStringArrayExtra("formula_labels");
        current_labels_values = getIntent().getStringArrayExtra("formula_labels_values");
        String current_formula_name = getIntent().getStringExtra("formula_name");
        actionBar.setTitle(current_formula_name);
        initialize_components();
        calculate_solution(current_labels_values, current_formula_name);
    }

    private void initialize_components(){
        linearLayout1 = findViewById(R.id.label_one_linear_layout_ss);
        linearLayout2 = findViewById(R.id.label_two_linear_layout_ss);
        linearLayout3 = findViewById(R.id.label_three_linear_layout_ss);

        label1 = findViewById(R.id.label_one_tv_ss);
        label2 = findViewById(R.id.label_two_tv_ss);
        label3 = findViewById(R.id.label_three_tv_ss);

        label_val1 = findViewById(R.id.label_one_et_ss);
        label_val2 = findViewById(R.id.label_two_et_ss);
        label_val3 = findViewById(R.id.label_three_et_ss);

        formula_text = findViewById(R.id.formula_tv_ss);

        configure_labels();
    }

    private void configure_labels(){
        label1.setText(current_labels[0]);
        label_val1.setText(current_labels_values[0]);

        label2.setText(current_labels[1]);
        label_val2.setText(current_labels_values[1]);

        if(current_labels_values.length == 2){
            linearLayout3.setVisibility(View.INVISIBLE);
        }
        else{
            label3.setText(current_labels[2]);
            label_val3.setText(current_labels_values[2]);
        }
    }

    private void calculate_solution(String[]current_labels_values, String formula_name){
        double val1 = Double.parseDouble(current_labels_values[0]);
        double val2 = Double.parseDouble(current_labels_values[1]);
        double val3 = 0;
        if(current_labels_values.length == 3){
            val3 = Double.parseDouble(current_labels_values[2]);
        }

        if(formula_name.equals("Breakeven")){
            String formula = "Formula: [(Fixed Cost / Selling Price) - Variable Cost]";
            formula_text.setText(formula);
            double answer = (val1/val2) - val3;
            Log.d("Answer: ", String.valueOf(answer));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Tag: ", "came here to on options in show solutions");
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent newIntent = new Intent(ShowSolutionActivity.this, FormulaSolverActivity.class);
                newIntent.putExtra("formula_type", getIntent().getStringExtra("formula_type"));
                newIntent.putExtra("formula_tag", getIntent().getStringExtra("formula_tag"));
                startActivity(newIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}