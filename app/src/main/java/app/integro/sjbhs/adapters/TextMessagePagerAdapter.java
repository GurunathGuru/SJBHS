package app.integro.sjbhs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import app.integro.sjbhs.R;
import app.integro.sjbhs.WebActivity;
import app.integro.sjbhs.models.TextMessage;

public class TextMessagePagerAdapter extends PagerAdapter {

    private final Context context;
    private final ArrayList<TextMessage> textMessageArrayList;
    boolean flag;

    public TextMessagePagerAdapter(Context context, ArrayList<TextMessage> textMessageArrayList) {
        this.context = context;
        this.textMessageArrayList = textMessageArrayList;
    }

    public TextMessagePagerAdapter(Context context, ArrayList<TextMessage> textMessageArrayList, boolean flag) {
        this.context = context;
        this.textMessageArrayList = textMessageArrayList;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return textMessageArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView tvText;
        View view = LayoutInflater.from(context).inflate(R.layout.card_text_message, container, false);
        tvText = view.findViewById(R.id.tvTextMessage);
        tvText.setText(textMessageArrayList.get(position).getMsg());
        if (flag == true) {
            tvText.setBackgroundResource(R.color.colorOrange);
        }
        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = textMessageArrayList.get(position).getWebLink();
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("URL", url);
                context.startActivity(intent);
            }
        });
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
