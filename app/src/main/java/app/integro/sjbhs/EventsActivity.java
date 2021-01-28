package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.EventsAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Events;
import app.integro.sjbhs.models.EventsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity {

    private ArrayList<Events> eventsArrayList;
    private RecyclerView rvEvents;
    private EventsAdapter eventsAdapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
        tvNoItems=findViewById(R.id.tvNoItems);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvEvents.setLayoutManager(manager);
        eventsArrayList = new ArrayList<>();
        getEventList();
    }

    public void getEventList() {
        Call<EventsList> eventsListCall = ApiClients.getClient().create(ApiServices.class).getEventList();
        eventsListCall.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(Call<EventsList> call, Response<EventsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEventsArrayList() != null) {
                        int size = response.body().getEventsArrayList().size();
                        Log.d("RESPONSE", "NewsList " + size);
                        for (int i = 0; i < size; i++) {
                            eventsArrayList.add(response.body().getEventsArrayList().get(i));
                        }
                        eventsAdapter =new EventsAdapter(getApplicationContext(), eventsArrayList);
                        rvEvents.setAdapter(eventsAdapter);
                    } else {
                        tvNoItems.setVisibility(View.VISIBLE);
                        tvNoItems.setText("No Items Found..!");
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<EventsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

}