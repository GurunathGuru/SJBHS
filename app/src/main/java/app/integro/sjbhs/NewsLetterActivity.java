package app.integro.sjbhs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.NewsLaterAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.NewsLetter;
import app.integro.sjbhs.models.NewsLetterList;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsLetterActivity extends AppCompatActivity {


    private RecyclerView rvNewsLetter;
    private ArrayList<NewsLetter> newsLetterArrayList;
    private NewsLaterAdapter adapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter);

        rvNewsLetter = findViewById(R.id.rvNewsLetter);
        tvNoItems = findViewById(R.id.tvNoItems);
        newsLetterArrayList = new ArrayList<>();
        rvNewsLetter.setLayoutManager(new LinearLayoutManager(this));
        getNewsLetterList();
    }

    public void getNewsLetterList() {
        retrofit2.Call<NewsLetterList> newsLetterListCall = ApiClients.getClient().create(ApiServices.class).getNewsLetterList();
        newsLetterListCall.enqueue(new Callback<NewsLetterList>() {
            @Override
            public void onResponse(retrofit2.Call<NewsLetterList> call, Response<NewsLetterList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsLetterArrayList() != null) {
                        int size = response.body().getNewsLetterArrayList().size();
                        for (int i = 0; i < size; i++) {
                            newsLetterArrayList.add(response.body().getNewsLetterArrayList().get(i));
                        }
                        adapter = new NewsLaterAdapter(getApplicationContext(), newsLetterArrayList);
                        rvNewsLetter.setAdapter(adapter);
                    } else {
                        tvNoItems.setVisibility(View.VISIBLE);
                        tvNoItems.setText("No Items Found..!");
                    }
                } else {
                    Toast.makeText(NewsLetterActivity.this, "Internet Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<NewsLetterList> call, Throwable t) {

            }
        });
    }
}
