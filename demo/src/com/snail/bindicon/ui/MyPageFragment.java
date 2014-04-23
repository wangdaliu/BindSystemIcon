package com.snail.bindicon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.snail.bindicon.R;

public class MyPageFragment extends Fragment {


    private static final String ARG_PAGE = "page_num";

    private int currentPageNum;

    public static MyPageFragment create(int pagerNum) {

        MyPageFragment mFragment = new MyPageFragment();
        Bundle arg = new Bundle();
        arg.putInt(ARG_PAGE, pagerNum);
        mFragment.setArguments(arg);
        return mFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPageNum = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_layout,
                container, false);
        TextView tip = (TextView) rootView.findViewById(R.id.tip);
        switch (currentPageNum) {
            case 0:
                rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                tip.setText(R.string.contact);
                break;
            case 1:
                rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                tip.setText(R.string.call);
                break;
            case 2:
                rootView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                tip.setText(R.string.sms);

                break;
            default:
                break;
        }

        return rootView;

    }

}
