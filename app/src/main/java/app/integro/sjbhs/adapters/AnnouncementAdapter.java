package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Announcement;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder> {

    private static final String TAG = "AnnouncementAdapter";
    private final ArrayList<Announcement> announcementArrayList;
    private final Context context;

    public AnnouncementAdapter(Context context, ArrayList<Announcement> announcementArrayList) {
        this.context = context;
        this.announcementArrayList = announcementArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_upcoming_events, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Announcement announcement = announcementArrayList.get(position);
        holder.tvTitle.setText(announcement.getTitle());
        holder.tvDate.setText(announcement.getDate());
        holder.tvDescription.setText(announcement.getDescription());

        int itemId = Integer.parseInt(announcement.getId());
        float colorValue = Integer.parseInt(String.valueOf(itemId / 2));
        Log.d(TAG, "onBindViewHolder: colorValue " + colorValue);
        Log.d(TAG, "onBindViewHolder: itemId: " + announcement.getId());
    }

    @Override
    public int getItemCount() {
        return announcementArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvDate;
        private final CardView cvAnnouncements;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            cvAnnouncements = itemView.findViewById(R.id.cvAnnouncements);
        }
    }
}
