package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.NewsLetter;

public class NewsLaterAdapter extends RecyclerView.Adapter<NewsLaterAdapter.MyViewHolder> {
    private final ArrayList<NewsLetter> newsLetterArrayList;
    private final Context context;

    public NewsLaterAdapter(Context context, ArrayList<NewsLetter> newsLetterArrayList) {
        this.context = context;
        this.newsLetterArrayList = newsLetterArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_newsletter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final NewsLetter newsLetter = newsLetterArrayList.get(position);

        holder.tvTitle.setText(newsLetter.getTitle());
        holder.tvDownload.setText("VIEW / DOWNLOAD");
        holder.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(newsLetter.getPdf()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load(newsLetter.getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return newsLetterArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDownload;
        private final ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDownload = itemView.findViewById(R.id.tvDownload);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
