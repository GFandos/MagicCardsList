package com.example.a47989768s.magiccardslist;

/**
 * Created by 47989768s on 15/11/16.
 */

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MagicContentProvider extends CupboardContentProvider{

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Card.class);
    }

    public MagicContentProvider() {
        super(AUTHORITY, 1);
    }

}
