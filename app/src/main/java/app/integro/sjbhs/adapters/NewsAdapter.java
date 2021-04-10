package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.NewsDetailActivity;
import app.integro.sjbhs.R;
import app.integro.sjbhs.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<News> newsArrayList;

    private static final String TAG = "NewsAdapter";

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News newsItem = newsArrayList.get(position);
        holder.tvN_Title.setText(newsItem.getTitle());
        holder.tvN_date.setText(newsItem.getDate());
        holder.tvDescription.setText(newsItem.getDescription());

        Log.d(TAG, "onBindViewHolder: " + newsArrayList.get(1).getImage());

        String imageUrl = newsArrayList.get(position).getImage();
        if (imageUrl != null) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivNews);
        } else {
            holder.ivNews.setVisibility(View.GONE);
        }
        holder.llNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("data", newsItem);
                intent.putExtra("itemId", newsItem.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivNews;
        TextView tvN_Title;
        TextView tvN_date;
        TextView tvDescription;
        LinearLayout llNews;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivNews = (ImageView) itemView.findViewById(R.id.ivImage);
            tvN_date = (TextView) itemView.findViewById(R.id.tvDate);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvN_Title = (TextView) itemView.findViewById(R.id.tvTitle);
            llNews = (LinearLayout) itemView.findViewById(R.id.rlNews);
        }
    }
}
