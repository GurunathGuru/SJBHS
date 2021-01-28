package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.UpComingEventsAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.UpComingEvents;
import app.integro.sjbhs.models.UpComingEventsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingEventsActivity extends AppCompatActivity {

    private RecyclerView rvEvents;
    private ArrayList<UpComingEvents> upComingEventsArrayList;
    private UpComingEventsAdapter adapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        upComingEventsArrayList = new ArrayList<>();
        tvNoItems = findViewById(R.id.tvNoItems);
        rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        getUpComingEventsList();
    }

    public void getUpComingEventsList() {
        Call<UpComingEventsList> eventsListCall = ApiClients.getClient().create(ApiServices.class).getUpComingEventsList();
        eventsListCall.enqueue(new Callback<UpComingEventsList>() {
            @Override
            public void onResponse(Call<UpComingEventsList> call, Response<UpComingEventsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUpComingEventsList() != null) {
                        int size = response.body().getUpComingEventsList().size();
                        Log.d("RESPONSE", "EventsList " + size);
                        for (int i = 0; i < size; i++) {
                            upComingEventsArrayList.add(response.body().getUpComingEventsList().get(i));
                            adapter = new UpComingEventsAdapter(getApplicationContext(), upComingEventsArrayList);
                            rvEvents.setAdapter(adapter);
                        }
                    } else {
                        tvNoItems.setVisibility(View.VISIBLE);
                        tvNoItems.setText("No Items Found..!");
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<UpComingEventsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
