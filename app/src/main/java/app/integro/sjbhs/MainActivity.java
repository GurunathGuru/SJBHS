package app.integro.sjbhs;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.github.demono.AutoScrollViewPager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import app.integro.sjbhs.adapters.LeadershipPagerAdapter;
import app.integro.sjbhs.adapters.SliderAdapter;
import app.integro.sjbhs.apis.ApiClients;
import app.integro.sjbhs.apis.ApiServices;
import app.integro.sjbhs.models.Leadership;
import app.integro.sjbhs.models.LeadershipList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.integro.sjbhs.firebase.MyFirebaseMessagingService.NEWS_KEY;
import static app.integro.sjbhs.firebase.MyFirebaseMessagingService.NOTIFICATION_KEY;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "MyFirebaseMsgService";

    private AutoScrollViewPager vpLeaderShip;
    private ArrayList<Leadership> leadershipArrayList;
    private LeadershipPagerAdapter leadershipPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("sjbhs");

        vpLeaderShip = findViewById(R.id.vpLeaderShip);
        leadershipArrayList = new ArrayList<>();
        vpLeaderShip.startAutoScroll(3000);
        vpLeaderShip.setCycle(true);
        getLeadershipList();

        TextView tvAboutUs = findViewById(R.id.tvAboutUs);
        TextView tvLogin = findViewById(R.id.tvLogin);
        ImageView ivCall = findViewById(R.id.ivCall);
        ImageView ivEmail = findViewById(R.id.ivEmail);
        ImageView ivGps = findViewById(R.id.ivGps);
        LinearLayout llGallery = findViewById(R.id.llGallery);
        LinearLayout llVideos = findViewById(R.id.llVideos);

        ViewPager slidingViewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(slidingViewPager);
        slidingViewPager.setAdapter(new SliderAdapter(getSupportFragmentManager()));
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_h1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_n1);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_nt1);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_w2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        boolean isFCMIntent = getIntent().getBooleanExtra(TAG, false);
        if (isFCMIntent) {
            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case NEWS_KEY:
                    slidingViewPager.setCurrentItem(1);
                    break;
                case NOTIFICATION_KEY:
                    slidingViewPager.setCurrentItem(2);
                    break;
            }
        }

        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://sjbhs.edu.in/sjbhs_app/about.html";
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumbers();
            }
        });
        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        ivGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.co.in/maps/place/St.+Joseph's+Boys'+High+School/@12.9719582,77.6035858,15z/data=!4m5!3m4!1s0x0:0xf98d7647ba7e3522!8m2!3d12.9719582!4d77.6035858";
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });

        llGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotosActivity1.class);
                startActivity(intent);
            }
        });

        llVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideosActivity1.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getLeadershipList() {
        Call<LeadershipList> leadershipListCall = ApiClients.getClient().create(ApiServices.class).getLeadershipList();
        leadershipListCall.enqueue(new Callback<LeadershipList>() {
            @Override
            public void onResponse(Call<LeadershipList> call, Response<LeadershipList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getLeadershipListArrayList() != null) {
                        int size = response.body().getLeadershipListArrayList().size();
                        Log.d("RESPONSE", "NewsList size " + size);
                        for (int i = 0; i < size; i++) {
                            leadershipArrayList.add(response.body().getLeadershipListArrayList().get(i));
                            leadershipPagerAdapter = new LeadershipPagerAdapter(getApplicationContext(), leadershipArrayList);
                            vpLeaderShip.setAdapter(leadershipPagerAdapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "NO ITEMS FOUND", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RESPONSE", "RESPONSE FAIL");
                }
            }

            @Override
            public void onFailure(Call<LeadershipList> call, Throwable t) {
                Log.d("RESPONSE", "SERVER FAIL");
            }
        });
    }

    public void sendEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"sjmch.infodesk@stjohns.in"});
        i.putExtra(Intent.EXTRA_SUBJECT, "");
        i.putExtra(Intent.EXTRA_TEXT, "");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void phoneNumbers() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog_menu, null);
        dialogBuilder.setView(view);

        final AlertDialog alertDialog = dialogBuilder.create();
        TextView tvText = (TextView) view.findViewById(R.id.tvParent);
        TextView tvText1 = (TextView) view.findViewById(R.id.tvFaculty);

        tvText.setText("+91 080 22065000");
        tvText1.setText("+91 080 22065001");

        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:+91 080 22065000";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        tvText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:+91 080 22065001";
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit")
                .setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void getPta(View view) {
        String url = "https://sjbhs.edu.in/pta.php";
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void facebook(View view) {
        String url = "https://www.facebook.com/SJBHS-2201000466826851/";
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void instagram(View view) {
        String url = "https://www.instagram.com/sjbhs.official/";
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void youtube(View view) {
        String url = "https://www.youtube.com/channel/UCZiGHEzyTulO6QdCOWpC59w";
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void openAdmissions(View view) {
        String url = "https://sjbhs.edu.in/whysjbhs.php";
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    public void openSportsAndCurricular(View view) {
        String url = "https://sjbhs.edu.in/co-curricular.php";
        Intent intent = new Intent(getApplicationContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }
}
