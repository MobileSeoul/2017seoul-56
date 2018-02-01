package com.funnytoday.ddoniddoni.seoulzerobottle.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.funnytoday.ddoniddoni.seoulzerobottle.Activity.MapActivity;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;

/**
 * Created by Owner on 2017-08-28.
 */

public class ThreeFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LinearLayout map_one_place;
    private LinearLayout map_two_place;
    private LinearLayout map_three_place;
    private LinearLayout map_four_place;
    private LinearLayout map_five_place;

    private ImageView map_one_place1;
    private ImageView map_two_place1;
    private ImageView map_three_place1;
    private ImageView map_four_place1;
    private ImageView map_five_place1;

    public ThreeFragment() {
    }

    public static ThreeFragment newInstance(String param1, String param2) {
        ThreeFragment fragment = new ThreeFragment();
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
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        map_one_place = (LinearLayout)view.findViewById(R.id.map_one_place);
        map_two_place = (LinearLayout)view.findViewById(R.id.map_two_place);
        map_three_place = (LinearLayout)view.findViewById(R.id.map_three_place);
        map_four_place = (LinearLayout)view.findViewById(R.id.map_four_place);
        map_five_place = (LinearLayout)view.findViewById(R.id.map_five_place);

        map_one_place1 = (ImageView)view.findViewById(R.id.map_one_place1);
        map_two_place1 = (ImageView)view.findViewById(R.id.map_two_place1);
        map_three_place1 = (ImageView)view.findViewById(R.id.map_three_place1);
        map_four_place1 = (ImageView)view.findViewById(R.id.map_four_place1);
        map_five_place1 = (ImageView)view.findViewById(R.id.map_five_place1);

        map_one_place1.setOnClickListener(this);
        map_two_place1.setOnClickListener(this);
        map_three_place1.setOnClickListener(this);
        map_four_place1.setOnClickListener(this);
        map_five_place1.setOnClickListener(this);

        map_one_place.setOnClickListener(this);
        map_two_place.setOnClickListener(this);
        map_three_place.setOnClickListener(this);
        map_four_place.setOnClickListener(this);
        map_five_place.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map_one_place:
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("LOCATION", "공덕");
                startActivity(intent);
                break;
            case R.id.map_two_place:
                Intent intent1 = new Intent(getActivity(), MapActivity.class);
                intent1.putExtra("LOCATION", "구로");
                startActivity(intent1);
                break;
            case R.id.map_three_place:
                Intent intent2 = new Intent(getActivity(), MapActivity.class);
                intent2.putExtra("LOCATION", "영등포");
                startActivity(intent2);
                break;
            case R.id.map_four_place:
                Intent intent3 = new Intent(getActivity(), MapActivity.class);
                intent3.putExtra("LOCATION", "성수");
                startActivity(intent3);
                break;
            case R.id.map_five_place:
                Intent intent4 = new Intent(getActivity(), MapActivity.class);
                intent4.putExtra("LOCATION", "목동");
                startActivity(intent4);
                break;
            case R.id.map_one_place1:
                Intent intent5 = new Intent(getActivity(), MapActivity.class);
                intent5.putExtra("LOCATION", "공덕");
                startActivity(intent5);
                break;
            case R.id.map_two_place1:
                Intent intent6 = new Intent(getActivity(), MapActivity.class);
                intent6.putExtra("LOCATION", "구로");
                startActivity(intent6);
                break;
            case R.id.map_three_place1:
                Intent intent7 = new Intent(getActivity(), MapActivity.class);
                intent7.putExtra("LOCATION", "영등포");
                startActivity(intent7);
                break;
            case R.id.map_four_place1:
                Intent intent8 = new Intent(getActivity(), MapActivity.class);
                intent8.putExtra("LOCATION", "성수");
                startActivity(intent8);
                break;
            case R.id.map_five_place1:
                Intent intent9 = new Intent(getActivity(), MapActivity.class);
                intent9.putExtra("LOCATION", "목동");
                startActivity(intent9);
                break;
        }
    }
}