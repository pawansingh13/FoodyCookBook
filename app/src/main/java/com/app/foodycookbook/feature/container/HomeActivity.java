package com.app.foodycookbook.feature.container;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.feature.container.adapter.DrawerListAdapter;
import com.app.foodycookbook.feature.container.model.DrawerItem;
import com.app.foodycookbook.feature.favorite.FavoriteFragment;
import com.app.foodycookbook.feature.meal.MealFragment;
import com.app.foodycookbook.feature.search.SearchActivity;
import com.app.foodycookbook.listeners.OnSetHeaderTitle;
import com.app.foodycookbook.permissions.AbstractPermissionActivity;
import com.app.foodycookbook.utills.Const;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AbstractPermissionActivity implements AdapterView.OnItemClickListener {
    private boolean doubleBackToExitPressedOnce = false;
    int[] mNavMenuTitlesImage = {R.drawable.ic_meal, R.drawable.ic_un_favorite_24};
    ArrayList<DrawerItem> mDrawerArrayList;
    RemoveDb RemoveDbListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn(this);
        initializeView();
    }

    private void initializeView() {
        setUpToolbar(false, true, false, true);
        mDlMenuItem = findViewById(R.id.drawer_layout);
        ImageView mIvSearch = findViewById(R.id.iv_search);
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivity(SearchActivity.class);
            }
        });
        ListView mLvDrawerList = findViewById(R.id.lv_list_slider_menu);
        mLvDrawerList.setOnItemClickListener(this);
        String[] mNavMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerArrayList = new ArrayList<>();
        for (int mCount = 0; mCount < mNavMenuTitles.length; mCount++) {
            DrawerItem mDrawerItem = new DrawerItem();
            mDrawerItem.setDrawerItem(mNavMenuTitles[mCount]);
            mDrawerItem.setDrawerItemImage(mNavMenuTitlesImage[mCount]);
            mDrawerArrayList.add(mDrawerItem);
        }
        DrawerListAdapter mDrawerListAdapter = new DrawerListAdapter(this, mDrawerArrayList);
        mLvDrawerList.setAdapter(mDrawerListAdapter);
        loadFragment(MealFragment.newInstance());
    }


    public void isRemoveFromDb(boolean isRemove) {
        RemoveDbListener.isRemoveDb(isRemove);
    }

    public void setRegisterRemoveDb(RemoveDb listener) {
        RemoveDbListener = listener;
    }

    public interface RemoveDb {
        void isRemoveDb(boolean isRemove);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() instanceof MealFragment) {
            if (doubleBackToExitPressedOnce) {
                if (mDlMenuItem.isDrawerOpen(GravityCompat.START)) {
                    mDlMenuItem.closeDrawer(GravityCompat.START);
                } else {
                    finishAffinity();
                }
            } else {
                this.doubleBackToExitPressedOnce = true;
                customSnack(findViewById(R.id.drawer_layout), getString(R.string.please_again_click_if_you_want_to_exit));
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, Const.EXIT_TIME);
            }
        } else {
            super.onBackPressed();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment mFragment = null;
        switch (position) {
            case 0:
                mFragment = MealFragment.newInstance();
                loadFragment(mFragment);
                break;
            case 1:
                mFragment = FavoriteFragment.newInstance();
                loadFragment(mFragment);
                break;
        }
        closeDrawer();

    }


    @Override
    public View getLayoutRootView() {
        return findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }
}
