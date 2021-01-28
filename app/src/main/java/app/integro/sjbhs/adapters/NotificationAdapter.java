package app.integro.sjbhs.adapters;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.WebActivity;
import app.integro.sjbhs.models.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Notification> notificationArrayList;

    public NotificationAdapter(Context context, ArrayList<Notification> notificationsArrayList) {
        this.context = context;
        this.notificationArrayList = notificationsArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_notification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Notification notificationItem = notificationArrayList.get(position);

        holder.tvN_Title.setText(notificationItem.getTitle());
        holder.tvN_date.setText(notificationItem.getDate());
        holder.tvN_Description.setText(notificationItem.getDescription());
        holder.tvWeblink.setText(notificationItem.getWeblink());

        holder.tvWeblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("URL", notificationItem.getWeblink());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvN_Title;
        private final TextView tvN_date;
        private final TextView tvN_Description;
        private final TextView tvWeblink;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvN_date = (TextView) itemView.findViewById(R.id.tvDate);
            tvN_Title = (TextView) itemView.findViewById(R.id.tvTitle);
            tvN_Description = (TextView) itemView.findViewById(R.id.tvDescription);
            tvWeblink = (TextView) itemView.findViewById(R.id.tvWeblink);
        }
    }
}
