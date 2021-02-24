package app.integro.sjbhs.adapters;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Sjbhs_Photos2;


public class PhotosAdapter3 extends PagerAdapter {
    private final Context context;
    private final ArrayList<Sjbhs_Photos2> sjbhsPhotos2ArrayList;

    @Override
    public String getPageTitle(int position) {
        Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
        return "" + position;
    }

    public PhotosAdapter3(Context context, ArrayList<Sjbhs_Photos2> sjbhsPhotos2ArrayList) {
        this.context = context;
        this.sjbhsPhotos2ArrayList = sjbhsPhotos2ArrayList;
    }

    @Override
    public int getCount()
    {
        return sjbhsPhotos2ArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivCoverPhotos;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.card_photos3, container, false);
        ivCoverPhotos = itemView.findViewById(R.id.ivCoverPhotos);
        Glide.with(context)
                .load(sjbhsPhotos2ArrayList.get(position).getImage())
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
