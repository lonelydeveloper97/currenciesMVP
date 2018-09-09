package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lonelydeveloper97.currenciesmvp.R;
import com.lonelydeveloper97.currenciesmvp.currencies.model.BaseCurrency;
import com.lonelydeveloper97.currenciesmvp.utills.Format;

import java9.util.function.Consumer;


public class CurrenciesViewHolder extends ViewHolder {
    private final EditText amountField;
    private final TextView name;

    public CurrenciesViewHolder(@NonNull View itemView, Consumer<BaseCurrency> consumer) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        amountField = itemView.findViewById(R.id.amount);

        View.OnClickListener onClickListener = v -> consumer.accept(new BaseCurrency((String) name.getText(), Double.parseDouble(amountField.getText().toString())));
        itemView.setOnClickListener(onClickListener);
        amountField.setOnClickListener(onClickListener);
    }

    public void updateCurrency(@NonNull String name, @NonNull Double amount) {
        this.name.setText(name);
        this.amountField.setText(Format.PRICE_FORMAT.format(amount));
    }
}
