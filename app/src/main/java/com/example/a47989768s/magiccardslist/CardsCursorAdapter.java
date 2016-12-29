package com.example.a47989768s.magiccardslist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a47989768s.magiccardslist.databinding.CardsRowsBinding;

/**
 * Created by 47989768s on 29/11/16.
 */

public class CardsCursorAdapter extends CupboardCursorAdapter<Card> {

    public CardsCursorAdapter(Context context, Class<Card> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Card model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        CardsRowsBinding binding = DataBindingUtil.inflate(inflater, R.layout.cards_rows, parent, false);
        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Card model) {
        CardsRowsBinding binding = DataBindingUtil.getBinding(view);
        binding.cardName.setText(model.getName());
        binding.cardType.setText(model.getType());
        Glide.with(context).load(model.getImageUrl()).into(binding.cardImage);
    }
}
