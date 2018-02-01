package com.funnytoday.ddoniddoni.seoulzerobottle.Data;


import com.funnytoday.ddoniddoni.seoulzerobottle.R;

import java.util.ArrayList;

/**
 * Created by Owner on 2017-10-17.
 */

public class ItemData {
    public int item_image;
    public String item_text;
    public String item_exp;

    public ItemData(int item_image, String item_text, String item_exp) {
        this.item_image = item_image;
        this.item_text = item_text;
        this.item_exp = item_exp;
    }

    public static ArrayList<ItemData> createItemList(int numItem) {
        ArrayList<ItemData> contacts = new ArrayList<ItemData>();
        contacts.add(new ItemData(R.drawable.item_one,"아이스크림", "1000p" ));
        contacts.add(new ItemData(R.drawable.item_two,"팝콘", "6500p"));
        contacts.add(new ItemData(R.drawable.item_three,"조각케이크", "2000p"));
        contacts.add(new ItemData(R.drawable.item_four,"초콜릿", "10000p"));
        contacts.add(new ItemData(R.drawable.item_five,"샌드위치", "4000p"));
        contacts.add(new ItemData(R.drawable.item_six,"커피", "2000p"));
        contacts.add(new ItemData(R.drawable.item_seven,"피자", "14900p"));
        contacts.add(new ItemData(R.drawable.item_eight,"치킨", "12900p"));

        return contacts;
    }
}
