package com.funnytoday.ddoniddoni.seoulzerobottle.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.funnytoday.ddoniddoni.seoulzerobottle.Data.ItemData;
import com.funnytoday.ddoniddoni.seoulzerobottle.Data.PointData;
import com.funnytoday.ddoniddoni.seoulzerobottle.Data.PointSumData;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Owner on 2017-08-28.
 */

public class SixFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RelativeLayout progress_layout;
    private ItemAdapter itemAdapter;
    private ArrayList<ItemData> list = new ArrayList<>();

    private String strResult = "";

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;


    public SixFragment() {
    }

    public static SixFragment newInstance(String param1, String param2) {
        SixFragment fragment = new SixFragment();
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
        View view = inflater.inflate(R.layout.fragment_six, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.item_recyclerView);
        progress_layout = (RelativeLayout) view.findViewById(R.id.progress_layout);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        strResult += Integer.toString(year) + "/";
        strResult += Integer.toString(month + 1) + "/";
        strResult += Integer.toString(day);

        list = ItemData.createItemList(2);
        itemAdapter = new ItemAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(itemAdapter);
        return view;
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {

        private Context context;
        private List<ItemData> list = new ArrayList<>();


        public ItemAdapter(Context context, List<ItemData> list) {
            this.context = context;
            this.list = list;
        }


        @Override
        public ItemAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_child, parent, false);
            Holder holder = new Holder(view);

            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();
            database = FirebaseDatabase.getInstance();

            return holder;
        }

        @Override
        public void onBindViewHolder(final ItemAdapter.Holder holder, final int position) {
            final int itemposition = position;
            Glide.with(holder.itemView.getContext()).load(list.get(itemposition).item_image).into(((Holder) holder).item_image);
            holder.item_text.setText(list.get(itemposition).item_text);
            holder.item_exp.setText(list.get(itemposition).item_exp);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView item_image;
            public TextView item_text;
            public TextView item_exp;

            public Holder(View view) {
                super(view);
                item_image = (ImageView) view.findViewById(R.id.item_image);
                item_text = (TextView) view.findViewById(R.id.item_text);
                item_exp = (TextView) view.findViewById(R.id.item_exp);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (v == itemView) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        switch (getAdapterPosition()) {
                            case 0:
                                pointCalculate(1000);
                                break;
                            case 1:
                                pointCalculate(6500);
                                break;
                            case 2:
                                pointCalculate(2000);
                                break;
                            case 3:
                                pointCalculate(10000);
                                break;
                            case 4:
                                pointCalculate(4000);
                                break;
                            case 5:
                                pointCalculate(2000);
                                break;
                            case 6:
                                pointCalculate(14900);
                                break;
                            case 7:
                                pointCalculate(12900);
                                break;
                        }
                    }
                }
            }
        }
    }

    private void pointCalculate(final int pointSub) {
        progress_layout.setVisibility(View.VISIBLE);
        final DatabaseReference pointSumDataReference = database.getReference().child("pointSum").child(user.getEmail().replace("@", "").replace(".", ""));

        pointSumDataReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PointSumData pointSumData = dataSnapshot.getValue(PointSumData.class);
                final HashMap<String, Object> result = new HashMap<>();
                String point = "";
                final int[] pointSum = new int[1];
                if (pointSumData != null) {
                    point = pointSumData.getPoint() == null ? "" : pointSumData.getPoint();
                }
                if (point != null && point.length() > 0 && Integer.parseInt(point) >= pointSub) {
                    AlertDialog.Builder alt_bld1 = new AlertDialog.Builder(getContext());
                    final String finalPoint = point;
                    alt_bld1.setMessage("포인트를 사용하여 쿠폰을 구매하시겠습니까?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                 /*포인트 합산 결과*/
                                pointSum[0] = Integer.parseInt(finalPoint) - pointSub;
                                result.put("point", String.valueOf(pointSum[0]));
                                pointSumDataReference.updateChildren(result);

                                /*구매내역 데이터 추가*/
                                PointData pointData = new PointData();
                                pointData.pointReuslt = "-";
                                pointData.userId = user.getEmail();
                                pointData.pointHistory = "포인트 쿠폰구매";
                                pointData.pointTime = strResult;
                                pointData.point = String.valueOf(pointSub);
                                database.getReference().child("point").push().setValue(pointData);
                                Toast.makeText(getContext(), "구매에 성공하였습니다..", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert1 = alt_bld1.create();
                    alert1.setTitle("쿠폰구매");
                    alert1.setIcon(R.drawable.common_google_signin_btn_icon_dark);
                    alert1.show();


                } else {
                    Toast.makeText(getContext(), "포인트가 부족합니다.", Toast.LENGTH_SHORT).show();
                }
                progress_layout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progress_layout.setVisibility(View.GONE);
                Toast.makeText(getContext(), "데이터 통신을 확인해주세요.", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
