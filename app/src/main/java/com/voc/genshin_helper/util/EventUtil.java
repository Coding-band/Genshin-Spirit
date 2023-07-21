package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.voc.genshin_helper.util.LogExport.EVENTLIST;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.CalculatorDBAdapter;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ItemRss;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventUtil {

    Context context;
    Activity activity;

    TextView event_available_tv, event_item_title1, event_item_title2, event_item_title3, event_item_title4, event_item_day1, event_item_day2, event_item_day3, event_item_day4;

    String jsonResult = null;

    ArrayList<EventItem> eventItemArrayList = new ArrayList<>();
    View viewInDesk;
    public void init(View viewInDesk, Context context, Activity activity){
        this.context = context;
        this.activity = activity;
        this.viewInDesk = viewInDesk;

        new grabDataFromServer().execute(ItemRss.SERVER_DOWNLOAD_ROOT + "/dailyMemo_3.5/dailyMemoEventPort.php");

    }




    private void displayRefresh() {
        System.out.println("viewInDesk has home_eventlist ? "+viewInDesk.findViewById(R.id.home_eventlist));
        System.out.println("viewInDesk has home_dailymemo ? "+viewInDesk.findViewById(R.id.home_dailymemo));
        event_available_tv = viewInDesk.findViewById(R.id.event_available_tv);
        event_item_title1 = viewInDesk.findViewById(R.id.event_item_title1);
        event_item_title2 = viewInDesk.findViewById(R.id.event_item_title2);
        event_item_title3 = viewInDesk.findViewById(R.id.event_item_title3);
        event_item_title4 = viewInDesk.findViewById(R.id.event_item_title4);
        event_item_day1 = viewInDesk.findViewById(R.id.event_item_day1);
        event_item_day2 = viewInDesk.findViewById(R.id.event_item_day2);
        event_item_day3 = viewInDesk.findViewById(R.id.event_item_day3);
        event_item_day4 = viewInDesk.findViewById(R.id.event_item_day4);

        event_item_title1.setText(eventItemArrayList.get(0).getTitle());
        event_item_title2.setText(eventItemArrayList.get(1).getTitle());
        event_item_title3.setText(eventItemArrayList.get(2).getTitle());
        event_item_title4.setText(eventItemArrayList.get(3).getTitle());
        event_item_day1.setText(String.valueOf(eventItemArrayList.get(0).getEnd_time_days()));
        event_item_day2.setText(String.valueOf(eventItemArrayList.get(1).getEnd_time_days()));
        event_item_day3.setText(String.valueOf(eventItemArrayList.get(2).getEnd_time_days()));
        event_item_day4.setText(String.valueOf(eventItemArrayList.get(3).getEnd_time_days()));
        event_available_tv.setText(setSpanAndTv(
                context.getColor(R.color.event_list_available_number),
                context.getString(R.string.event_list_available).replace("{%1}",String.valueOf(eventItemArrayList.size())),
                String.valueOf(eventItemArrayList.size()),
                20
        ));

        CardView event_card = viewInDesk.findViewById(R.id.event_card);

        event_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListFullList();
            }
        });

        // Press and show a dialog / fragment, which list all upcoming events

        // Press one -> Show HTML webpage (Get from content)
    }

    public void eventListFullList(){
        final Dialog dialog = new Dialog(context, R.style.PageDialogStyle_P);
        View view = View.inflate(context, R.layout.fragment_event_list_full, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        BackgroundReload.BackgroundReload(context,view);
        RecyclerView event_list_recycleview = view.findViewById(R.id.event_list_recycleview);
        EventAdapter eventAdapter = new EventAdapter(context);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        event_list_recycleview.setLayoutManager(mLayoutManager);
        event_list_recycleview.setAdapter(eventAdapter);

        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = (int) (width);
        lp.height = (int) (height);
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void eventItemWeb (EventItem eventItem, Context context){
        final Dialog dialog = new Dialog(context, R.style.PageDialogStyle_P);
        View view = View.inflate(context, R.layout.fragment_event_list_web, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        TextView info_title = view.findViewById(R.id.info_title);
        WebView event_web = view.findViewById(R.id.event_web);
        info_title.setText(eventItem.getTitle());
        String head = "<head><style>@font-face {font-family: 'SDK_SC_Web';src: url('file:///android_asset/genshin_font.ttf');}body {font-family: 'SDK_SC_Web';}</style></head><img src=\""+eventItem.getBanner()+"\" style='height: auto; width: 100%; object-fit: contain' >";
        event_web.loadDataWithBaseURL(null, head+eventItem.getContent().replace("&lt;","<").replace("&gt;",">"), "text/html", "utf-8", null);
        event_web.getSettings().setLoadWithOverviewMode(true);
        //event_web.getSettings().setUseWideViewPort(true);

        System.out.println(head+eventItem.getContent().replace("&lt;","<").replace("&gt;",">"));

        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = (int) (width);
        lp.height = (int) (height);
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    class grabDataFromServer extends AsyncTask<String,Integer,String>{
        private static final int TIME_OUT = 5000;
        protected void onPreExecute() {
            jsonResult = null;
        }
        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            // 再背景中處理的耗時工作
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url[0]).build();

            System.out.println(url[0]);

            try {
                Response sponse = client.newCall(request).execute();
                jsonResult = sponse.body().string();
                if (jsonResult == null){
                    LogExport.export("EventUtil", "init() -> jsonResult == null", "True", context, LogExport.EVENTLIST);
                    return null;
                }

                JSONArray jsonArray = new JSONArray(jsonResult);
                for (int x = 0 ; x < jsonArray.length() ; x++){
                    JSONObject jsonObject = jsonArray.getJSONObject(x);
                    eventItemArrayList.add(new EventItem(
                            jsonObject.getString("title"),
                            jsonObject.getString("title_detail"),
                            jsonObject.getString("subtitle"),
                            jsonObject.getString("banner"),
                            jsonObject.getString("content"),
                            jsonObject.getLong("end_time_unix"),
                            jsonObject.getInt("end_time_days")
                    ));
                }

            } catch (IOException | JSONException e) {
                LogExport.export("EventUtil","grabDataFromServer.doInBackground", e.getMessage(), context, EVENTLIST);
            }
            return "DONE";
        }



        public void onPostExecute(String result )
        { super.onPreExecute();
            // 背景工作處理完"後"需作的事
            displayRefresh();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

        }
    }


    public static class EventItem{
        String title;
        String title_detail;
        String subtitle;
        String banner;
        String content;
        long end_time_unix;
        int end_time_days;
        public EventItem(String title, String title_detail, String subtitle, String banner, String content, long end_time_unix, int end_time_days) {
            this.title = title;
            this.title_detail = title_detail;
            this.subtitle = subtitle;
            this.banner = banner;
            this.content = content;
            this.end_time_unix = end_time_unix;
            this.end_time_days = end_time_days;
        }

        public String getTitle_detail() {
            return title_detail;
        }

        public void setTitle_detail(String title_detail) {
            this.title_detail = title_detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getEnd_time_unix() {
            return end_time_unix;
        }

        public void setEnd_time_unix(long end_time_unix) {
            this.end_time_unix = end_time_unix;
        }

        public int getEnd_time_days() {
            return end_time_days;
        }

        public void setEnd_time_days(int end_time_days) {
            this.end_time_days = end_time_days;
        }
    }

    public class EventAdapter extends RecyclerView.Adapter<ViewHolder>{
        Context context;
        public EventAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_list_item, parent, false);
            ViewHolder evh = new ViewHolder(v);
            return evh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            EventItem eventItem = eventItemArrayList.get(position);

            final Transformation transformation = new RoundedCornersTransformation(25, 0);

            Picasso.get()
                    .load(eventItem.getBanner())
                    .transform(transformation)
                    .into(holder.event_item_img);

            holder.event_item_time.setText(String.valueOf(eventItem.getEnd_time_days())+" "+context.getString(R.string.event_list_day));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventItemWeb(eventItem,context);
                }
            });
        }

        @Override
        public int getItemCount() {
            return eventItemArrayList.size();
        }


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView event_item_time;
        public ImageView event_item_img;
        public ViewHolder(View itemView) {
            super(itemView);

            event_item_img = itemView.findViewById(R.id.event_item_img);
            event_item_time = itemView.findViewById(R.id.event_item_time);
        }


    }

    public SpannableString setSpanAndTv(@ColorInt int mColor, String str, String mWord, int mWordFontSize){
        SpannableString mSpannavleString = new SpannableString(str);

        for (int i = -1; (i = str.indexOf(mWord, i + 1)) != -1; i++) {
            final int end = str.indexOf(mWord) + mWord.length();
            mSpannavleString.setSpan(new ForegroundColorSpan(mColor),str.indexOf(mWord), end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mSpannavleString.setSpan(new AbsoluteSizeSpan(mWordFontSize, true), str.indexOf(mWord), end
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mSpannavleString;
    }
}
