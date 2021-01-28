package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.PhotosActivity3;
import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Sjbhs_Photos2;

public class PhotosAdapter2 extends RecyclerView.Adapter<PhotosAdapter2.MyViewHolder> {

    private final Context context;
    private final ArrayList<Sjbhs_Photos2> photosArrayList;

    public PhotosAdapter2(Context context, ArrayList<Sjbhs_Photos2> photosArrayList) {
        this.context = context;
        this.photosArrayList = photosArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_photos2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Sjbhs_Photos2 sjbhs_photos2Item = photosArrayList.get(position);

        Glide.with(context)
                .load(sjbhs_photos2Item.getImage())
                .into(holder.ivImage);

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosActivity3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position", position);
                intent.putExtra("data", sjbhs_photos2Item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvNoItems);
            ivImage = (ImageView) itemView.findViewById(R.id.ivPhotos);
        }
    }
}
