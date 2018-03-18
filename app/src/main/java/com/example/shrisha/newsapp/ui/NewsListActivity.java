package com.example.shrisha.newsapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shrisha.newsapp.BuildConfig;
import com.example.shrisha.newsapp.R;
import com.example.shrisha.newsapp.adapters.NewsAdapter;
import com.example.shrisha.newsapp.model.NewsArticle;
import com.example.shrisha.newsapp.model.NewsLoader;
import com.example.shrisha.newsapp.utils.ImageCacheManager;
import com.example.shrisha.newsapp.utils.NetworkHelper;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class NewsListActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<NewsArticle>>,NewsAdapter.onNewsItemClickListener {

    private RecyclerView newsRecyclerView;
    private TextView mEmptyTextView;
    private NewsAdapter mNewsAdapter;

    private static final String API_KEY = BuildConfig.API_KEY;

    public static final String NEWS_REQUEST_URL =
            "https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=" + API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        boolean networkConnected = NetworkHelper.hasNetwork(NewsListActivity.this);

        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        newsRecyclerView.setLayoutManager(mLayoutManager);
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (networkConnected) {
            getSupportLoaderManager().initLoader(1, null, NewsListActivity.this)
                    .forceLoad();

        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
            mEmptyTextView.setText(R.string.no_network);
        }
    }


    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> data) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {
            mNewsAdapter = new NewsAdapter(NewsListActivity.this, data, this);
            newsRecyclerView.setAdapter(mNewsAdapter);
        } else {
            mEmptyTextView.setVisibility(View.VISIBLE);
            mEmptyTextView.setText(R.string.no_news);
            newsRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewsItemClick(NewsArticle newsArticleItem) {

        Intent intent = new Intent(this, NewsDetailActivity.class);

        intent.putExtra("NewsTitle", newsArticleItem.getNewsTitle());
        intent.putExtra("NewsDescription", newsArticleItem.getNewsDescription());
        intent.putExtra("NewsUrl", newsArticleItem.getNewsUrl());

        // Compress the image
        /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
        newsArticleItem.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 10, stream);
        byte[] bytes = stream.toByteArray();

        intent.putExtra("NewsImage", bytes[0]);*/

        startActivity(intent);

    }
}
