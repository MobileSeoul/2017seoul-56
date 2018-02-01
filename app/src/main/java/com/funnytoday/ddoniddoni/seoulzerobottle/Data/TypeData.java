package com.funnytoday.ddoniddoni.seoulzerobottle.Data;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Owner on 2017-10-17.
 */

public class TypeData extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "fonts/NanumGothic.ttf"))
                             .addBold(Typekit.createFromAsset(this, "fonts/NanumGothicBold.ttf"));
    }
}
