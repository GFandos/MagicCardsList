package com.example.a47989768s.magiccardslist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cards_rows, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.cardName);
        TextView type = (TextView) convertView.findViewById(R.id.cardType);
        ImageView image = (ImageView) convertView.findViewById(R.id.cardImage);

        name.setText(card.getName());
        type.setText(card.getType());
        Glide.with(getContext()).load(card.getImageUrl()).into(image);

        return convertView;

    }

}
