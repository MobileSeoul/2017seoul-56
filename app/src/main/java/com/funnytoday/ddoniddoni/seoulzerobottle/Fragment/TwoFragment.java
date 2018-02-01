package com.funnytoday.ddoniddoni.seoulzerobottle.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;

/**
 * Created by Owner on 2017-08-28.
 */

public class TwoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView image_one;
    private ImageView image_two;
    private ImageView image_three;
    private ImageView image_four;


    public TwoFragment() {
    }

    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        image_one = (ImageView)view.findViewById(R.id.image_one);
        image_two = (ImageView)view.findViewById(R.id.image_two);
        image_three = (ImageView)view.findViewById(R.id.image_three);
        image_four = (ImageView)view.findViewById(R.id.image_four);


        Glide.with(getActivity()).load("").placeholder(R.drawable.bottle_one).into(image_one);
        Glide.with(getActivity()).load("").placeholder(R.drawable.bottle_two).into(image_two);
        Glide.with(getActivity()).load("").placeholder(R.drawable.bottle_three).into(image_three);
        Glide.with(getActivity()).load("").placeholder(R.drawable.bottle_four).into(image_four);
        return view;
    }

}

