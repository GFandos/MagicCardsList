package com.example.a47989768s.magiccardslist;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by 47989768s on 19/10/16.
 */

public class MagicTGGetAllCardsApi {

    private String url="https://api.magicthegathering.io/v1/cards?page=5&pageSize=100";

    public ArrayList<Card> getCards() {

        ArrayList<Card> cards = new ArrayList<>();

        try {

            JSONObject jsonO = new JSONObject(HttpUtils.get(url));
            JSONArray  jsonCards = jsonO.getJSONArray("cards");
            String name, rarity, type, imageUrl;

            for (int i = 0; i < jsonCards.length(); ++i) {

                JSONObject object = jsonCards.getJSONObject(i);

                name = object.getString("name");
                rarity = object.getString("rarity");
                type = object.getString("type");
                imageUrl = object.getString("imageUrl");

                Card c = new Card(name, rarity, type, imageUrl);
                cards.add(c);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;

    }

}
