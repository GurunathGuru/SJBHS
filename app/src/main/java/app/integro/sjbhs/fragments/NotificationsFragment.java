package app.integro.sjbhs.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.adapters.NotificationAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Notification;
import app.integro.sjbhs.models.NotificationList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private ArrayList<Notification> notificationsArrayList;
    private RecyclerView rvNotification;
    private NotificationAdapter adapter;
    private TextView tvNoItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        rvNotification = (RecyclerView) view.findViewById(R.id.rvNotifications);
        tvNoItems = view.findViewById(R.id.tvNoItems);
        rvNotification.setLayoutManager( new LinearLayoutManager(getContext()));
        notificationsArrayList = new ArrayList<>();
        getNotification();

        return view;
    }

    public void getNotification() {
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
                            adapter = new NotificationAdapter(getContext(), notificationsArrayList);
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
