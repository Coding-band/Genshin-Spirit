package com.voc.genshin_helper.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.AlarmUI;
import com.voc.genshin_helper.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.voc.genshin_helper.data.RoundRectImageView.getRoundBitmapByShader;

/**
 * Created by ankit on 27/10/17.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private Context context;
    private List<Alarm> alarmList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;

    SharedPreferences sharedPreferences;
    public AlarmAdapter(Context context, List<Alarm> alarmList) {
        this.context = context;
        this.alarmList = alarmList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm_list, parent, false);
        ViewHolder evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        Log.wtf("CHAR", String.valueOf(alarmList.size()));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm");

        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        long alarm_cnt = sharedPreferences.getLong("alarm_cnt",0);
        for(long x = 0 ; x < alarm_cnt ; x ++){
            Alarm alarms = new Alarm();
            alarms.setTitle(sharedPreferences.getString("alarm"+String.valueOf(x)+"_title","GenshinHpeler"));
            // CONTINUE TMR ...
        }

        holder.ui_title.setText(alarm.getTitle());
        holder.ui_finish_time.setText(simpleDateFormat.format(alarm.getFinish_time()));
        holder.ui_time.setText(simpleDateFormat.format(alarm.getRemain_time()));

        if(alarm.getType() == R.string.alarm_t1){
            holder.ui_progress.setVisibility(View.GONE);
            holder.ui_ico.setImageResource(R.drawable.dream_solvent);
        }else if(alarm.getType() == R.string.alarm_t2){
            holder.ui_ico.setImageResource(R.drawable.fragile_resin);
        }else if(alarm.getType() == R.string.alarm_t3){
            holder.ui_ico.setImageResource(R.drawable.realm_currency);
        }else if(alarm.getType() == R.string.alarm_t4){
            holder.ui_ico.setImageResource(R.drawable.dandelion_seed);
        }else if(alarm.getType() == R.string.alarm_t5){
            holder.ui_ico.setImageResource(R.drawable.primogem);
        }

    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ui_title,ui_time,ui_progress,ui_finish_time;
        public ImageView ui_ico;
        public LinearLayout ui_ll;


        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            ui_ll = itemView.findViewById(R.id.ui_ll);
            ui_title = itemView.findViewById(R.id.ui_title);
            ui_time = itemView.findViewById(R.id.ui_time);
            ui_progress = itemView.findViewById(R.id.ui_progress);
            ui_finish_time = itemView.findViewById(R.id.ui_finish_time);
            ui_ico = itemView.findViewById(R.id.ui_ico);

            ui_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.wtf("is context instanceof AlarmUI ?",context.getPackageName());
                    if (context instanceof AlarmUI){Log.wtf("YES","IT's");
                      //  (((AlarmUI) context)).startInfo(String.valueOf(char_base_name.getText()));

                    }
                }
            });

        }
    }
    public void filterList(List<Alarm> filteredList) {
        alarmList = filteredList;
        notifyDataSetChanged();
    }
}