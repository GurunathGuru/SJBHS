package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.NewsAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.News;
import app.integro.sjbhs.models.NewsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private ArrayList<News> newsArrayList;
    private RecyclerView rvNews;
    private NewsAdapter newsAdapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        rvNews = (RecyclerView) findViewById(R.id.rvNews);
        tvNoItems=findViewById(R.id.tvNoItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvNews.setLayoutManager(manager);
        newsArrayList = new ArrayList<>();
        getNews();
    }

    public void getNews() {
        Call<NewsList> newsListCall = ApiClients.getClient().create(ApiServices.class).getNewsList();
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsList() != null) {
                        int size = response.body().getNewsList().size();
                        Log.d("RESPONSE", "NewsList " + size);
                        for (int i = 0; i < size; i++) {
                            newsArrayList.add(response.body().getNewsList().get(i));
                        }
                        newsAdapter=new NewsAdapter(getApplicationContext(),newsArrayList);
                        rvNews.setAdapter(newsAdapter);
                    } else {
                        tvNoItems.setVisibility(View.VISIBLE);
                        tvNoItems.setText("No Items Found..!");
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

}