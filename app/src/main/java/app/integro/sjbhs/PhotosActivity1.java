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

import app.integro.sjbhs.adapters.PhotosAdapter1;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Sjbhs_photos1;
import app.integro.sjbhs.models.Sjbhs_photos1List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity1 extends AppCompatActivity {

    private RecyclerView rvPhotos;
    private ArrayList<Sjbhs_photos1> photosArrayList;
    private PhotosAdapter1 adapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos1);

        photosArrayList = new ArrayList<>();
        rvPhotos = (RecyclerView) findViewById(R.id.rvPhotos);
        rvPhotos.setLayoutManager(new GridLayoutManager(this, 2));
        tvNoItems = findViewById(R.id.tvNoItems);
        getPhotosList();
    }

    public void getPhotosList() {
        Call<Sjbhs_photos1List> photosListCall = ApiClients.getClient().create(ApiServices.class).getSjbhs_photosList();
        photosListCall.enqueue(new Callback<Sjbhs_photos1List>() {
            @Override
            public void onResponse(Call<Sjbhs_photos1List> call, Response<Sjbhs_photos1List> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSjbhs_photos1ArrayList() != null) {
                        int size = response.body().getSjbhs_photos1ArrayList().size();
                        Log.d("RESPONSE", "AnnouncementList " + size);
                        for (int i = 0; i < size; i++) {
                            photosArrayList.add(response.body().getSjbhs_photos1ArrayList().get(i));
                            adapter = new PhotosAdapter1(getApplicationContext(), photosArrayList);
                            rvPhotos.setAdapter(adapter);
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
            public void onFailure(Call<Sjbhs_photos1List> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
