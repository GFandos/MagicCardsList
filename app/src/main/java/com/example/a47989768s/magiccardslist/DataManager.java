package com.example.a47989768s.magiccardslist;

import android.content.Context;
import android.support.v4.content.CursorLoader;
import android.net.Uri;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Gerard on 29/12/2016.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(MagicContentProvider.AUTHORITY);
    private static Uri CARD_URI = URI_HELPER.getUri(Card.class);

    static void saveCards(ArrayList<Card> cards, Context context) {
        cupboard().withContext(context).put(CARD_URI, Card.class, cards);
    }

    static void deleteMovies(Context context) {
        cupboard().withContext(context).delete(CARD_URI, "_id > ?", "0");
    }

    static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, CARD_URI, null, null, null, null);
    }
}
