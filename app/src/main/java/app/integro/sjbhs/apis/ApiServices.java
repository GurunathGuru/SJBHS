package app.integro.sjbhs.apis;

import app.integro.sjbhs.models.Alumni_list;
import app.integro.sjbhs.models.AnnouncementList;
import app.integro.sjbhs.models.CoverPhotoList;
import app.integro.sjbhs.models.UpComingEventsList;
import app.integro.sjbhs.models.LeadershipList;
import app.integro.sjbhs.models.EventsList;
import app.integro.sjbhs.models.NewsImagesList;
import app.integro.sjbhs.models.NewsLetterList;
import app.integro.sjbhs.models.NewsList;
import app.integro.sjbhs.models.NotificationList;
import app.integro.sjbhs.models.PhenomenonList;
import app.integro.sjbhs.models.Sjbhs_Photos2List;
import app.integro.sjbhs.models.Sjbhs_photos1List;
import app.integro.sjbhs.models.Sjbhs_videos1List;
import app.integro.sjbhs.models.Sjbhs_videos2List;
import app.integro.sjbhs.models.TextMessageList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @GET("sjbhs_coverphoto.php")
    Call<CoverPhotoList> getCoverPhotoList();

    @GET("sjbhs_leadership.php")
    Call<LeadershipList> getLeadershipList();

    @GET("sjbhs_news.php")
    Call<NewsList> getNewsList();

    // notifications and announcements
    @GET("sjbhs_notification.php")
    Call<NotificationList> getNotificationsList();

    // events
    @GET("sjbhs_events.php")
    Call<EventsList> getEventList();

    // upcoming events
    @GET("sjbhs_event_of_month.php")
    Call<UpComingEventsList> getUpComingEventsList();

    @GET("sjbhs_newsletter.php")
    Call<NewsLetterList> getNewsLetterList();

    @FormUrlEncoded
    @POST("sjbhs_newsimage.php")
    Call<NewsImagesList> getNewsImagesList(@Field("news_id") String d_id);

    @GET("sjbhs_alumni.php")
    Call<Alumni_list> getAlumniList();

    @GET("sjbhs_photos.php")
    Call<Sjbhs_photos1List> getSjbhs_photosList();

    @FormUrlEncoded
    @POST("sjbhs_photo1.php")
    Call<Sjbhs_Photos2List> getSjbhs_photos1List(@Field("p_id") String p_id);

    @GET("sjbhs_videos.php")
    Call<Sjbhs_videos1List> getSjbhs_videos1List();

    @FormUrlEncoded
    @POST("sjbhs_video1.php")
    Call<Sjbhs_videos2List> getSjbhs_videos2List(@Field("v_id") String video_id);

    @GET("sjbhs_phenomenon.php")
    Call<PhenomenonList> getPhenomenonList();

    @GET("sjbhs_msg.php")
    Call<TextMessageList> getTextMessageList();
}
