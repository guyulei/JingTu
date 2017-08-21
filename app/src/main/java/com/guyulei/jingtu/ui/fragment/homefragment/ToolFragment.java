package com.guyulei.jingtu.ui.fragment.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.guyulei.jingtu.R;
import com.guyulei.jingtu.ui.fragment.toolfragment.ToolPageFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A on 2017/8/21.
 */

public class ToolFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager        mViewpager;
    ArrayList<Fragment>        fragments  = new ArrayList<>();
    private String tabTitles[] = new String[]{"1", "2", "3", "4"};

    public static ToolFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ToolFragment toolFragment = new ToolFragment();
        toolFragment.setArguments(args);
        return toolFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_tool, null);
        ButterKnife.bind(this, inflate);
        initview();
        return inflate;

    }

    private void initview() {
        for (int i = 0; i < tabTitles.length; i++) {
            fragments.add(ToolPageFragment.newInstance(i));
        }
        ToolPageAdapter toolPageAdapter = new ToolPageAdapter(getChildFragmentManager());
        mViewpager.setAdapter(toolPageAdapter);
        mViewpager.setOffscreenPageLimit(4);
        mSlidingTabLayout.setViewPager(mViewpager);
    }

    public class ToolPageAdapter extends FragmentPagerAdapter {
        public ToolPageAdapter(FragmentManager fm) {
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
