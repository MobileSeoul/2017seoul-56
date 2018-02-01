package com.funnytoday.ddoniddoni.seoulzerobottle.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.funnytoday.ddoniddoni.seoulzerobottle.Adapter.ExpanListAdapter;
import com.funnytoday.ddoniddoni.seoulzerobottle.ExpanPosition;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;

import java.util.ArrayList;


/**
 * Created by Owner on 2017-08-28.
 */

public class OneFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView one_text_main;
    private TextView one_text_explain;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public OneFragment() {
    }

    public static OneFragment newInstance(String param1, String param2) {
        OneFragment fragment = new OneFragment();
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
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ExpandableListView elv = (ExpandableListView) view.findViewById(R.id.one_listview);

        final ArrayList<ExpanPosition> position = getData();

        ExpanListAdapter adapter = new ExpanListAdapter(getActivity(), position);
        elv.setAdapter(adapter);


        return view;
    }

    private ArrayList<ExpanPosition> getData() {
        ExpanPosition p1 = new ExpanPosition("1. 인상 전 빈병을 모아서 인상 후에 반환하면 인상된 보증금을 받을 수 있나요?");
        p1.items.add("아닙니다. 보증금 인상 전 출고된 제품의 병은 인상 후에 반환해도 이전 보증금으로 환불됩니다.");

        ExpanPosition p2 = new ExpanPosition("2. 인상 전과 인상 후의 빈병을 어떻게 구별하죠?");
        p2.items.add("제품에 부착된 라벨과 재사용 표시 등을 통해 구분 할 수 있습니다.");

        ExpanPosition p3 = new ExpanPosition("3. 보증금이 오르면 주류 가격도 오르나요?");
        p3.items.add("보증금은 전액 환불 받을 수 있어 실질적인 가격 인상으로 볼 수 없습니다. 오히려 재사용 증가로 주류가격 인하요인이 됩니다.");


        ExpanPosition p4 = new ExpanPosition("4. 그래도 보증금 인상폭이 너무 큰 것은 아닌가요?");
        p4.items.add("20년 동안 외식, 과자, 교통 요금은 3~5.5배로 인상되었습니다. 이번 빈용기 보증금은 물가를 반영해 약 2.5배 인상됐습니다.");


        ExpanPosition p5 = new ExpanPosition("5. 빈용기 보증금을 올리면 소비자 반환율도 올라갈까요?");
        p5.items.add("소비자 설문조사에서 빈용기 보증금을 인상할 경우 87.7%가 반환하겠다고 응답했습니다.");


        ExpanPosition p6 = new ExpanPosition("6. 빈용기 보증금은 어디에서 어떻게 반환받을 수 있나요?");
        p6.items.add("슈퍼나 대형마트 등 빈병보증금 제품을 취급하고 있는 소매점이라면 구매여부나 반환 시간에 상관없이 빈용기 보증금을 반환받을 수 있습니다.");


        ExpanPosition p7 = new ExpanPosition("7. 소비자의 빈병 반환 편의를 위해 어떤 것들이 달라지나요?");
        p7.items.add("제품 판매대와 영수증에 보증금이 별도로 표시됩니다.\n소매점 반환거부 등 주요 민원에 대한 신고보상제를 도입합니다");

        ExpanPosition p8 = new ExpanPosition("8. 소매점이 보증금 반환을 거부할 경우 어떤 처벌을 받게 되나요?");
        p8.items.add("반환을 거부하는 소매점은 300만원 이하의 과태료 처분 대상이 됩니다.");


        ExpanPosition p9 = new ExpanPosition("9. 보증금 반환을 거부할 경우 어떻게 해야 하나요?");
        p9.items.add("빈용기 상담센터(1522-0082) 또는 관할 지자체로 신고 가능하며, 신고자에게 연간 최대 10건, 5만원 이하의 보상금이 지급됩니다. ");
        p9.items.add("*보상금 목적의 사전공모·허위 신고 등은 보상금 지급제한");


        ExpanPosition p10 = new ExpanPosition("10. 다량의 빈병을 한번에 반환해도 되나요?");
        p10.items.add("소매점의 공간부족·안전사고·분실문제 등으로 동일인이 하루 30병 이상을 반환할 경우 초과분에 대해 거부할 수 있으니, 신속하게 반환하셔야 합니다.");
        p10.items.add("*해당 소매점에서 구매한 경우 수량과 관계없이 반환가능");


        ExpanPosition p11 = new ExpanPosition("11. 깨지거나 이물질 등이 묻은 빈병도 반환할 수 있나요?");
        p11.items.add("깨지거나 담뱃재, 참기름 등 이물질로 오염되어 재사용이 불가능할 경우 보증금을 반환받을 수 없으니 제품을 드신 이후 그대로 반환하셔야 합니다.");


        ExpanPosition p12 = new ExpanPosition("12. 빈용기보증금액은 어떻게 확인하나요?");
        p12.items.add("2016년 7월 1일부터 생산된 소주병·맥주병 등은 병의 앞‧뒷면 또는 측면 재사용 표시를 통해 금액을 확인할 수 있습니다.");

        ExpanPosition p13 = new ExpanPosition("13. 소매점에서 보증금 반환 대신 물건으로 교환해 주는 것이 가능한가요?");
        p13.items.add("원칙적으로 보증금 전액을 반환해주어야 하며, 소비자의 동의 없이 물건으로 교환할 것을 강요할 경우 과태료 처분 대상이 될 수 있습니다.");

        ExpanPosition p14 = new ExpanPosition("14. 소매점에서 빈용기 반환시간을 특정할 수 있나요?");
        p14.items.add("영업시간내 언제라도 빈병을 반환 받아야 하며, 시간이나 요일을 특정하여 반환을 거부할 경우 과태료 처분 대상이 될 수 있습니다.");


        ExpanPosition p15 = new ExpanPosition("15. 대형마트에서 구매한 빈병을 일반 소매점에 반환해도 되나요?");
        p15.items.add("구매처에 관계없이 같은 종류의 보증금 제품을 판매하는 곳이라면 어느 곳에나 반환할 수 있습니다.");

        ArrayList<ExpanPosition> allposition = new ArrayList<>();
        allposition.add(p1);
        allposition.add(p2);
        allposition.add(p3);
        allposition.add(p4);
        allposition.add(p5);
        allposition.add(p6);
        allposition.add(p7);
        allposition.add(p8);
        allposition.add(p9);
        allposition.add(p10);
        allposition.add(p11);
        allposition.add(p12);
        allposition.add(p13);
        allposition.add(p14);
        allposition.add(p15);

        return allposition;
    }

}