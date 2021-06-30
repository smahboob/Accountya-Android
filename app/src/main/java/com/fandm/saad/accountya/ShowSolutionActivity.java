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
        actionBar.setTitle("Answer");
        actionBar.setDisplayHomeAsUpEnabled(true);

        current_labels = getIntent().getStringArrayExtra("formula_labels");
        current_labels_values = getIntent().getStringArrayExtra("formula_labels_values");

        initialize_components();


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

        if(current_labels.length == 2){
            linearLayout3.setVisibility(View.INVISIBLE);
        }
        else{
            label2.setText(current_labels[2]);
            label_val2.setText(current_labels_values[2]);
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