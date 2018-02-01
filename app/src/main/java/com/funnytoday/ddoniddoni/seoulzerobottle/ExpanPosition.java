package com.funnytoday.ddoniddoni.seoulzerobottle;

import java.util.ArrayList;

/**
 * Created by Owner on 2017-09-18.
 */

public class ExpanPosition {

    public String position;
    public String image;
    public ArrayList<String> items = new ArrayList<String>();

    public ExpanPosition(String position){
        this.position = position;
    }

    public String toString() {
        return position;
    }
}


