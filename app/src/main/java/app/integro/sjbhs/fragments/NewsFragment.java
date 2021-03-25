package app.integro.sjbhs.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.demono.AutoScrollViewPager;

import java.util.ArrayList;

import app.integro.sjbhs.AlumniActivity;
import app.integro.sjbhs.R;
import app.integro.sjbhs.adapters.AlumniViewPagerAdapter;
import app.integro.sjbhs.adapters.UpComingEventsViewPagerAdapter;
import app.integro.sjbhs.adapters.TextMessagePagerAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Alumni;
import app.integro.sjbhs.models.Alumni_list;
import app.integro.sjbhs.models.UpComingEvents;
import app.integro.sjbhs.models.UpComingEventsList;
import app.integro.sjbhs.models.TextMessage;
import app.integro.sjbhs.models.TextMessageList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private ApiServices apiServices;
    private AutoScrollViewPager vpAlumni;
    private ArrayList<Alumni> alumniArrayList;
    private AlumniViewPagerAdapter alumniAdapter;

    private AutoScrollViewPager vpUpComingEvents;
    private ArrayList<UpComingEvents> upComingEventsArrayList;
    private UpComingEventsViewPagerAdapter upComingEventsViewPagerAdapter;

    private AutoScrollViewPager vpTextMessage;
    private ArrayList<TextMessage> textMessageArrayList;
    private TextMessagePagerAdapter textMessagePagerAdapter;
    private final boolean flag = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        apiServices = ApiClients.getClient().create(ApiServices.class);

        vpAlumni = view.findViewById(R.id.vpAlumni);
        alumniArrayList = new ArrayList<>();
        vpAlumni.setCycle(true);
        vpAlumni.startAutoScroll(3000);

        vpUpComingEvents = view.findViewById(R.id.vpUpComingEvents);
        upComingEventsArrayList = new ArrayList<>();
        vpUpComingEvents.setCycle(true);
        vpUpComingEvents.startAutoScroll(3000);

        vpTextMessage = view.findViewById(R.id.vpTextMessage);
        textMessageArrayList = new ArrayList<>();
        vpTextMessage.setCycle(true);
        vpTextMessage.startAutoScroll(3000);

        TextView tvAlumni = view.findViewById(R.id.tvAlumni);
        tvAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlumniActivity.class);
                startActivity(intent);
            }
        });

        getAlumni();
        getUpComingEventsList();
        getTextMessageList();
        return view;
    }

    public void getAlumni() {
        Call<Alumni_list> newsListCall = apiServices.getAlumniList();
        newsListCall.enqueue(new Callback<Alumni_list>() {
            @Override
            public void onResponse(Call<Alumni_list> call, Response<Alumni_list> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAlumniArrayList() != null) {
                        int size = response.body().getAlumniArrayList().size();
                        Log.d("RESPONSE", "NewsList size " + size);
                        for (int i = 0; i < size; i++) {
                            alumniArrayList.add(response.body().getAlumniArrayList().get(i));
                            alumniAdapter = new AlumniViewPagerAdapter(getContext(), alumniArrayList);
                            vpAlumni.setAdapter(alumniAdapter);
                        }
                    } else {
                        Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
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

    public void getUpComingEventsList() {
        Call<UpComingEventsList> upComingEventsListCall = apiServices.getUpComingEventsList();
        upComingEventsListCall.enqueue(new Callback<UpComingEventsList>() {
            @Override
            public void onResponse(Call<UpComingEventsList> call, Response<UpComingEventsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUpComingEventsList() != null) {
                        int size = response.body().getUpComingEventsList().size();
                        Log.d("RESPONSE", "NewsList size " + size);
                        upComingEventsArrayList.add(response.body().getUpComingEventsList().get(size - 1));
                        upComingEventsViewPagerAdapter = new UpComingEventsViewPagerAdapter(getContext(), upComingEventsArrayList);
                        vpUpComingEvents.setAdapter(upComingEventsViewPagerAdapter);
                    } else {
                        Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
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

    public void getTextMessageList() {
        Call<TextMessageList> textMessageListCall = apiServices.getTextMessageList();
        textMessageListCall.enqueue(new Callback<TextMessageList>() {
            @Override
            public void onResponse(Call<TextMessageList> call, Response<TextMessageList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getTextMessageArrayList() != null) {
                        int size = response.body().getTextMessageArrayList().size();
                        Log.d("RESPONSE", "CoverPhotoArrayList size " + size);
                        for (int i = 0; i < size; i++) {
                            textMessageArrayList.add(response.body().getTextMessageArrayList().get(i));
                            textMessagePagerAdapter = new TextMessagePagerAdapter(getContext(), textMessageArrayList, flag);
                            vpTextMessage.setAdapter(textMessagePagerAdapter);
                        }
                    } else {
                        Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<TextMessageList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }
}
