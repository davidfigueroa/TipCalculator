package com.example.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etBillAmount;
	TextView tvTipAmount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBillAmount = (EditText)findViewById(R.id.etBillAmount);
        tvTipAmount = (TextView)findViewById(R.id.tvTip);
        
        etBillAmount.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
		    	tvTipAmount.setText(getString(R.string.zero_tip));
			}
		});
    }
    
    public void onTipPercentageClick(View v) {
    	if (etBillAmount.getText().length() == 0) {
    		Toast.makeText(this, getString(R.string.enter_bill_amount), Toast.LENGTH_SHORT).show();
    		return;
    	}
    	BigDecimal billAmount = new BigDecimal(etBillAmount.getText().toString());
    	BigDecimal tipPercent = new BigDecimal(v.getTag().toString());
    	BigDecimal tipAmount = billAmount.multiply(tipPercent).divide(new BigDecimal(100));
    	tipAmount.setScale(2, RoundingMode.CEILING);
    	
    	tvTipAmount.setText(NumberFormat.getCurrencyInstance().format(tipAmount));
    }
}
