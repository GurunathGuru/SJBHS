package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.NewsActivity;
import app.integro.sjbhs.NewsDetailActivity;
import app.integro.sjbhs.R;
import app.integro.sjbhs.models.News;

public class NewsViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final ArrayList<News> newsArrayList;

    public NewsViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivNews;
        TextView tvNews;
        RelativeLayout rlNews;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.card_newsviewpager, container, false);

        ivNews =  itemView.findViewById(R.id.ivNews);
        rlNews =  itemView.findViewById(R.id.rlNews);
        tvNews =  itemView.findViewById(R.id.tvTitle);
        final News newsItem = newsArrayList.get(position);

        tvNews.setText(newsItem.getTitle());

        Glide.with(context)
                .load(newsItem.getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivNews);
        rlNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
