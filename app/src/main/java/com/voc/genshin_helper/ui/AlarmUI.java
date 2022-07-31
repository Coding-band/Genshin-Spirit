package com.voc.genshin_helper.ui;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Alarm;
import com.voc.genshin_helper.data.AlarmAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.LangUtils;
import com.voc.genshin_helper.util.ReminderBroadcast;
import com.voc.genshin_helper.util.SampleBootReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class AlarmUI extends AppCompatActivity {

    ImageView alarm_add ;
    Context context ;
    // In View
    Spinner type_spinner;
    Spinner ph_spinner; //派遣
    Spinner coin_lvl_spinner; //信任等階
    Spinner coin_grade_spinner; //洞天仙力
    Spinner jb_spinner; //周本
    EditText count_et,title_et,info_et;
    Switch use_switch;
    Switch cd_switch;
    Button alarm_cancel , alarm_ok, alarm_delete ;
    LinearLayout alarm_ph_ll , alarm_dtbc_ll , alarm_jb_ll,alarm_custom_ll;
    TextView alarm_time_picker;

    // Var
    String[] typeList ;
    String[] timeJbList ;
    String[] timePhList ;
    String[] coin_lvl_grade = {"1","2","3","4","5","6","7","8","9","10"};
    String[] coin_grade_grade = {"1","2","3","4","5","6","7","8","9","10"};

    int[] typeListINT = {R.string.alarm_t1,R.string.alarm_t2,R.string.alarm_t3,R.string.alarm_t4,R.string.alarm_t5};
    int[] timePhListINT = {R.string.alarm_time1,R.string.alarm_time2,R.string.alarm_time3,R.string.alarm_time4};
    int[] timeJbListINT = {R.string.sunday,R.string.monday,R.string.tuesday,R.string.wednesday,R.string.thursday,R.string.friday,R.string.saturday};
    int[] coin_lvl_gradeINT = {1,2,3,4,5,6,7,8,9,10};
    int[] coin_grade_gradeINT = {1,2,3,4,5,6,7,8,9,10};

    int[] coin_lvl_max = {300,600,900,1200,1400,1600,1800,2000,2200,2400};
    int[] coin_grade_max = {4,8,12,16,20,22,24,26,28,30};
    int[] timePhList_max = {4,8,12,20};

    int new_choose_type = 0;
    int new_choose_jb = 0;
    int new_choose_ph = 0;
    int new_choose_coin_lvl = 0;
    int new_choose_coin_grade = 0;
    int new_count = 0;
    String new_alarm_title ;
    String new_alarm_info ;
    boolean new_alarm_noti = false;

    int edit_choose_type = 0;
    int edit_choose_jb = 0;
    int edit_choose_ph = 0;
    int edit_choose_coin_lvl = 0;
    int edit_choose_coin_grade = 0;
    int edit_count = 0;
    String edit_alarm_title ;
    String edit_alarm_info ;
    long edit_finish_time;
    long edit_remain_time;
    boolean edit_alarm_noti = false;
    long alarm_count;

    Date time_temp ;
    SharedPreferences sharedPreferences;

    RecyclerView mList;
    AlarmAdapter mAdapter;
    public List<Alarm> alarmList = new ArrayList<>();

    boolean sleep = false ;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ui);
        createNotificationChannel();
        typeList = new String[]{getString(R.string.alarm_t1), getString(R.string.alarm_t2), getString(R.string.alarm_t3), getString(R.string.alarm_t4), getString(R.string.alarm_t5)};
        timePhList = new String[]{getString(R.string.alarm_time1), getString(R.string.alarm_time2), getString(R.string.alarm_time3), getString(R.string.alarm_time4)};
        timeJbList = new String[]{getString(R.string.sunday), getString(R.string.monday), getString(R.string.tuesday), getString(R.string.wednesday), getString(R.string.thursday), getString(R.string.friday), getString(R.string.saturday)};
        context = this;
        activity = this;

        BackgroundReload.BackgroundReload(context,activity);

        sharedPreferences = getSharedPreferences("alarm_records",MODE_PRIVATE);
        mList = findViewById(R.id.alarm_list);
        mAdapter = new AlarmAdapter(this, alarmList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);
        mList.removeAllViewsInLayout();

        refreshAlarmList();

        alarm_add = findViewById(R.id.alarm_add);
        alarm_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_add();
            }
        });
        new_alarm_title = getString(R.string.app_name);
        new_alarm_info = getString(R.string.app_name);

        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked},
                },
                new int[] {
                        context.getResources().getColor(R.color.tv_color),
                        context.getResources().getColor(R.color.tv_color),
                        Color.parseColor(color_hex)
                }
        );

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if(color_hex.toUpperCase().equals("#FFFFFFFF")){
            window.setStatusBarColor(Color.parseColor("#000000"));}
        else {
            window.setStatusBarColor(Color.parseColor(color_hex));
            Log.w("WRF",color_hex);
        }
    }

    public void type_add (){
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_D);
        View view = View.inflate(context, R.layout.menu_alarm_setup, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);

        ArrayAdapter type_aa = new ArrayAdapter(this,R.layout.spinner_item,typeList);
        type_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter time_ph_aa = new ArrayAdapter(this,R.layout.spinner_item,timePhList);
        time_ph_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter time_jb_aa = new ArrayAdapter(this,R.layout.spinner_item,timeJbList);
        time_jb_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter coin_lvl_aa = new ArrayAdapter(this,R.layout.spinner_item,coin_lvl_grade);
        coin_lvl_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter coin_grade_aa = new ArrayAdapter(this,R.layout.spinner_item,coin_grade_grade);
        coin_grade_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Method
        alarm_ph_ll = view.findViewById(R.id.alarm_ph_ll);
        alarm_dtbc_ll = view.findViewById(R.id.alarm_dtbc_ll);
        alarm_jb_ll = view.findViewById(R.id.alarm_jb_ll);
        alarm_custom_ll = view.findViewById(R.id.alarm_custom_ll);

        type_spinner = view.findViewById(R.id.alarm_type_tv);
        ph_spinner = view.findViewById(R.id.alarm_ph_time_tv);
        coin_lvl_spinner = view.findViewById(R.id.alarm_coin_tv);
        coin_grade_spinner = view.findViewById(R.id.alarm_coin_tv2);
        jb_spinner = view.findViewById(R.id.alarm_jb_time_tv);

        count_et = view.findViewById(R.id.alarm_count_et);
        title_et = view.findViewById(R.id.alarm_title_et);
        info_et = view.findViewById(R.id.alarm_info_et);
        alarm_time_picker = view.findViewById(R.id.alarm_time_picker);

        use_switch = view.findViewById(R.id.alarm_use_switch);
        cd_switch = view.findViewById(R.id.alarm_25_switch);

        alarm_ok = view.findViewById(R.id.alarm_ok);
        alarm_cancel = view.findViewById(R.id.alarm_cancel);

        count_et.setVisibility(View.GONE);
        alarm_dtbc_ll.setVisibility(View.GONE);
        alarm_ph_ll.setVisibility(View.GONE);
        alarm_jb_ll.setVisibility(View.GONE);
        alarm_custom_ll.setVisibility(View.GONE);
        cd_switch.setVisibility(View.INVISIBLE);

        type_spinner.setAdapter(type_aa);
        ph_spinner.setAdapter(time_ph_aa);
        coin_lvl_spinner.setAdapter(coin_lvl_aa);
        coin_grade_spinner.setAdapter(coin_grade_aa);
        jb_spinner.setAdapter(time_jb_aa);

        title_et.setText(new_alarm_title);
        info_et.setText(new_alarm_info);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        alarm_time_picker.setText(sdf.format(System.currentTimeMillis()));
        alarm_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(alarm_time_picker);
            }
        });

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new_choose_type = typeListINT[position];
                if(position == 0){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.VISIBLE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))|title_et.getText().toString().equals(getString(R.string.jb_title))|title_et.getText().toString().equals(getString(R.string.resin_title))|title_et.getText().toString().equals(getString(R.string.dtbc_title))|title_et.getText().toString().equals(getString(R.string.ph_title))){title_et.setText(getString(R.string.jb_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))|info_et.getText().toString().equals(getString(R.string.dtbc_info))|info_et.getText().toString().equals(getString(R.string.ph_info))|info_et.getText().toString().equals(getString(R.string.common_info))){info_et.setText(getString(R.string.common_info));}
                }else if(position == 1){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.VISIBLE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))|title_et.getText().toString().equals(getString(R.string.jb_title))|title_et.getText().toString().equals(getString(R.string.resin_title))|title_et.getText().toString().equals(getString(R.string.dtbc_title))|title_et.getText().toString().equals(getString(R.string.ph_title))){title_et.setText(getString(R.string.resin_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))|info_et.getText().toString().equals(getString(R.string.dtbc_info))|info_et.getText().toString().equals(getString(R.string.ph_info))|info_et.getText().toString().equals(getString(R.string.common_info))){info_et.setText(getString(R.string.common_info));}
                }else if(position == 2){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.VISIBLE);
                    alarm_dtbc_ll.setVisibility(View.VISIBLE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))|title_et.getText().toString().equals(getString(R.string.jb_title))|title_et.getText().toString().equals(getString(R.string.resin_title))|title_et.getText().toString().equals(getString(R.string.dtbc_title))|title_et.getText().toString().equals(getString(R.string.ph_title))){title_et.setText(getString(R.string.dtbc_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))|info_et.getText().toString().equals(getString(R.string.dtbc_info))|info_et.getText().toString().equals(getString(R.string.ph_info))|info_et.getText().toString().equals(getString(R.string.common_info))){info_et.setText(getString(R.string.dtbc_info));}
                }else if(position == 3){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    alarm_ph_ll.setVisibility(View.VISIBLE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))|title_et.getText().toString().equals(getString(R.string.jb_title))|title_et.getText().toString().equals(getString(R.string.resin_title))|title_et.getText().toString().equals(getString(R.string.dtbc_title))|title_et.getText().toString().equals(getString(R.string.ph_title))){title_et.setText(getString(R.string.ph_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))|info_et.getText().toString().equals(getString(R.string.dtbc_info))|info_et.getText().toString().equals(getString(R.string.ph_info))|info_et.getText().toString().equals(getString(R.string.common_info))){info_et.setText(getString(R.string.ph_info));}
                }else {
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    alarm_custom_ll.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new_choose_type = typeListINT[0];
            }
        });

        ph_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new_choose_ph = timePhListINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new_choose_ph = timePhListINT[0];
            }
        });

        jb_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new_choose_jb = timeJbListINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new_choose_jb = timeJbListINT[0];
            }
        });

        coin_lvl_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new_choose_coin_lvl = coin_lvl_gradeINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new_choose_coin_lvl = coin_lvl_gradeINT[0];
            }
        });

        coin_grade_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new_choose_coin_grade = coin_grade_gradeINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                new_choose_coin_grade = coin_grade_gradeINT[0];
            }
        });

        alarm_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        alarm_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean title_ok = false;
                boolean info_ok = false;
                boolean count_ok = false;

                if (title_et.getText().toString().equals(" ")|title_et.getText().toString().equals("")| title_et.getText() == null){
                    Toast.makeText(context, "請輸入標題", Toast.LENGTH_SHORT).show();
                }else {new_alarm_title = title_et.getText().toString();title_ok = true;}

                if (info_et.getText().toString().equals(" ")|info_et.getText().toString().equals("")| info_et.getText() == null){
                    Toast.makeText(context, "請輸入內容", Toast.LENGTH_SHORT).show();
                }else {new_alarm_info = info_et.getText().toString();info_ok = true;}

                if(new_choose_type == R.string.alarm_t2 | new_choose_type == R.string.alarm_t3) {
                    if (count_et.getText().toString().equals(" ") | count_et.getText().toString().equals("") | count_et.getText() == null) {
                        Toast.makeText(context, "請輸入數目", Toast.LENGTH_SHORT).show();
                    } else {
                        new_count = Integer.parseInt(count_et.getText().toString());

                        if (new_choose_type == R.string.alarm_t2){
                            if(Integer.parseInt(count_et.getText().toString()) > 159){
                                Toast.makeText(context, "樹脂數目不得多於159個", Toast.LENGTH_SHORT).show();
                            }else {new_count = Integer.parseInt(count_et.getText().toString());count_ok = true;}
                        }else if (new_choose_type == R.string.alarm_t3){
                            if(Integer.parseInt(count_et.getText().toString()) > coin_lvl_max[new_choose_coin_lvl]){
                                Toast.makeText(context, "本等級的洞天寶錢數目不得多於"+String.valueOf(coin_lvl_max[new_choose_coin_lvl])+"個", Toast.LENGTH_SHORT).show();
                            }else {new_count = Integer.parseInt(count_et.getText().toString());count_ok = true;}
                        }else {new_count = 0;count_ok = true;}
                    }
                }else {new_count = 0;count_ok = true;}


                if(use_switch.isChecked()){
                    new_alarm_noti = true;
                }else {new_alarm_noti = false;}

                if (title_ok && info_ok && count_ok){
                    dialog.dismiss();
                    // ADD ALARM

                    Intent intent = new Intent(context, ReminderBroadcast.class);
                    intent.putExtra("title",new_alarm_title);
                    intent.putExtra("info",new_alarm_info);
                    intent.putExtra("type",new_choose_type);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,FLAG_IMMUTABLE);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long finish_time = 0;
                    long require_time = 0;

                    if(new_choose_type == R.string.alarm_t1){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.DAY_OF_WEEK,new_choose_jb+1);
                        calendar.set(Calendar.HOUR_OF_DAY, 6);
                        calendar.set(Calendar.SECOND, 0);
                        require_time = calendar.getTimeInMillis() - System.currentTimeMillis();
                        finish_time = calendar.getTimeInMillis();
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);
                    }
                    else if(new_choose_type == R.string.alarm_t2){
                        require_time = (160-new_count)*8*60*1000;
                        finish_time = System.currentTimeMillis()+require_time;
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+require_time, pendingIntent);
                    }
                    else if(new_choose_type == R.string.alarm_t3){
                        float vars = (coin_lvl_max[new_choose_coin_lvl]-new_count)/coin_grade_max[new_choose_coin_grade];
                        require_time = (int) vars*60*60*1000;
                        finish_time = System.currentTimeMillis()+require_time;
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+require_time, pendingIntent);
                    }
                    else if(new_choose_type == R.string.alarm_t4){
                        int pos = 0;
                        if (new_choose_ph == R.string.alarm_time1){
                            pos = 0;
                        }else if (new_choose_ph == R.string.alarm_time2){
                            pos = 1;
                        }else if (new_choose_ph == R.string.alarm_time3){
                            pos = 2;
                        }else if (new_choose_ph == R.string.alarm_time4) {
                            pos = 3;
                        }
                        require_time = timePhList_max[pos]*60*60*1000;
                        finish_time = System.currentTimeMillis()+require_time;
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+require_time, pendingIntent);
                    }
                    else if(new_choose_type == R.string.alarm_t5){
                        Log.w("CUSTOM","TRUE");
                        finish_time = time_temp.getTime();
                        require_time = time_temp.getTime() - System.currentTimeMillis();
                        alarmManager.set(AlarmManager.RTC_WAKEUP,time_temp.getTime(), pendingIntent);
                        Log.w("CUSTOM_FINISH", String.valueOf(time_temp.getTime()));
                    }

                    // Set SharedPreference
                    long alarm_count = sharedPreferences.getLong("alarm_cnt",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("alarm"+String.valueOf(alarm_count)+"_title",new_alarm_title);
                    editor.putString("alarm"+String.valueOf(alarm_count)+"_info",new_alarm_info);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_type",new_choose_type);
                    editor.putLong("alarm"+String.valueOf(alarm_count)+"_finish_time",finish_time);
                    editor.putLong("alarm"+String.valueOf(alarm_count)+"_remain_time",require_time);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_count",new_count);
                    editor.putLong("alarm"+String.valueOf(alarm_count)+"_id",alarm_count);
                    editor.putLong("alarm_cnt",alarm_count+1);

                    // Function set
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_jb_time",new_choose_jb);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_ph_time",new_choose_ph);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_lvl",new_choose_coin_lvl);//信任等階
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_grade",new_choose_coin_grade);//洞天仙力
                    editor.apply();

                    Alarm alarm = new Alarm();
                    alarm.setId(alarm_count);
                    alarm.setTitle(new_alarm_title);
                    alarm.setInfo(new_alarm_info);
                    alarm.setType(new_choose_type);
                    alarm.setCount(new_count);
                    alarm.setJb_time(new_choose_jb);
                    alarm.setPh_time(new_choose_ph);
                    alarm.setLvl(new_choose_coin_lvl);
                    alarm.setGrade(new_choose_coin_grade);
                    alarm.setFinish_time(finish_time);
                    alarm.setRemain_time(require_time);
                    alarmList.add(alarm);
                    mAdapter.filterList(alarmList);
                }
            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    //https://github.com/hackstarsj/AndroidDatetime_Picker_Dialog/blob/master/app/src/main/java/com/example/androiddatetimepickerdialog/MainActivity.java
    private void showDateTimeDialog(final TextView date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        calendar.set(Calendar.SECOND,0);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        time_temp = calendar.getTime();
                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(AlarmUI.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
            }
        };

        new DatePickerDialog(AlarmUI.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "GenshinHelper";
            String desc = "Channel for Genshin Helper's Reminder function";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("GenshinHelper",name,importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void refreshAlarmList (){
        sharedPreferences = context.getSharedPreferences("alarm_records",Context.MODE_PRIVATE);
        long alarm_cnt = sharedPreferences.getLong("alarm_cnt",0);
        alarmList.clear();
        for(long x = 0 ; x < alarm_cnt ; x ++){
            Alarm alarm = new Alarm();
            if(sharedPreferences.getLong("alarm"+String.valueOf(x)+"_id",-1) != -1) {
                alarm.setTitle(sharedPreferences.getString("alarm" + String.valueOf(x) + "_title", "GenshinHpeler"));
                alarm.setInfo(sharedPreferences.getString("alarm" + String.valueOf(x) + "_info", "GenshinHpeler"));
                alarm.setType(sharedPreferences.getInt("alarm" + String.valueOf(x) + "_type", 0));
                alarm.setCount(sharedPreferences.getInt("alarm" + String.valueOf(x) + "_count", 0));
                alarm.setJb_time(sharedPreferences.getInt("alarm" + String.valueOf(x) + "_jb_time", 0));
                alarm.setPh_time(sharedPreferences.getInt("alarm" + String.valueOf(x) + "_ph_time", 0));
                alarm.setLvl(sharedPreferences.getInt("alarm" + String.valueOf(x) + "_lvl", 0));
                alarm.setGrade(sharedPreferences.getInt("alarm" + String.valueOf(x) + "_grade", 0));
                alarm.setFinish_time(sharedPreferences.getLong("alarm" + String.valueOf(x) + "_finish_time", 0));
                alarm.setRemain_time(sharedPreferences.getLong("alarm" + String.valueOf(x) + "_remain_time", 0));
                alarm.setId(sharedPreferences.getLong("alarm" + String.valueOf(x) + "_id", -1));
                alarmList.add(alarm);
            }
        }
        mAdapter.filterList(alarmList);
        mHandler.sendEmptyMessageDelayed(REFRESH_ALARM_LIST, 1000);
    }
    private static final int REFRESH_ALARM_LIST = 1;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case REFRESH_ALARM_LIST:
                    if (!sleep){
                        refreshAlarmList();
                    }
                    break;

                default:
                    break;
            }
        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            sleep = true ;
            finishAndRemoveTask();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void type_edit (int alarm_count, int real_pos){
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_D);
        View view = View.inflate(context, R.layout.menu_alarm_edit, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);

        ArrayAdapter type_aa = new ArrayAdapter(this,R.layout.spinner_item,typeList);
        type_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter time_ph_aa = new ArrayAdapter(this,R.layout.spinner_item,timePhList);
        time_ph_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter time_jb_aa = new ArrayAdapter(this,R.layout.spinner_item,timeJbList);
        time_jb_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter coin_lvl_aa = new ArrayAdapter(this,R.layout.spinner_item,coin_lvl_grade);
        coin_lvl_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter coin_grade_aa = new ArrayAdapter(this,R.layout.spinner_item,coin_grade_grade);
        coin_grade_aa.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Method
        alarm_ph_ll = view.findViewById(R.id.alarm_ph_ll);
        alarm_dtbc_ll = view.findViewById(R.id.alarm_dtbc_ll);
        alarm_jb_ll = view.findViewById(R.id.alarm_jb_ll);
        alarm_custom_ll = view.findViewById(R.id.alarm_custom_ll);

        type_spinner = view.findViewById(R.id.alarm_type_tv);
        ph_spinner = view.findViewById(R.id.alarm_ph_time_tv);
        coin_lvl_spinner = view.findViewById(R.id.alarm_coin_tv);
        coin_grade_spinner = view.findViewById(R.id.alarm_coin_tv2);
        jb_spinner = view.findViewById(R.id.alarm_jb_time_tv);

        count_et = view.findViewById(R.id.alarm_count_et);
        title_et = view.findViewById(R.id.alarm_title_et);
        info_et = view.findViewById(R.id.alarm_info_et);
        alarm_time_picker = view.findViewById(R.id.alarm_time_picker);

        use_switch = view.findViewById(R.id.alarm_use_switch);
        cd_switch = view.findViewById(R.id.alarm_25_switch);

        alarm_ok = view.findViewById(R.id.alarm_ok);
        alarm_cancel = view.findViewById(R.id.alarm_cancel);
        alarm_delete = view.findViewById(R.id.alarm_delete);

        count_et.setVisibility(View.GONE);
        alarm_dtbc_ll.setVisibility(View.GONE);
        alarm_ph_ll.setVisibility(View.GONE);
        alarm_jb_ll.setVisibility(View.GONE);
        alarm_custom_ll.setVisibility(View.GONE);
        cd_switch.setVisibility(View.INVISIBLE);

        type_spinner.setAdapter(type_aa);
        ph_spinner.setAdapter(time_ph_aa);
        coin_lvl_spinner.setAdapter(coin_lvl_aa);
        coin_grade_spinner.setAdapter(coin_grade_aa);
        jb_spinner.setAdapter(time_jb_aa);

        edit_alarm_title = sharedPreferences.getString("alarm" + String.valueOf(alarm_count) + "_title", "GenshinHpeler");
        edit_alarm_info = sharedPreferences.getString("alarm" + String.valueOf(alarm_count) + "_info", "GenshinHpeler");
        edit_choose_type = sharedPreferences.getInt("alarm" + String.valueOf(alarm_count) + "_type", 0);
        edit_count = sharedPreferences.getInt("alarm" + String.valueOf(alarm_count) + "_count", 0);
        edit_choose_jb = sharedPreferences.getInt("alarm" + String.valueOf(alarm_count) + "_jb_time", 0);
        edit_choose_ph = sharedPreferences.getInt("alarm" + String.valueOf(alarm_count) + "_ph_time", 0);
        edit_choose_coin_lvl = sharedPreferences.getInt("alarm" + String.valueOf(alarm_count) + "_lvl", 0);
        edit_choose_coin_grade = sharedPreferences.getInt("alarm" + String.valueOf(alarm_count) + "_grade", 0);
        edit_finish_time = sharedPreferences.getLong("alarm" + String.valueOf(alarm_count) + "_finish_time", 0);
        edit_remain_time = sharedPreferences.getLong("alarm" + String.valueOf(alarm_count) + "_remain_time", 0);

        title_et.setText(edit_alarm_title);
        info_et.setText(edit_alarm_info);
        int selectionINT = 0;

        if(edit_choose_type == R.string.alarm_t1){
            selectionINT = 0;
        }else if(edit_choose_type == R.string.alarm_t2){
            selectionINT = 1;
        }else if(edit_choose_type == R.string.alarm_t3){
            selectionINT = 2;
        }else if(edit_choose_type == R.string.alarm_t4){
            selectionINT = 3;
        }else if(edit_choose_type == R.string.alarm_t5){
            selectionINT = 4;
        }

        type_spinner.setSelection(selectionINT);

        if(edit_choose_ph == R.string.alarm_time1){
            selectionINT = 0;
        }else if(edit_choose_ph == R.string.alarm_time2){
            selectionINT = 1;
        }else if(edit_choose_ph == R.string.alarm_time3){
            selectionINT = 2;
        }else if(edit_choose_ph == R.string.alarm_time4){
            selectionINT = 3;
        }
        ph_spinner.setSelection(selectionINT);

        coin_lvl_spinner.setSelection(coin_lvl_gradeINT[edit_choose_coin_lvl]);

        coin_grade_spinner.setSelection(coin_grade_gradeINT[edit_choose_coin_grade]);

        if(edit_choose_jb == R.string.sunday){
            selectionINT = 0;
        }else if(edit_choose_jb == R.string.monday){
            selectionINT = 1;
        }else if(edit_choose_jb == R.string.tuesday){
            selectionINT = 2;
        }else if(edit_choose_jb == R.string.wednesday){
            selectionINT = 3;
        }else if(edit_choose_jb == R.string.thursday){
            selectionINT = 4;
        }else if(edit_choose_jb == R.string.friday){
            selectionINT = 5;
        }else if(edit_choose_jb == R.string.saturday){
            selectionINT = 6;
        }
        jb_spinner.setSelection(selectionINT);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        alarm_time_picker.setText(sdf.format(System.currentTimeMillis()));
        alarm_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(alarm_time_picker);
            }
        });

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_choose_type = typeListINT[position];
                if(position == 0){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.VISIBLE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))){title_et.setText(getString(R.string.jb_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))){info_et.setText(getString(R.string.common_info));}
                }else if(position == 1){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.VISIBLE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))){title_et.setText(getString(R.string.resin_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))){info_et.setText(getString(R.string.common_info));}
                }else if(position == 2){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.VISIBLE);
                    alarm_dtbc_ll.setVisibility(View.VISIBLE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))){title_et.setText(getString(R.string.dtbc_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))){info_et.setText(getString(R.string.dtbc_info));}
                }else if(position == 3){
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    count_et.setVisibility(View.VISIBLE);
                    alarm_ph_ll.setVisibility(View.VISIBLE);
                    alarm_custom_ll.setVisibility(View.GONE);
                    if(title_et.getText().toString().equals(getString(R.string.app_name))){title_et.setText(getString(R.string.ph_title));}
                    if(info_et.getText().toString().equals(getString(R.string.app_name))){info_et.setText(getString(R.string.ph_info));}
                }else {
                    count_et.setVisibility(View.GONE);
                    alarm_dtbc_ll.setVisibility(View.GONE);
                    alarm_ph_ll.setVisibility(View.GONE);
                    alarm_jb_ll.setVisibility(View.GONE);
                    cd_switch.setVisibility(View.INVISIBLE);
                    alarm_custom_ll.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edit_choose_type = typeListINT[0];
            }
        });

        ph_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_choose_ph = timePhListINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edit_choose_ph = timePhListINT[0];
            }
        });

        jb_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_choose_jb = timeJbListINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edit_choose_jb = timeJbListINT[0];
            }
        });

        coin_lvl_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_choose_coin_lvl = coin_lvl_gradeINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edit_choose_coin_lvl = coin_lvl_gradeINT[0];
            }
        });

        coin_grade_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edit_choose_coin_grade = coin_grade_gradeINT[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edit_choose_coin_grade = coin_grade_gradeINT[0];
            }
        });

        alarm_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        alarm_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("alarm"+String.valueOf(alarm_count)+"_title");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_info");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_type");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_finish_time");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_remain_time");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_count");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_id");

                // Function set
                editor.remove("alarm"+String.valueOf(alarm_count)+"_jb_time");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_ph_time");
                editor.remove("alarm"+String.valueOf(alarm_count)+"_lvl");//信任等階
                editor.remove("alarm"+String.valueOf(alarm_count)+"_grade");//洞天仙力
                editor.apply();

                alarmList.remove(real_pos);
                mAdapter.filterList(alarmList);
                dialog.dismiss();
            }
        });

        alarm_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean title_ok = false;
                boolean info_ok = false;
                boolean count_ok = false;

                if (title_et.getText().toString().equals(" ")|title_et.getText().toString().equals("")| title_et.getText() == null){
                    Toast.makeText(context, "請輸入標題", Toast.LENGTH_SHORT).show();
                }else {edit_alarm_title = title_et.getText().toString();title_ok = true;}

                if (info_et.getText().toString().equals(" ")|info_et.getText().toString().equals("")| info_et.getText() == null){
                    Toast.makeText(context, "請輸入內容", Toast.LENGTH_SHORT).show();
                }else {edit_alarm_info = info_et.getText().toString();info_ok = true;}

                if(edit_choose_type == R.string.alarm_t2 | edit_choose_type == R.string.alarm_t3) {
                    if (count_et.getText().toString().equals(" ") | count_et.getText().toString().equals("") | count_et.getText() == null) {
                        Toast.makeText(context, "請輸入數目", Toast.LENGTH_SHORT).show();
                    } else {
                        edit_count = Integer.parseInt(count_et.getText().toString());

                        if (edit_choose_type == R.string.alarm_t2){
                            if(Integer.parseInt(count_et.getText().toString()) > 159){
                                Toast.makeText(context, "樹脂數目不得多於159個", Toast.LENGTH_SHORT).show();
                            }else {edit_count = Integer.parseInt(count_et.getText().toString());count_ok = true;}
                        }else if (edit_choose_type == R.string.alarm_t3){
                            if(Integer.parseInt(count_et.getText().toString()) > coin_lvl_max[edit_choose_coin_lvl]){
                                Toast.makeText(context, "本等級的洞天寶錢數目不得多於"+String.valueOf(coin_lvl_max[edit_choose_coin_lvl])+"個", Toast.LENGTH_SHORT).show();
                            }else {edit_count = Integer.parseInt(count_et.getText().toString());count_ok = true;}
                        }else {edit_count = 0;count_ok = true;}
                    }
                }else {edit_count = 0;count_ok = true;}


                if(use_switch.isChecked()){
                    edit_alarm_noti = true;
                }else {edit_alarm_noti = false;}

                if (title_ok && info_ok && count_ok){
                    dialog.dismiss();
                    // ADD ALARM

                    System.out.println(alarmList);
                    Intent intent = new Intent(context, ReminderBroadcast.class);
                    intent.putExtra("title",edit_alarm_title);
                    intent.putExtra("info",edit_alarm_info);
                    intent.putExtra("type",edit_choose_type);

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long finish_time = 0;
                    long require_time = 0;

                    if(edit_choose_type == R.string.alarm_t1){
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.DAY_OF_WEEK,edit_choose_jb+1);
                        calendar.set(Calendar.HOUR_OF_DAY, 6);
                        calendar.set(Calendar.SECOND, 0);
                        require_time = calendar.getTimeInMillis() - System.currentTimeMillis();
                        finish_time = calendar.getTimeInMillis();
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, pendingIntent);
                    }
                    else if(edit_choose_type == R.string.alarm_t2){
                        require_time = (160-edit_count)*8*60*1000;
                        finish_time = System.currentTimeMillis()+require_time;
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+require_time, pendingIntent);
                    }
                    else if(edit_choose_type == R.string.alarm_t3){
                        float vars = (coin_lvl_max[edit_choose_coin_lvl]-edit_count)/coin_grade_max[edit_choose_coin_grade];
                        require_time = (int) vars*60*60*1000;
                        finish_time = System.currentTimeMillis()+require_time;
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+require_time, pendingIntent);
                    }
                    else if(edit_choose_type == R.string.alarm_t4){
                        require_time = timePhList_max[edit_choose_ph]*60*60*1000;
                        finish_time = System.currentTimeMillis()+require_time;
                        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+require_time, pendingIntent);
                    }
                    else if(edit_choose_type == R.string.alarm_t5){
                        Log.w("CUSTOM","TRUE");
                        finish_time = time_temp.getTime();
                        require_time = time_temp.getTime() - System.currentTimeMillis();
                        alarmManager.set(AlarmManager.RTC_WAKEUP,time_temp.getTime(), pendingIntent);
                        Log.w("CUSTOM_FINISH", String.valueOf(time_temp.getTime()));
                    }

                    // Set SharedPreference
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("alarm"+String.valueOf(alarm_count)+"_title",edit_alarm_title);
                    editor.putString("alarm"+String.valueOf(alarm_count)+"_info",edit_alarm_info);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_type",edit_choose_type);
                    editor.putLong("alarm"+String.valueOf(alarm_count)+"_finish_time",finish_time);
                    editor.putLong("alarm"+String.valueOf(alarm_count)+"_remain_time",require_time);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_count",edit_count);
                    editor.putLong("alarm"+String.valueOf(alarm_count)+"_id",alarm_count);

                    // Function set
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_jb_time",edit_choose_jb);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_ph_time",edit_choose_ph);
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_lvl",edit_choose_coin_lvl);//信任等階
                    editor.putInt("alarm"+String.valueOf(alarm_count)+"_grade",edit_choose_coin_grade);//洞天仙力
                    editor.apply();

                    edit_finish_time = finish_time;
                    edit_remain_time = require_time;

                    Alarm alarm = new Alarm();
                    alarm.setId(alarm_count);
                    alarm.setTitle(edit_alarm_title);
                    alarm.setInfo(edit_alarm_info);
                    alarm.setType(edit_choose_type);
                    alarm.setCount(edit_count);
                    alarm.setJb_time(edit_choose_jb);
                    alarm.setPh_time(edit_choose_ph);
                    alarm.setLvl(edit_choose_coin_lvl);
                    alarm.setGrade(edit_choose_coin_grade);
                    alarm.setFinish_time(edit_finish_time);
                    alarm.setRemain_time(edit_remain_time);
                    alarmList.add(alarm);

                    mAdapter.filterList(alarmList);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    notificationManager.cancel("GenshinHelper",(int) alarm_count);

                }
            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences.getInt("curr_lang_pos",2);
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, sharedPreferences.getInt("curr_lang_pos",2)));
    }
}