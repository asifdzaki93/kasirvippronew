package id.kasirvippro.android.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

import id.kasirvippro.android.feature.sell.confirmation.ConfirmationContract;

public class PaymentNumberTextWatcher implements TextWatcher {

    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;

    private EditText et;
    private ConfirmationContract.Presenter presenter;

    public PaymentNumberTextWatcher(EditText et, ConfirmationContract.Presenter p)
    {
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        this.et = et;
        presenter = p;
        hasFractionalPart = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";

    @Override
    public void afterTextChanged(Editable s)
    {
        et.removeTextChangedListener(this);
        String editable = et.getText().toString();
        if(editable.equals("0.00")){
            et.setText("");
            et.addTextChangedListener(this);
            return;
        }



        try {
            int inilen, endlen;
            inilen = et.getText().length();

            String v = editable.replace(String.valueOf(","), "");
            Number n = df.parse(v);
            int cp = et.getSelectionStart();
            if (hasFractionalPart) {
                et.setText(df.format(n).replace(".", ",").substring(0, dfnd.format(n).length()));
            } else {
                et.setText(dfnd.format(n).replace(".", ",").substring(0, dfnd.format(n).length()));
            }
            endlen = et.getText().length();
            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= et.getText().length()) {
                et.setSelection(sel);
            } else {
                // place cursor at the end?
                et.setSelection(et.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        presenter.countCashback();

        et.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(".")) {
            hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
    }

}