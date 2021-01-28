package app.integro.sjbhs;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.PhotosAdapter3;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Sjbhs_Photos2;
import app.integro.sjbhs.models.Sjbhs_Photos2List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosActivity3 extends AppCompatActivity {

    private ArrayList<Sjbhs_Photos2> photos2ArrayList;
    private PhotosAdapter3 adapter;
    private ViewPager vpGallery;
    private String photos_id;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos3);

        Sjbhs_Photos2 sjbhs_photos2 = (Sjbhs_Photos2) getIntent().getSerializableExtra("data");
        position = (int) getIntent().getSerializableExtra("position");
        photos_id = sjbhs_photos2.getP_id();

        photos2ArrayList = new ArrayList<>();
        vpGallery = findViewById(R.id.vpGallery);
        getFullImage();
    }

    public void getFullImage() {
        String date = photos_id;
        Call<Sjbhs_Photos2List> photos2ListCall =  ApiClients.getClient().create(ApiServices.class).getSjbhs_photos1List(date);
        photos2ListCall.enqueue(new Callback<Sjbhs_Photos2List>() {
            @Override
            public void onResponse(Call<Sjbhs_Photos2List> call, Response<Sjbhs_Photos2List> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSjbhsPhotos1ArrayList() != null) {
                        int size = response.body().getSjbhsPhotos1ArrayList().size();
                        Log.d("RESPONSE", "Gallery Size " + size);
                        for (int i = 0; i < size; i++) {
                            photos2ArrayList.add(response.body().getSjbhsPhotos1ArrayList().get(i));
                            adapter = new PhotosAdapter3(getApplicationContext(), photos2ArrayList);
                            vpGallery.setAdapter(adapter);
                            vpGallery.setCurrentItem(Integer.parseInt(String.valueOf(position)));
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Response Null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Sjbhs_Photos2List> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}