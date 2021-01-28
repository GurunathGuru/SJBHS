package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.AlumniAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Alumni;
import app.integro.sjbhs.models.Alumni_list;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlumniActivity extends AppCompatActivity {

    private RecyclerView rvAlumni;
    private ArrayList<Alumni> alumniArrayList;
    private AlumniAdapter adapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);

        alumniArrayList = new ArrayList<>();
        rvAlumni = findViewById(R.id.rvAlumni);
        rvAlumni.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        tvNoItems = findViewById(R.id.tvNoItems);
        getAlumniList();
    }

    public void getAlumniList() {
        Call<Alumni_list> alumniListCall = ApiClients.getClient().create(ApiServices.class).getAlumniList();
        alumniListCall.enqueue(new Callback<Alumni_list>() {
            @Override
            public void onResponse(Call<Alumni_list> call, Response<Alumni_list> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAlumniArrayList() != null) {
                        int size = response.body().getAlumniArrayList().size();
                        Log.d("RESPONSE", "AnnouncementList " + size);
                        for (int i = 0; i < size; i++) {
                            alumniArrayList.add(response.body().getAlumniArrayList().get(i));
                            adapter = new AlumniAdapter(getApplicationContext(), alumniArrayList);
                            rvAlumni.setAdapter(adapter);
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
            public void onFailure(Call<Alumni_list> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
