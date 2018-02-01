package com.funnytoday.ddoniddoni.seoulzerobottle.Fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnytoday.ddoniddoni.seoulzerobottle.Data.PointData;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2017-08-28.
 */

public class FiveFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseUser user;


    private List<PointData> pointDatas = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PointRecyclerViewAdapter pointRecyclerViewAdapter;

    private TextView point_user_name;
    private TextView point_text;
    private TextView point_user_email;

    private SwipeRefreshLayout swipeRefreshLayout;

    public FiveFragment() {
    }

    public static FiveFragment newInstance(String param1, String param2) {
        FiveFragment fragment = new FiveFragment();
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
        View view = inflater.inflate(R.layout.fragment_five_new, container, false);

        point_user_name = (TextView) view.findViewById(R.id.point_user_name);
        point_text = (TextView) view.findViewById(R.id.point_text);
        point_user_email = (TextView) view.findViewById(R.id.point_user_email);

        database = FirebaseDatabase.getInstance();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(0);
        pointRecyclerViewAdapter = new PointRecyclerViewAdapter();
        recyclerView.setAdapter(pointRecyclerViewAdapter);

        point_user_name.setText(user.getDisplayName());
        point_user_email.setText(user.getEmail());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiper_layout);
        swipeRefreshLayout.setOnRefreshListener(this);


        database.getReference().child("point").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pointDatas.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    PointData pointData = snapshot.getValue(PointData.class);
                    if (user.getEmail().equals(pointData.userId)) {
                        pointDatas.add(pointData);
                    }
                }
                pointRecyclerViewAdapter.notifyDataSetChanged();
                PointSum();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return view;
    }

    @Override
    public void onRefresh() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pointRecyclerViewAdapter.notifyDataSetChanged();
                Snackbar.make(recyclerView, "포인트 정보를 불러왔습니다", Snackbar.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 500);
    }


    class PointRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.point_list, parent, false);

            return new PointViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            try {
                ((PointViewHolder) holder).point_list_point.setText(pointDatas.get(position).pointReuslt + pointDatas.get(position).point);
                ((PointViewHolder) holder).point_list_reason.setText(pointDatas.get(position).pointHistory);
                ((PointViewHolder) holder).point_list_day.setText(pointDatas.get(position).pointTime);


            } catch (Exception e) {

            }
        }

        @Override
        public int getItemCount() {
            return pointDatas.size();
        }

        private class PointViewHolder extends RecyclerView.ViewHolder {
            TextView point_list_day;
            TextView point_list_reason;
            TextView point_list_point;

            public PointViewHolder(View view) {
                super(view);
                point_list_day = (TextView) view.findViewById(R.id.point_list_day);
                point_list_reason = (TextView) view.findViewById(R.id.point_list_reason);
                point_list_point = (TextView) view.findViewById(R.id.point_list_point);
            }
        }
    }

    public void PointSum() {
        int i;
        int sum = 0;
        int sum1 = 0;
        int sum2;
        for (i = 0; i < pointDatas.size(); i++) {
            if (pointDatas.get(i).pointReuslt.equals("+")) {
                sum += Integer.parseInt(pointDatas.get(i).point);
            } else if (pointDatas.get(i).pointReuslt.equals("-")) {
                sum1 -= Integer.parseInt(pointDatas.get(i).point);
            }
        }
        sum2 = (sum + sum1);
        point_text.setText("내 포인트 " + String.valueOf(sum2) + " Point");
        pointRecyclerViewAdapter.notifyDataSetChanged();
    }


}
