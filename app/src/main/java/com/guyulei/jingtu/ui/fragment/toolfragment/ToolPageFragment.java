package com.guyulei.jingtu.ui.fragment.toolfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guyulei.jingtu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A on 2017/8/21.
 */

public class ToolPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    @BindView(R.id.text)
    TextView mText;

    public static ToolPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ToolPageFragment toolFragment = new ToolPageFragment();
        toolFragment.setArguments(args);
        return toolFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_tool_itempage, null);
        ButterKnife.bind(this,inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        int anInt = getArguments().getInt(ARG_PAGE);
        mText.setText("ToolPageFragment "+anInt);
    }
}
