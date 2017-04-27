package com.example.bestfragmentpractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestfragmentpractice.R;

/**
 * 新闻内容部分碎片
 * Created by 安维 on 2017/4/27.
 */

public class NewsContentFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_fragment,container,false);
        return view;
    }
    public void refresh(String newTitle,String newConten){
        View visibilityLayout= view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView textView=(TextView)view.findViewById(R.id.news_title);
        textView.setText(newTitle);
        TextView contenView=(TextView)view.findViewById(R.id.news_content);
        contenView.setText(newConten);
    }
}
