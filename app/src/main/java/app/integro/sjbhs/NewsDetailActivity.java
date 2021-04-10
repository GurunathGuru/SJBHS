package app.integro.sjbhs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.github.demono.AutoScrollViewPager;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.NewsImagesViewPagerAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.News;
import app.integro.sjbhs.models.NewsImages;
import app.integro.sjbhs.models.NewsImagesList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView ivImages;
    private News newsItem;

    private AutoScrollViewPager vpNewsImages;
    private ArrayList<NewsImages> newsImagesArrayList;
    private NewsImagesViewPagerAdapter adapter;
    private String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        vpNewsImages = findViewById(R.id.vpNewsImages);
        newsImagesArrayList = new ArrayList<>();
        vpNewsImages.startAutoScroll(3000);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvDate = (TextView) findViewById(R.id.tvDate);
        Button btnShare = (Button) findViewById(R.id.btnShare);
        ivImages = findViewById(R.id.ivImages);

        newsItem = (News) getIntent().getSerializableExtra("data");
        itemId = (String) getIntent().getSerializableExtra("itemId");

        tvTitle.setText(newsItem.getTitle());
        tvDescription.setText(newsItem.getDescription());
        tvDate.setText(newsItem.getDate());

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://sjbhs.edu.in/sjbhs_app/newsshare.php?id=" + newsItem.getId());
                startActivity(intent);
            }
        });
        getNewsImages();
    }

    public void getNewsImages() {
        Log.d("RESPONSE", "NewsImgasList Id " + itemId);
        Call<NewsImagesList> newsImagesListCall = ApiClients.getClient().create(ApiServices.class).getNewsImagesList(itemId);
        newsImagesListCall.enqueue(new Callback<NewsImagesList>() {
            @Override
            public void onResponse(Call<NewsImagesList> call, Response<NewsImagesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsImagesArrayList() != null) {
                        int size = response.body().getNewsImagesArrayList().size();
                        Log.d("RESPONSE", "NewsList size " + size);
                        if (size != 0) {
                            for (int i = 0; i < size; i++) {
                                newsImagesArrayList.add(response.body().getNewsImagesArrayList().get(i));
                                adapter = new NewsImagesViewPagerAdapter(getApplicationContext(), newsImagesArrayList);
                                vpNewsImages.setAdapter(adapter);
                            }
                        } else {
                            vpNewsImages.setVisibility(View.GONE);
                            ivImages.setVisibility(View.VISIBLE);

                            if (newsItem.getImage().equals("")) {
                                Glide.with(getApplicationContext())
                                        .load(R.drawable.sjbhs_building)
                                        .into(ivImages);
                            } else {
                                Glide.with(getApplicationContext())
                                        .load(newsItem.getImage())
                                        .into(ivImages);
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<NewsImagesList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
