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
import app.integro.sjbhs.models.CoverPhoto;

public class CoverPhotoPagerAdapter extends PagerAdapter {

    private final Context context;
    private final ArrayList<CoverPhoto> coverPhotoArrayList;

    public CoverPhotoPagerAdapter(Context context, ArrayList<CoverPhoto> coverPhotoArrayList) {
        this.context = context;
        this.coverPhotoArrayList = coverPhotoArrayList;
    }

    @Override
    public int getCount() {
        return coverPhotoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivCoverPhotos;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.card_coverphotos, container, false);
        ivCoverPhotos = itemView.findViewById(R.id.ivCoverPhotos);
        Glide.with(context)
                .load(coverPhotoArrayList.get(position).getImage())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivCoverPhotos);
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
