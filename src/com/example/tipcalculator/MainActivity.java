package com.example.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText etBillAmount;
	EditText etTipPercent;
	TextView tvTipAmount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBillAmount = (EditText)findViewById(R.id.etBillAmount);
        etTipPercent = (EditText)findViewById(R.id.etTipPercent);
        tvTipAmount = (TextView)findViewById(R.id.tvTip);
        
        TextWatcher billAmountAndTipPercentWatcher = new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				recalculateTip();
			}
		};
			
        etBillAmount.addTextChangedListener(billAmountAndTipPercentWatcher);
        etTipPercent.addTextChangedListener(billAmountAndTipPercentWatcher);
    }
    
    private void recalculateTip() {
    	if (etBillAmount.getText().length() == 0 || etTipPercent.getText().length() == 0) {
    		tvTipAmount.setText(getString(R.string.zero_tip));
    		return;
    	}

    	BigDecimal billAmount = new BigDecimal(etBillAmount.getText().toString());
    	BigDecimal tipPercent = new BigDecimal(etTipPercent.getText().toString());
    	BigDecimal tipAmount = billAmount.multiply(tipPercent).divide(new BigDecimal(100));
    	tipAmount.setScale(2, RoundingMode.CEILING);
    	
    	tvTipAmount.setText(NumberFormat.getCurrencyInstance().format(tipAmount));
    }
}
