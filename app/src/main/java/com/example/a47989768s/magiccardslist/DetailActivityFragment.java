package com.example.a47989768s.magiccardslist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView cardImage;
    private TextView cardName;
    private TextView cardType;
    private TextView cardRarity;
    private TextView cardDescription;




    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if(i != null) {
            Card card = (Card) i.getSerializableExtra("card");

            if(card != null) {
                updateUI(card);
            }
        }

        return view;
    }

    private void updateUI(Card card) {
        //Log.d("CARD", card.toString());

        cardImage = (ImageView) view.findViewById(R.id.cardImage);
        cardName = (TextView) view.findViewById(R.id.cardName);
        cardType = (TextView) view.findViewById(R.id.cardType);
        cardRarity = (TextView) view.findViewById(R.id.cardRarity);
        cardDescription = (TextView) view.findViewById(R.id.cardDescription);

        Glide.with(getContext()).load(card.getImageUrl()).into(cardImage);
        cardName.setText(card.getName());
        cardType.setText(card.getType());
        cardRarity.setText(card.getRarity());
        cardDescription.setText(card.getDescription());

    }
}
