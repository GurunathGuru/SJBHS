package app.integro.sjbhs.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Alumni;

public class AlumniViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "AlumniViewPagerAdapter";
    private final Context context;
    private final ArrayList<Alumni> alumniArrayList;

    public AlumniViewPagerAdapter(Context context, ArrayList<Alumni> alumniArrayList) {
        this.context = context;
        this.alumniArrayList = alumniArrayList;
    }

    @Override
    public int getCount() {
        return alumniArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvDesignation;
        View view = LayoutInflater.from(context).inflate(R.layout.card_alumni2, container, false);

        ivImage = view.findViewById(R.id.ivImage);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDesignation = view.findViewById(R.id.tvDesignation);

        tvTitle.setText(alumniArrayList.get(position).getName());
        tvDesignation.setText(alumniArrayList.get(position).getDesignation());

        Log.d(TAG, "instantiateItem: " + alumniArrayList.get(0).getImage());
        Glide.with(context)
                .load(alumniArrayList.get(position).getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivImage);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
