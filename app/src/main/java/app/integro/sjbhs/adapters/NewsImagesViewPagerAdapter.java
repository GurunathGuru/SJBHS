package app.integro.sjbhs.adapters;

import android.content.Context;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.NewsImages;

public class NewsImagesViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final ArrayList<NewsImages> newsImagesArrayList;

    public NewsImagesViewPagerAdapter(Context context, ArrayList<NewsImages> newsImagesArrayList) {
        this.context = context;
        this.newsImagesArrayList = newsImagesArrayList;
    }

    @Override
    public int getCount() {
        return newsImagesArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivNews;
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_newsimages, container, false);
        ivNews = (ImageView) itemView.findViewById(R.id.ivImage);
        final NewsImages newsItem = newsImagesArrayList.get(position);
        Glide.with(context)
                .load(newsImagesArrayList.get(position).getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivNews);

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
