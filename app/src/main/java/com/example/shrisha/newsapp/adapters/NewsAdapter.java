package com.example.shrisha.newsapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shrisha.newsapp.model.NewsArticle;
import com.example.shrisha.newsapp.R;
import com.example.shrisha.newsapp.model.NewsLoader;
import com.example.shrisha.newsapp.ui.NewsListActivity;
import com.example.shrisha.newsapp.utils.ImageCacheManager;

import java.util.List;

/**
 * Created by shrisha on 8/10/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>{

    private TextView titleView;
    private ImageView imageView;
    private List<NewsArticle> newsArticleList;
    private onNewsItemClickListener listener;
    private Context context;

    public NewsAdapter(Context context, List<NewsArticle> newsArticleList, onNewsItemClickListener listener) {
        this.context = context;
        this.newsArticleList = newsArticleList;
        this.listener = listener;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {

        holder.bind(newsArticleList.get(position),listener);

    }

    @Override
    public int getItemCount() {

        return newsArticleList.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {

        public NewsItemViewHolder(View itemView) {

            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        public void bind(final NewsArticle NewsArticle, final onNewsItemClickListener listener) {

            titleView.setText(NewsArticle.getNewsTitle());

            Bitmap bitmap = ImageCacheManager.getBitmap(context, NewsArticle);
            /*if (bitmap == null) {

                new NewsLoader(context, NewsListActivity.NEWS_REQUEST_URL);

            } else {*/
                imageView.setImageBitmap(NewsArticle.getImageBitmap());
//            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNewsItemClick(NewsArticle);
                }
            });
        }
    }

    public interface onNewsItemClickListener {

        void onNewsItemClick(NewsArticle newsArticleItem);
    }
}
