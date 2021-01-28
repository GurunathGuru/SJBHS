package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Sjbhs_videos1;

public class VideosAdapter1 extends RecyclerView.Adapter<VideosAdapter1.MyViewHolder> {
    private final Context context;
    private final ArrayList<Sjbhs_videos1> videos1ArrayList;

    public VideosAdapter1(Context context, ArrayList<Sjbhs_videos1> videos1ArrayList) {
        this.context = context;
        this.videos1ArrayList = videos1ArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_video, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Sjbhs_videos1 sjbhs_videos1Item = videos1ArrayList.get(position);
        holder.name.setText(sjbhs_videos1Item.getTitle());

        String image_url="http://img.youtube.com/vi/"+sjbhs_videos1Item.getV_id()+"/0.jpg";

        Glide.with(context)
                .load(image_url)
                .into(holder.ivImage);

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + sjbhs_videos1Item.getV_id()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Log.i("Video", "Video Playing...." + sjbhs_videos1Item.getV_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videos1ArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvTitle);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}