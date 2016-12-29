package com.example.a47989768s.magiccardslist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.a47989768s.magiccardslist.databinding.CardsRowsBinding;

import java.util.List;

/**
 * Created by Gerard on 06/11/2016.
 */

public class CardsAdapter extends ArrayAdapter<Card> {

    public CardsAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Card card =  getItem(position);
        Log.w("Carta: ", card.toString());

        CardsRowsBinding binding = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.cards_rows, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.cardName.setText(card.getName());
        binding.cardType.setText(card.getType());
        Glide.with(getContext()).load(card.getImageUrl()).into(binding.cardImage);

        return binding.getRoot();

    }

}
