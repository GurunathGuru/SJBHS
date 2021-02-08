package app.integro.sjbhs.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.demono.AutoScrollViewPager;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import app.integro.sjbhs.AnnouncementsActivity;
import app.integro.sjbhs.EventsActivity;
import app.integro.sjbhs.NewsLetterActivity;
import app.integro.sjbhs.R;
import app.integro.sjbhs.WebActivity;
import app.integro.sjbhs.adapters.CoverPhotoPagerAdapter;
import app.integro.sjbhs.adapters.NewsViewPagerAdapter;
import app.integro.sjbhs.adapters.TextMessagePagerAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.CoverPhoto;
import app.integro.sjbhs.models.CoverPhotoList;
import app.integro.sjbhs.models.News;
import app.integro.sjbhs.models.NewsList;
import app.integro.sjbhs.models.Sjbhs_videos1;
import app.integro.sjbhs.models.Sjbhs_videos1List;
import app.integro.sjbhs.models.TextMessage;
import app.integro.sjbhs.models.TextMessageList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    private static final String TAG = "FragmentHome";

    private static final String API_KEY = "AIzaSyDI8lwUCJgNkMIOMYF9FaafwIWpKZ_J-ng";
    private YouTubePlayer mPlayer;
    private ArrayList<Sjbhs_videos1> videosArrayList;

    private ApiServices apiServices;
    private AutoScrollViewPager vpNews;
    private ArrayList<News> newsArrayList;
    private NewsViewPagerAdapter adapter;

    private AutoScrollViewPager vpCoverPhotos;
    private ArrayList<CoverPhoto> coverPhotoArrayList;
    private CoverPhotoPagerAdapter coverPhotoPagerAdapter;

    private AutoScrollViewPager vpTextMessage;
    private ArrayList<TextMessage> textMessageArrayList;
    private TextMessagePagerAdapter textMessagePagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiServices = ApiClients.getClient().create(ApiServices.class);
        vpNews = view.findViewById(R.id.vpNews);
        newsArrayList = new ArrayList<>();
        vpNews.setCycle(true);
        vpNews.startAutoScroll(3000);

        vpCoverPhotos = view.findViewById(R.id.vpCoverPhotos);
        coverPhotoArrayList = new ArrayList<>();
        vpCoverPhotos.setCycle(true);
        vpCoverPhotos.startAutoScroll(3000);

        vpTextMessage = view.findViewById(R.id.vpTextMessage);
        textMessageArrayList = new ArrayList<>();
        vpTextMessage.setCycle(true);
        vpTextMessage.startAutoScroll(3000);

        getNews();
        getCoverPhotoList();
        getVideosList();
        getTextMessageList();

        TextView tvAnnouncements =  view.findViewById(R.id.tvAnnouncements);
        TextView tvLogin =  view.findViewById(R.id.tvLogin);
        TextView tvEvents =  view.findViewById(R.id.tvEvents);
        TextView tvNewsLetter =  view.findViewById(R.id.tvNewsLetter);

        tvAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AnnouncementsActivity.class);
                startActivity(intent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tvEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EventsActivity.class);
                startActivity(intent);
            }
        });

        tvNewsLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewsLetterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void getNews() {
        Call<NewsList> newsListCall = apiServices.getNewsList();
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsList() != null) {
                        int size = response.body().getNewsList().size();
                        Log.d("RESPONSE", "NewsList size " + size);
                        for (int i = 0; i < size; i++) {
                            newsArrayList.add(response.body().getNewsList().get(i));
                            adapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                            vpNews.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    public void login() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.card_login, null);
        dialogBuilder.setView(view);

        final AlertDialog alertDialog = dialogBuilder.create();
        Button tvParent = view.findViewById(R.id.tvParent);
        Button tvFaculty = view.findViewById(R.id.tvFaculty);

        tvParent.setText("Parent Login");
        tvFaculty.setText("Faculty Login");

        tvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://sjbhs.pupilpod.in/home?destination=home";
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        tvFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://sjbhs.schoolphins.com/";
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void getCoverPhotoList() {
        Call<CoverPhotoList> coverPhotoListCall = apiServices.getCoverPhotoList();
        coverPhotoListCall.enqueue(new Callback<CoverPhotoList>() {
            @Override
            public void onResponse(Call<CoverPhotoList> call, Response<CoverPhotoList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCoverPhotoArrayList() != null) {
                        int size = response.body().getCoverPhotoArrayList().size();
                        Log.d("RESPONSE", "CoverPhotoArrayList size " + size);
                        for (int i = 0; i < size; i++) {
                            coverPhotoArrayList.add(response.body().getCoverPhotoArrayList().get(i));
                            coverPhotoPagerAdapter = new CoverPhotoPagerAdapter(getContext(), coverPhotoArrayList);
                            vpCoverPhotos.setAdapter(coverPhotoPagerAdapter);
                        }
                    } else {
                        Toast.makeText(getContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<CoverPhotoList> call, Throwable t) {
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
                            textMessagePagerAdapter= new TextMessagePagerAdapter(getContext(), textMessageArrayList);
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

    private void getVideosList() {
        videosArrayList = new ArrayList<>();
        Call<Sjbhs_videos1List> videosListCall = apiServices.getSjbhs_videos1List();
        videosListCall.enqueue(new Callback<Sjbhs_videos1List>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull retrofit2.Call<Sjbhs_videos1List> call, @NonNull Response<Sjbhs_videos1List> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body() == null && response.body().getArSjbhs_videos1() == null) {
                    return;
                }
                int size = response.body().getArSjbhs_videos1().size();
                for (int i = 0; i < size; i++) {
                    videosArrayList.add(response.body().getArSjbhs_videos1().get(i));
                }
                YouTubePlayerSupportFragment youTubePlayerFragment = new YouTubePlayerSupportFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();
                youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                        if (!b) {
                            mPlayer = youTubePlayer;
                            mPlayer.cueVideo("" + videosArrayList.get(0).getV_id());
                            Log.i(TAG, "onInitializationSuccess: " + videosArrayList.get(0).getV_id());
                            mPlayer.setFullscreen(false);
                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                        // TODO Auto-generated method stub
                    }
                });
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<Sjbhs_videos1List> call, @NonNull Throwable t) {

            }
        });
    }
}
