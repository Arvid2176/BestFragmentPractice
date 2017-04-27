package com.example.bestfragmentpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bestfragmentpractice.fragment.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        Intent intent=getIntent();
        String newTitle=intent.getStringExtra("newTitle");
        String newContent=intent.getStringExtra("newContent");
        NewsContentFragment newsContentFragment=(NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newTitle,newContent);
    }
    public static void startActivity(Context context,String newTitle,String newContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("newTitle",newTitle);
        intent.putExtra("newContent",newContent);
        context.startActivity(intent);
    }
}
