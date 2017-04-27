package com.example.bestfragmentpractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bestfragmentpractice.NewsContentActivity;
import com.example.bestfragmentpractice.R;
import com.example.bestfragmentpractice.entity.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 新闻标题部分碎片
 * Created by 安维 on 2017/4/27.
 */

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_fragment,container,false);
        List<News> newsList=getNews();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.news_title_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsAdapter newsAdapter=new NewsAdapter(newsList);
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPage=true;
        }else {
            isTwoPage=false;
        }
    }
    private List<News> getNews(){
        List<News> newsList =new ArrayList<>();
        for(int i=1;i<50;i++){
            News news=new News();
            news.setTitle("this is new title"+i);
            news.setContent(getRandomLengthContent("this is new content"+i));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random =new Random();
        int lenth=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<lenth;i++){
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }
    /**
     * 适配器类
     */
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> newsList;
        public  NewsAdapter(List<News> newsList){
            this.newsList=newsList;
        }
        class ViewHolder extends RecyclerView.ViewHolder{
            public TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                textView=(TextView) itemView.findViewById(R.id.news_title);
            }
        }
    @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_title_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        TextView textView=(TextView)view.findViewById(R.id.news_title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news=newsList.get(viewHolder.getAdapterPosition());
                if(isTwoPage){
                    NewsContentFragment newsContentFragment =(NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragment.refresh(news.getTitle(),news.getContent());
                }
                else {
                    NewsContentActivity.startActivity(getActivity(),news.getTitle(),news.getContent());
                }
            }
        });
        return viewHolder;
    }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news=newsList.get(position);
            holder.textView.setText(news.getTitle());
        }
        @Override
        public int getItemCount(){
            return newsList.size();
        }
    }
}
