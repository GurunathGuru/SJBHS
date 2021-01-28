package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.LeadershipDetailActivity;
import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Leadership;

/**
 * Created by gurunath on 06-01-2018.
 */

public class LeadershipPagerAdapter extends PagerAdapter {

    private final Context context;
    private final ArrayList<Leadership> leadershipArrayList;

    public LeadershipPagerAdapter(Context context, ArrayList<Leadership> leadershipArrayList) {
        this.context = context;
        this.leadershipArrayList = leadershipArrayList;
    }

    @Override
    public int getCount() {
        return leadershipArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivImage;
        TextView tvName;
        TextView tvDesignation;
        CardView card_leadership;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.card_leadership, container, false);

        ivImage = itemView.findViewById(R.id.ivImage);
        tvName = itemView.findViewById(R.id.tvTitle);
        tvDesignation = itemView.findViewById(R.id.tvDesignation);
        card_leadership = itemView.findViewById(R.id.card_leadership);

        final Leadership leadership = leadershipArrayList.get(position);
        tvName.setText(leadership.getName());
        tvDesignation.setText(leadership.getDesignation());
        card_leadership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "coming soon", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, LeadershipDetailActivity.class);
                intent.putExtra("data", leadership);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .load(leadershipArrayList.get(position).getImage())
                .into(ivImage);
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
