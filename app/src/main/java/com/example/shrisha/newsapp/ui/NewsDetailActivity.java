package com.example.shrisha.newsapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shrisha.newsapp.R;
import com.example.shrisha.newsapp.model.NewsArticle;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        TextView title = (TextView) findViewById(R.id.detail_title);
        TextView description = (TextView) findViewById(R.id.detail_description);
//        ImageView image = (ImageView) findViewById(R.id.detail_image);

        title.setText(getIntent().getStringExtra("NewsTitle"));
        description.setText(getIntent().getStringExtra("NewsDescription"));

        /*byte[] bytes = getIntent().getByteArrayExtra("NewsImage");
        final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        image.setImageBitmap(bmp);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri newsUri = Uri.parse(getIntent().getStringExtra("NewsUrl"));
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(browserIntent);
            }
        });
    }

}
