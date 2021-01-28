package app.integro.sjbhs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Phenomenon;

public class PhenomenonAdapter extends RecyclerView.Adapter<PhenomenonAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Phenomenon> phenomenonArrayList;

    public PhenomenonAdapter(Context context, ArrayList<Phenomenon> phenomenonArrayList) {
        this.context = context;
        this.phenomenonArrayList = phenomenonArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_mun, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Phenomenon phenomenon = phenomenonArrayList.get(position);
        holder.tvN_Title.setText(phenomenon.getTitle());
        holder.tvDescription.setText(phenomenon.getDescription());

        Glide.with(context)
                .load(phenomenon.getImage())
                .into(holder.ivNews);
    }

    @Override
    public int getItemCount() {
        return phenomenonArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNews;
        TextView tvN_Title;
        TextView tvDescription;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivNews = (ImageView) itemView.findViewById(R.id.ivNews);
            tvN_Title = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}
