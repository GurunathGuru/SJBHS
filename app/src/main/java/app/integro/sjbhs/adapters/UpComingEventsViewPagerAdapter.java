package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import app.integro.sjbhs.UpcomingEventsActivity;
import app.integro.sjbhs.R;
import app.integro.sjbhs.models.UpComingEvents;

public class UpComingEventsViewPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<UpComingEvents> upComingEventsArrayList;

    public UpComingEventsViewPagerAdapter(Context context, ArrayList<UpComingEvents> upComingEventsArrayList) {
        this.context = context;
        this.upComingEventsArrayList = upComingEventsArrayList;
    }

    @Override
    public int getCount() {
        return upComingEventsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivImage;
        TextView tvTitle;
        CardView card_events;
        View view = LayoutInflater.from(context).inflate(R.layout.card_events2, container, false);

        card_events = view.findViewById(R.id.card_events);
        ivImage = view.findViewById(R.id.ivImage);
        tvTitle = view.findViewById(R.id.tvTitle);

        tvTitle.setText(upComingEventsArrayList.get(0).getTitle());

        card_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpcomingEventsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        /*Glide.with(context)
                .load(eventsArrayList.get(position).getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivNews);*/

        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
