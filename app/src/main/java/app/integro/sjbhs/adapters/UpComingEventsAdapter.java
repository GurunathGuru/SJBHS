package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.UpComingEvents;

public class UpComingEventsAdapter extends RecyclerView.Adapter<UpComingEventsAdapter.MyViewHolder> {
   private final Context context;
    private final ArrayList<UpComingEvents> upComingEventsArrayList;

    public UpComingEventsAdapter(Context context, ArrayList<UpComingEvents> upComingEventsArrayList) {
        this.context = context;
        this.upComingEventsArrayList = upComingEventsArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_upcoming_events, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final UpComingEvents upComingEventsItems = upComingEventsArrayList.get(position);
        holder.tvTitle.setText(upComingEventsItems.getTitle());
        holder.tvDate.setText(upComingEventsItems.getDate());
        holder.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                Calendar cal = Calendar.getInstance();
                long startTime = cal.getTimeInMillis();
                long endTime = cal.getTimeInMillis() + 60 * 60 * 1000;
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(CalendarContract.Events.TITLE, "" + upComingEventsArrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return upComingEventsArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
