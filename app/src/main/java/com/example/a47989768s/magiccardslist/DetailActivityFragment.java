package com.example.a47989768s.magiccardslist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
import com.bumptech.glide.Glide;
import android.databinding.DataBindingUtil;
import com.example.a47989768s.magiccardslist.databinding.FragmentDetailBinding;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,  R.layout.fragment_detail, container, false);
        View view = binding.getRoot();

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
        Log.d("CARD", card.toString());

        binding.cardName.setText(card.getName());
        binding.cardType.setText(card.getType());
        binding.cardRarity.setText(card.getRarity());
        binding.cardDescription.setText(card.getDescription());

        Glide.with(getContext()).load(card.getImageUrl()).into(binding.cardImage);

    }
}
