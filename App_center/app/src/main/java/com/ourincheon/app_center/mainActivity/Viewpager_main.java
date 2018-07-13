package com.ourincheon.app_center.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import com.ourincheon.app_center.Calender;
import com.ourincheon.app_center.R;

/**
 * Created by 성민 on 2018-03-30.
 */

public class Viewpager_main extends FragmentActivity {

    ViewPager viewPager;
    private MenuItem currentPosition;

    long pressedtime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);

        viewPager = (ViewPager) findViewById(R.id.viewPagerScreen);
        viewPager.setAdapter(new pageAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(currentPosition != null){
                    currentPosition.setChecked(false);
                }
                currentPosition = bottomNavigationView.getMenu().getItem(position);
                currentPosition.setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home :
                        viewPager.setCurrentItem(0);
                        return true;

                    case R.id.event:
                        viewPager.setCurrentItem(1);
                        return true;

                    case R.id.setting:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

    }

    private class pageAdapter extends FragmentPagerAdapter{
        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MainActivity();
                case 1:
                    return new Calender();
                case 2:
                    return new Setting();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() >= pressedtime + 2000) {
            pressedtime = System.currentTimeMillis();
            Toast.makeText(this, " 한번 더 누르면 어플이 종료됩니다", Toast.LENGTH_SHORT).show();
            return;
        } else {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
        }
    }
}
