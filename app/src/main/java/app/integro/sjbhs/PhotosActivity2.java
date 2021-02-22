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

import app.integro.sjbhs.adapters.PhotosAdapter2;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Sjbhs_Photos2;
import app.integro.sjbhs.models.Sjbhs_Photos2List;
import app.integro.sjbhs.models.Sjbhs_photos1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity2 extends AppCompatActivity {
    private static final String TAG = "PhotosActivity2";
    private RecyclerView rvPhotos;
    private ArrayList<Sjbhs_Photos2> photos1ArrayList;
    private PhotosAdapter2 adapter;
    private TextView tvNoItems;

    String photos_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos2);

        Sjbhs_photos1 sjbhsPhotos1 = (Sjbhs_photos1) getIntent().getSerializableExtra("data");
        photos_id = sjbhsPhotos1.getId();
        //Log.d("RESPONSE", "photos_id " + photos_id);


        photos1ArrayList = new ArrayList<>();
        rvPhotos = (RecyclerView) findViewById(R.id.rvGallery);
        rvPhotos.setLayoutManager(new GridLayoutManager(this, 2));
        tvNoItems = findViewById(R.id.tvNoItems);
        getPhotosList();
    }

    public void getPhotosList() {
        Log.d(TAG, "getPhotosList: "+photos_id);
        Call<Sjbhs_Photos2List> photos1ListCall = ApiClients.getClient().create(ApiServices.class).getSjbhs_photos1List(photos_id);
        photos1ListCall.enqueue(new Callback<Sjbhs_Photos2List>() {
            @Override
            public void onResponse(Call<Sjbhs_Photos2List> call, Response<Sjbhs_Photos2List> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSjbhsPhotos1ArrayList() != null) {
                        int size = response.body().getSjbhsPhotos1ArrayList().size();
                        Log.d("RESPONSE", "AnnouncementList " + size);
                        for (int i = 0; i < size; i++) {
                            photos1ArrayList.add(response.body().getSjbhsPhotos1ArrayList().get(i));
                            adapter = new PhotosAdapter2(getApplicationContext(), photos1ArrayList);
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
            public void onFailure(Call<Sjbhs_Photos2List> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
