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

    private static String url="https://api.magicthegathering.io/v1/cards";

    public static String getUrl(String rarity, String color) {

        Uri builtUri = Uri.parse(url)
                .buildUpon()
                .appendQueryParameter("rarity", rarity)
                .appendQueryParameter("colors", color)
                .build();
        Log.d("DEBUG", builtUri.toString());
        return builtUri.toString();

    }

    public ArrayList<Card> getCards(String rarity, String color) {

        ArrayList<Card> cards = new ArrayList<>();

        try {

            String newUrl = getUrl(rarity, color);
            JSONObject jsonO = new JSONObject(HttpUtils.get(newUrl));
            JSONArray  jsonCards = jsonO.getJSONArray("cards");
            String name, type, imageUrl, cardColor, cardDescription;

            for (int i = 0; i < jsonCards.length(); ++i) {

                JSONObject object = jsonCards.getJSONObject(i);

                name = object.getString("name");
                rarity = object.getString("rarity");
                type = object.getString("type");
                cardColor = object.getString("colors");

                if(object.has("imageUrl")) imageUrl = object.getString("imageUrl");
                else imageUrl = object.getString("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png");

                cardDescription = object.getString("originalText");

                Card c = new Card(name, rarity, type, imageUrl, cardColor, cardDescription);
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
