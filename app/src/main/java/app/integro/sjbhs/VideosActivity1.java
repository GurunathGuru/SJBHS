package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.VideosAdapter1;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Sjbhs_videos1;
import app.integro.sjbhs.models.Sjbhs_videos1List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosActivity1 extends AppCompatActivity {

    private RecyclerView rvVideos1;
    private ArrayList<Sjbhs_videos1> videos1ArrayList;
    private VideosAdapter1 adapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos1);

        videos1ArrayList = new ArrayList<>();
        rvVideos1 = (RecyclerView) findViewById(R.id.rvVideos1);
        rvVideos1.setLayoutManager(new GridLayoutManager(this, 2));
        tvNoItems = findViewById(R.id.tvNoItems);

        getVideosList();
    }

    public void getVideosList() {
        Call<Sjbhs_videos1List> sjbhsVideos1ListCall = ApiClients.getClient().create(ApiServices.class).getSjbhs_videos1List();
        sjbhsVideos1ListCall.enqueue(new Callback<Sjbhs_videos1List>() {
            @Override
            public void onResponse(Call<Sjbhs_videos1List> call, Response<Sjbhs_videos1List> response) {
                if (response.isSuccessful()) {
                    if (response.body().getArSjbhs_videos1() != null) {
                        int size = response.body().getArSjbhs_videos1().size();
                        Log.d("RESPONSE", "AnnouncementList " + size);
                        for (int i = 0; i < size; i++) {
                            videos1ArrayList.add(response.body().getArSjbhs_videos1().get(i));
                            adapter = new VideosAdapter1(getApplicationContext(), videos1ArrayList);
                            rvVideos1.setAdapter(adapter);
                        }
                    } else {
                        tvNoItems.setVisibility(View.VISIBLE);
                        tvNoItems.setText("No Items Found..!");
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<Sjbhs_videos1List> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
