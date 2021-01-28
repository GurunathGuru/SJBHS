package app.integro.sjbhs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.AnnouncementAdapter;
import app.integro.sjbhs.adapters.NotificationAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Announcement;
import app.integro.sjbhs.models.AnnouncementList;
import app.integro.sjbhs.models.Notification;
import app.integro.sjbhs.models.NotificationList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsActivity extends AppCompatActivity {
    private ArrayList<Notification> notificationsArrayList;
    private RecyclerView rvNotification;
    private NotificationAdapter adapter;
    private TextView tvNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncements);

        rvNotification = findViewById(R.id.rvNotification);
        tvNoItems = findViewById(R.id.tvNoItems);
        rvNotification.setLayoutManager(new LinearLayoutManager(AnnouncementsActivity.this));
        notificationsArrayList = new ArrayList<>();
        getAnnouncementList();
    }

    public void getAnnouncementList() {
        Call<NotificationList> notificationListCall = ApiClients.getClient().create(ApiServices.class).getNotificationsList();
        notificationListCall.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNotificationList() != null) {
                        int size = response.body().getNotificationList().size();
                        Log.d("RESPONSE", "Notification " + size);
                        for (int i = 0; i < size; i++) {
                            notificationsArrayList.add(response.body().getNotificationList().get(i));
                            adapter = new NotificationAdapter(getApplicationContext(), notificationsArrayList);
                            rvNotification.setAdapter(adapter);
                        }
                    }

                } else {
                    tvNoItems.setVisibility(View.VISIBLE);
                    tvNoItems.setText("No Items Found..!");
                }
            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
