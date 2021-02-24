package app.integro.sjbhs.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.models.Alumni;

public class AlumniAdapter extends RecyclerView.Adapter<AlumniAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Alumni> alumni_lists;

    public AlumniAdapter(Context context, ArrayList<Alumni> alumni_lists) {
        this.context = context;
        this.alumni_lists = alumni_lists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_alumni, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Alumni alumniItem = alumni_lists.get(position);

        holder.name.setText(alumniItem.getName());
        holder.designation.setText(alumniItem.getDesignation());
        Glide.with(context)
                .load(alumniItem.getImage())
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return alumni_lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name,designation;
        private final ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvTitle);
            designation = itemView.findViewById(R.id.tvDesignation);
            ivImage = itemView.findViewById(R.id.ivImage);

        }
    }
}