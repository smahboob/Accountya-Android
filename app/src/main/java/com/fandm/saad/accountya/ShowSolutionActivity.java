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
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShowSolutionActivity extends AppCompatActivity {

    private LinearLayout linearLayout1, linearLayout2, linearLayout3;
    private TextView label1, label2, label3, label_val1, label_val2, label_val3, formula_text, answer_text, explanation_text;
    private String [] current_labels, current_labels_values;
    private DecimalFormat df;

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
        Log.d("TAG formula name ", current_formula_name);
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
        answer_text = findViewById(R.id.answer_tv_ss);
        explanation_text = findViewById(R.id.explanation_tv_ss);
        df = new DecimalFormat("#.####");

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
        double answer;
        String formula_explanation;
        String formula;

        //hide the third value label, if the total labels passed in were 2, and only parse the value in case if the number of labels is 3
        if(current_labels_values.length == 3){
            val3 = Double.parseDouble(current_labels_values[2]);
        }

        switch (formula_name) {
            //cost accounting formula
            case "Breakeven": {
                formula = "Formula: [(Fixed Cost / Sales Price Per Unit) - Variable Cost Per Unit]";
                answer = (val1 / val2) - val3;
                formula_explanation = "The break-even point (BEP) is the point at which total cost and total revenue are equal, i.e. 'even'. There is no net loss or gain, and one has 'broken even', though opportunity costs have been paid and capital has received the risk-adjusted, expected return. In short, all costs that must be paid are paid, and there is neither profit nor loss.";
                break;
            }
            case "Gross Margin": {
                formula = "Formula: [(Total Revenue - Cost of goods sold) / Total Revenue]";
                answer = (val1 - val2) / val1;
                formula_explanation = "Gross margin is the sales revenue a company retains after incurring the direct costs associated with producing the goods it sells, and the services it provides. The higher the gross margin, the more capital a company retains on each dollar of sales, which it can then use to pay other costs or satisfy debt obligations. ";
                break;
            }
            case "Target Net Income": {
                formula = "Formula: [(Total Revenue - Variable Cost) - Fixed Cost]";
                answer = (val1 - val2) - val3;
                formula_explanation = "Target income is the profit that the managers of a company expect to attain for a designated accounting period. It is a key concept in a corporate control system that drives corrective management actions.";
                break;
            }
            case "Contribution Margin": {
                formula = "Formula: [(Total Revenue - Variable Cost) / Units Sold]";
                answer = (val1 - val2) / val3;
                formula_explanation = "Contribution margin per unit is the dollar amount of a product’s selling price exceeds its variable costs. In other words, it’s the amount of revenues from the sale of one unit that is left over after the variable costs for that unit have been paid. You can think it as the amount of money that each unit brings in to pay for fixed costs.";
                break;
            }
            case "Profit Margin": {
                formula = "Formula: [(Total Revenue - Total Expenses) / Total Revenue]";
                answer = (val1 - val2) / val1;
                formula_explanation = "Profit margin is one of the commonly used profitability ratios to gauge the degree to which a company or a business activity makes money. It represents what percentage of sales has turned into profits. Simply put, the percentage figure indicates how many cents of profit the business has generated for each dollar of sale.";
                break;
            }
            case "Price Variance": {
                formula = "Formula: [(Actual Cost incurred - Standard Cost) * Actual Quantity]";
                answer = (val1 - val2) * val3;
                formula_explanation =  "If the actual cost incurred is lower than the standard cost, this is considered a favorable price variance. If the actual cost incurred is higher than the standard cost, this is considered an unfavorable price variance.The price variance concept can be applied to any type of cost.";
                break;
            }
            case "Efficiency Variance": {
                formula = "Formula: [(Actual Cost incurred - Standard Cost) * Standard Cost]";
                answer = (val1 - val2) / val3;
                formula_explanation = "Efficiency variance is the difference between the theoretical amount of inputs required to produce a unit of output and the actual number of inputs used to produce the unit of output. In manufacturing, efficiency variance can be used to analyze the effectiveness of an operation.";
                break;
            }
            case "Variable Overhead Variance": {
                formula = "Formula: [Spending Variance - Efficiency Variance]";
                answer = (val1 - val2);
                formula_explanation = "Variable overhead efficiency variance refers to the difference between the true time it takes to manufacture a product and the time budgeted for it, as well as the impact of that difference. It arises from variance in productive efficiency. Irrespective of the production, the standard variable overhead rate remains the same.";
                break;
            }
            case "Ending Inventory": {
                formula = "Formula: [(Beginning Inventory + Purchases) - Cost of goods sold]";
                answer = (val1 + val2) - val3;
                formula_explanation = "Ending Inventory formula calculates the value of goods available for sale at the end of the accounting period. Usually, it is recorded on the balance sheet at the lower of cost or its market value.";
                break;
            }

            //Financial Accounting Formula
            case "Net Income":{
                formula = "Formula: [Total Revenue - Total Expenses]";
                answer = val1 - val2;
                formula_explanation = "Net income is calculated as sales minus cost of goods sold, selling, general and administrative expenses, and other expenses. It is a useful number for investors to assess how much revenue exceeds the expenses of an organization. This number appears on a company's income statement and is also an indicator of a company's profitability.";
                break;
            }
            case "Return on Asset": {
                formula = "Formula: [Net Income / Average Total Assets]";
                answer = (val1 / val2);
                formula_explanation = "Return on assets (ROA) is an indicator of how profitable a company is relative to its total assets. ROA gives a manager, investor, or analyst an idea as to how efficient a company's management is at using its assets to generate earnings. Return on assets is displayed as a percentage. ROA is best used when comparing a company to its previous performance.";
                break;
            }
            case "Return on Equity": {
                formula = "Formula: [Net Income / Shareholder Equity]";
                answer = (val1 / val2);
                formula_explanation = "Return on equity (ROE) is a measure of financial performance calculated by dividing net income by shareholders' equity. Because shareholders' equity is equal to a company’s assets minus its debt, ROE is considered the return on net assets. ROE is considered a measure of how effectively management is using a company’s assets to create profits.";
                break;
            }
            case "Debt to Asset": {
                formula = "Formula: [(Short Term Debt + Long Term Debt) / Total Assets]";
                answer = (val1 + val2) / val3;
                Log.d("TAG answer ", String.valueOf(answer));
                formula_explanation = "Total-debt-to-total-assets is a leverage ratio that defines the total amount of debt relative to assets owned by a company. Using this metric, analysts can compare one company's leverage with that of other companies in the same industry. This information can reflect how financially stable a company is.";
                break;
            }
            case "Straight-line depreciation": {
                formula = "Formula: [(Initial Cost - Salvage Value) / Useful Life]";
                answer = (val1 - val2) / val3;
                formula_explanation = "Straight line depreciation is a common method of depreciation where the value of a fixed asset is reduced gradually over its useful life.The default method used to gradually reduce the carrying amount of a fixed asset over its useful life is called Straight Line Depreciation.";
                break;
            }
            case "Double Declining Balance": {
                formula = "Formula: [Final book value * (Depreciation Rate / 100)]";
                List<Double> depreciation_list = new ArrayList<>();

                double depreciationRate = 2 * ((1/val3) * 100);
                double netBookValue = val1;
                double itr = 1;
                while(itr < val3){
                    double depreciation = netBookValue * (depreciationRate/100);
                    depreciation_list.add(Double.parseDouble(df.format(depreciation)));
                    netBookValue = netBookValue - depreciation;
                    itr = itr + 1;
                }
                double finalBookValue = netBookValue;
                double finalDepreciation = finalBookValue * (depreciationRate/100);

                if((finalBookValue - finalDepreciation) < val2){
                    finalDepreciation = finalBookValue - val2;
                }
                answer = finalDepreciation;

                String yearly_data = "The yearly deprecations are: \n" + Arrays.toString(depreciation_list.toArray());
                Log.d("TAG: ", yearly_data);
                formula_explanation = yearly_data + "\n------\nDouble declining balance method is a form of an accelerated depreciation method in which the asset value is depreciated at twice the rate it is done in the straight-line method.The asset will depreciate by the same amount however it will be expensed higher in early years of its useful life";
                break;
            }
            case "Asset Turnover Ratio": {
                formula = "Formula: [Net Sales / Average Total Assets]";
                answer = (val1 / val2);
                formula_explanation = "This ratio measures the value of a company's revenues relative to the value of its assets. The asset turnover ratio can be used as an indicator of the efficiency with which a company is using its assets to generate revenue.The higher the ratio, the more efficient a company is at generating revenue from its assets.";
                break;
            }
            case "Debt service coverage ratio": {
                formula = "Formula: [Net Operating Income / Total Debt Services]";
                answer = (val1 / val2);
                formula_explanation = "In corporate finance, the debt-service coverage ratio (DSCR) is a measurement of the cash flow available to pay current debt obligations. The ratio states net operating income as a multiple of debt obligations due within one year, including interest, principal, sinking-fund and lease payments.";
                break;
            }
            case "Acid Test Ratio": {
                formula = "Formula: [(Current Assets - Ending Inventory) / Current Liabilities]";
                answer = (val1 - val3) / val2;
                formula_explanation = "The acid-test ratio uses a firm's balance sheet data as an indicator of whether it has sufficient short-term assets to cover its short-term liabilities. This metric is more useful in certain situations than the current ratio, since it ignores assets such as inventory, which may be difficult to quickly liquidate.";
                break;
            }
            default:
                return;
        }

        String answer_str = "Answer: " + df.format(answer);
        formula_text.setText(formula);
        answer_text.setText(answer_str);
        explanation_text.setText(formula_explanation);
    }

    //we override this method to send data back to the previous activity.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Tag: ", "came here to on options in show solutions");
        if (item.getItemId() == android.R.id.home) {
            Intent newIntent = new Intent(ShowSolutionActivity.this, FormulaSolverActivity.class);
            newIntent.putExtra("formula_type", getIntent().getStringExtra("formula_type"));
            newIntent.putExtra("formula_tag", getIntent().getStringExtra("formula_tag"));
            startActivity(newIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}