package app.integro.sjbhs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Alumni;

public class AlumniViewPagerAdapter extends PagerAdapter {

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
        ImageView ivNews;
        TextView tvTitle;
        View view= LayoutInflater.from(context).inflate(R.layout.card_alumni2,container,false);

        ivNews = view.findViewById(R.id.ivNews);
        tvTitle=view.findViewById(R.id.tvTitle);

        tvTitle.setText(alumniArrayList.get(position).getName());

        Glide.with(context)
                .load(alumniArrayList.get(position).getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivNews);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
