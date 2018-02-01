package com.funnytoday.ddoniddoni.seoulzerobottle.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.funnytoday.ddoniddoni.seoulzerobottle.CircleTransForm;
import com.funnytoday.ddoniddoni.seoulzerobottle.Fragment.FiveFragment;
import com.funnytoday.ddoniddoni.seoulzerobottle.Fragment.OneFragment;
import com.funnytoday.ddoniddoni.seoulzerobottle.Fragment.SixFragment;
import com.funnytoday.ddoniddoni.seoulzerobottle.Fragment.ThreeFragment;
import com.funnytoday.ddoniddoni.seoulzerobottle.Fragment.TwoFragment;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "SignInActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;

    private String mUsername;
    private String mUserEmail;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private Toolbar toolbar;

    public static int navItemIndex = 0;

    private static final String TAG_ONE = "one";
    private static final String TAG_TWO = "two";
    private static final String TAG_THREE = "three";
    private static final String TAG_FIVE = "five";
    private static final String TAG_SIX = "six";
    public static String CURRENT_TAG = TAG_ONE;

    private String[] activityTitles;

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        /*remoteConfig();*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_ONE;
            loadHomeFragment();
        }


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
    }


    private void loadNavHeader() {

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            Toast.makeText(this, "로그인이 필요합니다", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            mUserEmail = mFirebaseUser.getEmail();
            if (mFirebaseUser.getPhotoUrl() != null) {
                String photoUrl = mFirebaseUser.getPhotoUrl().toString();

                ImageView photoImageView = (ImageView) navHeader.findViewById(R.id.img_profile);
                Glide.with(this).load(photoUrl).bitmapTransform(new CircleTransForm(this)).into(photoImageView);
            }

            TextView usernameTextView = (TextView) navHeader.findViewById(R.id.name);
            usernameTextView.setText(mUsername);
            TextView emailTextView = (TextView) navHeader.findViewById(R.id.website);
            emailTextView.setText(mUserEmail);

            Toast.makeText(this, mUsername + "님 환영합니다.", Toast.LENGTH_SHORT).show();

        }

    }

    private void loadHomeFragment() {
        // 메뉴 적용 선택 적용
        selectNavMenu();

        // 툴바 타이틀 적용
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        drawer.closeDrawers();

        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                OneFragment oneFragment = new OneFragment();
                return oneFragment;
            case 1:
                TwoFragment twoFragment = new TwoFragment();
                return twoFragment;
            case 2:
                ThreeFragment threeFragment = new ThreeFragment();
                return threeFragment;
            case 3:
                FiveFragment fiveFragment = new FiveFragment();
                return fiveFragment;
            case 4:
                SixFragment sixFragment = new SixFragment();
                return sixFragment;

            default:
                return new OneFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_top:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_ONE;
                        break;
                    case R.id.nav_bottom:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_TWO;
                        break;
                    case R.id.nav_stuff:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_THREE;
                        break;
                    case R.id.nav_closet:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_FIVE;
                        break;
                    case R.id.nav_cum:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SIX;

                        break;

                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);


                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_ONE;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        if (navItemIndex == 1) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        if (navItemIndex == 2) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        if (navItemIndex == 4) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        if (navItemIndex == 5) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("로그아웃 하시겠습니까?")
                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mFirebaseAuth.signOut();
                            Auth.GoogleSignInApi.signOut(mGoogleApiClient);

                            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).show();


            return true;
        }

        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
