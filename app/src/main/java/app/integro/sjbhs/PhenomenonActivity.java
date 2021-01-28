package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.PhenomenonAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Phenomenon;
import app.integro.sjbhs.models.PhenomenonList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhenomenonActivity extends AppCompatActivity {

    private ArrayList<Phenomenon> phenomenonArrayList;
    private RecyclerView rvPhenomenon;
    private PhenomenonAdapter phenomenonAdapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phenomenon);

        rvPhenomenon = (RecyclerView) findViewById(R.id.rvPhenomenon);
        tvNoItems=findViewById(R.id.tvNoItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvPhenomenon.setLayoutManager(manager);
        phenomenonArrayList = new ArrayList<>();
        getPhenomenonList();
    }

    public void getPhenomenonList() {
        Call<PhenomenonList> phenomenonListCall = ApiClients.getClient().create(ApiServices.class).getPhenomenonList();
        phenomenonListCall.enqueue(new Callback<PhenomenonList>() {
            @Override
            public void onResponse(Call<PhenomenonList> call, Response<PhenomenonList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getPhenomenonArrayList() != null) {
                        int size = response.body().getPhenomenonArrayList().size();
                        Log.d("RESPONSE", "NewsList " + size);
                        for (int i = 0; i < size; i++) {
                            phenomenonArrayList.add(response.body().getPhenomenonArrayList().get(i));
                        }
                        phenomenonAdapter=new PhenomenonAdapter(getApplicationContext(),phenomenonArrayList);
                        rvPhenomenon.setAdapter(phenomenonAdapter);
                    } else {
                        tvNoItems.setVisibility(View.VISIBLE);
                        tvNoItems.setText("No Items Found..!");
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<PhenomenonList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

}
