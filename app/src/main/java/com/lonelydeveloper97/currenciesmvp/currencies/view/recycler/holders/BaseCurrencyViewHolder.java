package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lonelydeveloper97.currenciesmvp.R;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.utills.Format;

import java9.util.function.Consumer;

public class BaseCurrencyViewHolder extends RecyclerView.ViewHolder {
    private final EditText amountField;
    private TextView name;
    private final View itemView;


    public BaseCurrencyViewHolder(@NonNull View itemView, Consumer<BaseCurrency> consumer) {
        super(itemView);

        this.itemView = itemView;

        amountField = itemView.findViewById(R.id.amount);
        amountField.setFocusableInTouchMode(true);
        amountField.setFocusable(true);
        amountField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    amountField.setText("0");
                }
                if (amountField.isFocused() && charSequence.length() != 0)
                    consumer.accept(new BaseCurrency((String) name.getText(), Double.parseDouble(charSequence.toString())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        amountField.setImeOptions(EditorInfo.IME_ACTION_DONE);

        name = itemView.findViewById(R.id.name);
        name.setTextColor(name.getContext().getResources().getColor(R.color.colorPrimary));
    }

    public void updateBase(@NonNull BaseCurrency currency) {
        if (hidden()) {
            itemView.setVisibility(View.VISIBLE);
        }
        name.setText(currency.getName());
        amountField.setText(Format.PRICE_FORMAT.format(currency.getAmount()));
    }

    private boolean hidden() {
        return itemView.getVisibility() != View.VISIBLE;
    }

    public void hide() {
        itemView.setVisibility(View.GONE);
    }
}
