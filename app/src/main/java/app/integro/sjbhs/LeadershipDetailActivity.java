package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import app.integro.sjbhs.models.Leadership;

public class LeadershipDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leadership_detail);

        Leadership leadership= (Leadership) getIntent().getSerializableExtra("data");

        ImageView ivImage = findViewById(R.id.ivImage);
        TextView tvName = findViewById(R.id.tvTitle);
        TextView tvDesignation = findViewById(R.id.tvDesignation);
        TextView tvDescription = findViewById(R.id.tvDescription);

        Glide.with(getApplicationContext())
                .load(leadership.getImage())
                .into(ivImage);

        tvName.setText(leadership.getName());
        tvDesignation.setText(leadership.getDesignation());
        tvDescription.setText(leadership.getDescription());
    }
}
