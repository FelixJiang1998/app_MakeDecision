package edu.cczu.makedecision;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import edu.cczu.makedecision.fragment.DecideFragment;
import edu.cczu.makedecision.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity
        implements DecideFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener {

    protected HomeFragment mFragmentHome = new HomeFragment();
    protected DecideFragment mFragmentDecide = new DecideFragment();

    private static ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;
    //摇一摇
    private ShakeManager shakeManager;

    public static void set_tab(int tab) {
        mViewPager.setCurrentItem(tab);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState==null)
            initView();
    }

    private void initView() {
        // find view
        mViewPager = findViewById(R.id.fragment_vp);
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        // init fragment
        mFragments = new ArrayList<>(2);
        mFragments.add(new HomeFragment());
        mFragments.add(new DecideFragment());
        // init view pager
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        // register listener
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //用于ViewPager
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        set_tab(i);
                        return;
                    }
                }
            }
        });

        shakeManager=new ShakeManager(this);
        shakeManager.setShakeListener(new ShakeManager.ShakeListener() {
            @Override
            public void onShake() {
                mFragmentDecide.MakeDecision();
            }
        });
        shakeManager.start();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mList == null ? null : this.mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null ? 0 : this.mList.size();
        }
    }

}
