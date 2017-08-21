package com.guyulei.jingtu.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.guyulei.jingtu.R;
import com.guyulei.jingtu.bean.TabEntity;
import com.guyulei.jingtu.ui.fragment.homefragment.Tab2Fragment;
import com.guyulei.jingtu.ui.fragment.homefragment.Tab3Fragment;
import com.guyulei.jingtu.ui.fragment.homefragment.Tab4Fragment;
import com.guyulei.jingtu.ui.fragment.homefragment.ToolFragment;
import com.guyulei.jingtu.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar         mToolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout    mDrawerLayout;//侧边 布局android:layout_gravity="left"  需自己打出
    @BindView(R.id.nav_view)
    NavigationView  mNavView;
    @BindView(R.id.viewpager)
    ViewPager       mViewpager;
    @BindView(R.id.tablayout)
    CommonTabLayout mTablayout;

    ArrayList<Fragment>        fragments  = new ArrayList<>();
    ArrayList<CustomTabEntity> tabEntitys = new ArrayList<>();
    private String tabTitles[]      = new String[]{"工具", "tab2", "tab3", "tab4"};
    private int[]  mIconUnselectIds = {R.mipmap.home1, R.mipmap.home3, R.mipmap.home5, R.mipmap.home7};
    private int[]  mIconSelectIds   = {R.mipmap.home2, R.mipmap.home4, R.mipmap.home6, R.mipmap.home8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            //supportActionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher_round);
        }
        mNavView.setNavigationItemSelectedListener(this);
        View headerView = mNavView.getHeaderView(0);
        headerView.findViewById(R.id.nav_header_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("点击了nav_header_logo");
                mDrawerLayout.closeDrawers();
            }
        });
        //
        fragments.add(ToolFragment.newInstance(1));
        fragments.add(Tab2Fragment.newInstance(2));
        fragments.add(Tab3Fragment.newInstance(3));
        fragments.add(Tab4Fragment.newInstance(4));
        for (int i = 0; i < tabTitles.length; i++) {
            tabEntitys.add(new TabEntity(tabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(homePageAdapter);
        mViewpager.setOffscreenPageLimit(4);
        mTablayout.setTabData(tabEntitys);
        //
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewpager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.first:
                ToastUtils.showShort("点击了first");
                break;
            case R.id.two:
                ToastUtils.showShort("点击了two");
                break;
            case R.id.three:
                ToastUtils.showShort("点击了three");
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_first:
                ToastUtils.showShort("点击了nav_first");
                break;
            case R.id.nav_two:
                ToastUtils.showShort("点击了nav_two");
                break;
            case R.id.nav_three:
                ToastUtils.showShort("点击了nav_three");
                break;
            case R.id.nav_four:
                ToastUtils.showShort("点击了nav_four");
                break;
            case R.id.nav_share:
                ToastUtils.showShort("点击了nav_share");
                break;
            case R.id.nav_idea:
                ToastUtils.showShort("点击了nav_idea");
                break;
        }
        mDrawerLayout.closeDrawers();
        return true;
    }


    public class HomePageAdapter extends FragmentPagerAdapter {
        public HomePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
