package com.voc.genshin_helper.ui;

//https://stackoverflow.com/questions/27128425/add-multiple-custom-views-to-layout-programmatically

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.Characters_Rss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.data.Today_Material;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Package com.voc.genshin_helper.ui was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */

public class MainActivity extends AppCompatActivity {

    ViewGroup char_ll;
    ViewGroup weapon_ll;
    BottomNavigationView nav_view;
    Today_Material tm;
    Characters_Rss css;
    //Char Page
    RecyclerView mList;
    CharactersAdapter mAdapter;

    View char_pg,art_pg,home_pg,weapon_pg,setting_pg;

    int dow = 0;

    Context context;

    public boolean show_pyro = true;
    public boolean show_hydro = true;
    public boolean show_anemo = true;
    public boolean show_dendor = true;
    public boolean show_electro = true;
    public boolean show_cryo = true;
    public boolean show_geo = true;

    public boolean show_sword = true;
    public boolean show_claymore = true;
    public boolean show_polearm = true;
    public boolean show_bow = true;
    public boolean show_catalyst = true;

    public int show_stars = 0;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public List<Characters> charactersList = new ArrayList<>();
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        tm = new Today_Material();
        css = new Characters_Rss();

        char_pg = findViewById(R.id.char_pg);
        art_pg = findViewById(R.id.art_pg);
        home_pg = findViewById(R.id.home_pg);
        weapon_pg = findViewById(R.id.weapon_pg);
        setting_pg = findViewById(R.id.setting_pg);
        context = this;

        sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mList = findViewById(R.id.main_list);
        mAdapter = new CharactersAdapter(this, charactersList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);
        mList.removeAllViewsInLayout();

        getDOW();
        char_reload();
        weapon_reload();

        nav_view = findViewById(R.id.nav_view);
        nav_view.setSelectedItemId(R.id.navigation_home);
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_char){
                    char_pg.setVisibility(View.VISIBLE);
                    art_pg.setVisibility(View.GONE);
                    home_pg.setVisibility(View.GONE);
                    weapon_pg.setVisibility(View.GONE);
                    setting_pg.setVisibility(View.GONE);

                    mList.removeAllViewsInLayout();

                    char_list_reload();

                    EditText char_et = findViewById(R.id.char_et);
                    char_et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            ArrayList<Characters> filteredList = new ArrayList<>();
                            int x = 0;
                            for (Characters item : charactersList) {
                                String str = String.valueOf(s).toLowerCase();
                                if (item.getName().toLowerCase().contains(String.valueOf(str))||css.LocaleStr(x,context).contains(String.valueOf(s))||css.LocaleStr(x,context).toLowerCase().contains(String.valueOf(s).toLowerCase())) {
                                    filteredList.add(item);
                                }
                                x = x +1;
                            }
                            mAdapter.filterList(filteredList);
                        }
                    });

                    ImageView char_filter = findViewById(R.id.char_filter);
                    char_filter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                            View view = View.inflate(context, R.layout.menu_char_filter, null);
                            // Elements
                            ImageView pyro = view.findViewById(R.id.pyro_ico);
                            ImageView hydro = view.findViewById(R.id.hydro_ico);
                            ImageView anemo = view.findViewById(R.id.anemo_ico);
                            ImageView electro = view.findViewById(R.id.electro_ico);
                            ImageView dendor = view.findViewById(R.id.dendor_ico);
                            ImageView cryo = view.findViewById(R.id.cryo_ico);
                            ImageView geo = view.findViewById(R.id.geo_ico);
                            // Weapons
                            ImageView ico_sword = view.findViewById(R.id.ico_sword);
                            ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                            ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                            ImageView ico_bow = view.findViewById(R.id.ico_bow);
                            ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                            // Rating
                            RatingBar ratingBar = view.findViewById(R.id.menu_rating);
                            // Function Buttons
                            Button cancel = view.findViewById(R.id.menu_cancel);
                            Button reset = view.findViewById(R.id.menu_reset);
                            Button ok = view.findViewById(R.id.menu_ok);

                            show_pyro = sharedPreferences.getBoolean("show_pyro",true);
                            show_hydro = sharedPreferences.getBoolean("show_hydro",true);
                            show_anemo = sharedPreferences.getBoolean("show_anemo",true);
                            show_electro = sharedPreferences.getBoolean("show_electro",true);
                            show_dendor = sharedPreferences.getBoolean("show_dendor",true);
                            show_cryo = sharedPreferences.getBoolean("show_cryo",true);
                            show_geo = sharedPreferences.getBoolean("show_geo",true);
                            show_sword = sharedPreferences.getBoolean("show_sword",true);
                            show_claymore = sharedPreferences.getBoolean("show_claymore",true);
                            show_polearm = sharedPreferences.getBoolean("show_polearm",true);
                            show_bow = sharedPreferences.getBoolean("show_bow",true);
                            show_catalyst = sharedPreferences.getBoolean("show_catalyst",true);
                            show_catalyst = sharedPreferences.getBoolean("show_catalyst",true);
                            show_stars = sharedPreferences.getInt("char_stars",0);

                            if(show_pyro){show_pyro = true;pyro.setColorFilter(Color.parseColor("#00000000"));}else{show_pyro = false;pyro.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_hydro){show_hydro = true;hydro.setColorFilter(Color.parseColor("#00000000"));}else{show_hydro = false;hydro.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_anemo){show_anemo = true;anemo.setColorFilter(Color.parseColor("#00000000"));}else{show_anemo = false;anemo.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_electro){show_electro = true;electro.setColorFilter(Color.parseColor("#00000000"));}else{show_electro = false;electro.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_dendor){show_dendor = true;dendor.setColorFilter(Color.parseColor("#00000000"));}else{show_dendor = false;dendor.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_cryo){show_cryo = true;cryo.setColorFilter(Color.parseColor("#00000000"));}else{show_cryo = false;cryo.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_geo){show_geo = true;geo.setColorFilter(Color.parseColor("#00000000"));}else{show_geo = false;geo.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_sword){show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}else{show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_claymore){show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}else{show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_polearm){show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}else{show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_bow){show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}else{show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}
                            if(show_catalyst){show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}else{show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}
                            ratingBar.setNumStars(5);
                            ratingBar.setRating(show_stars);

                            pyro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_pyro){show_pyro = false;pyro.setColorFilter(Color.parseColor("#66313131"));}else{show_pyro = true;pyro.setColorFilter(Color.parseColor("#00000000"));}}});
                            hydro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_hydro){show_hydro = false;hydro.setColorFilter(Color.parseColor("#66313131"));}else{show_hydro = true;hydro.setColorFilter(Color.parseColor("#00000000"));}}});
                            anemo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_anemo){show_anemo = false;anemo.setColorFilter(Color.parseColor("#66313131"));}else{show_anemo = true;anemo.setColorFilter(Color.parseColor("#00000000"));}}});
                            electro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_electro){show_electro = false;electro.setColorFilter(Color.parseColor("#66313131"));}else{show_electro = true;electro.setColorFilter(Color.parseColor("#00000000"));}}});
                            dendor.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_dendor){show_dendor = false;dendor.setColorFilter(Color.parseColor("#66313131"));}else{show_dendor = true;dendor.setColorFilter(Color.parseColor("#00000000"));}}});
                            cryo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_cryo){show_cryo = false;cryo.setColorFilter(Color.parseColor("#66313131"));}else{show_cryo = true;cryo.setColorFilter(Color.parseColor("#00000000"));}}});
                            geo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_geo){show_geo = false;geo.setColorFilter(Color.parseColor("#66313131"));}else{show_geo = true;geo.setColorFilter(Color.parseColor("#00000000"));}}});
                            ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}else{show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}}});
                            ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}else{show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}}});
                            ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}else{show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}}});
                            ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}else{show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}}});
                            ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});

                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            reset.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    show_pyro = true;
                                    show_hydro = true;
                                    show_anemo = true;
                                    show_dendor = true;
                                    show_electro = true;
                                    show_cryo = true;
                                    show_geo = true;

                                    show_sword = true;
                                    show_claymore = true;
                                    show_polearm = true;
                                    show_bow = true;
                                    show_catalyst = true;

                                    ratingBar.setRating(0);

                                    editor.putBoolean("show_pyro",show_pyro);
                                    editor.putBoolean("show_hydro",show_hydro);
                                    editor.putBoolean("show_anemo",show_anemo);
                                    editor.putBoolean("show_electro",show_electro);
                                    editor.putBoolean("show_dendor",show_dendor);
                                    editor.putBoolean("show_cryo",show_cryo);
                                    editor.putBoolean("show_geo",show_geo);
                                    editor.putBoolean("show_sword",show_sword);
                                    editor.putBoolean("show_claymore",show_claymore);
                                    editor.putBoolean("show_polearm",show_polearm);
                                    editor.putBoolean("show_bow",show_bow);
                                    editor.putBoolean("show_catalyst",show_catalyst);
                                    editor.putInt("char_stars", (int) ratingBar.getRating());
                                    editor.apply();
                                    dialog.dismiss();

                                    mAdapter.filterList(charactersList);

                                }
                            });

                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ArrayList<Characters> filteredList = new ArrayList<>();
                                    for (Characters item : charactersList) {
                                        if (item.getElement().toLowerCase().equals("pyro") && show_pyro||item.getElement().toLowerCase().equals("hydro") && show_hydro||item.getElement().toLowerCase().equals("anemo") && show_anemo||item.getElement().toLowerCase().equals("electro") && show_electro||item.getElement().toLowerCase().equals("dendor") && show_dendor||item.getElement().toLowerCase().equals("cryo") && show_cryo||item.getElement().toLowerCase().equals("geo") && show_geo) {
                                            if(item.getWeapon().toLowerCase().equals("sword") && show_sword||item.getWeapon().toLowerCase().equals("claymore") && show_claymore||item.getWeapon().toLowerCase().equals("polearm") && show_polearm||item.getWeapon().toLowerCase().equals("bow") && show_bow||item.getWeapon().toLowerCase().equals("catalyst") && show_catalyst){
                                                if(ratingBar.getRating() != 0 && item.getRare() == ratingBar.getRating()){
                                                    filteredList.add(item);
                                                }else if (ratingBar.getRating() == 0){
                                                    filteredList.add(item);
                                                }
                                            }
                                        }
                                    }

                                    mList.removeAllViews();
                                    mAdapter.filterList(filteredList);
                                    editor.putBoolean("show_pyro",show_pyro);
                                    editor.putBoolean("show_hydro",show_hydro);
                                    editor.putBoolean("show_anemo",show_anemo);
                                    editor.putBoolean("show_electro",show_electro);
                                    editor.putBoolean("show_dendor",show_dendor);
                                    editor.putBoolean("show_cryo",show_cryo);
                                    editor.putBoolean("show_geo",show_geo);
                                    editor.putBoolean("show_sword",show_sword);
                                    editor.putBoolean("show_claymore",show_claymore);
                                    editor.putBoolean("show_polearm",show_polearm);
                                    editor.putBoolean("show_bow",show_bow);
                                    editor.putBoolean("show_catalyst",show_catalyst);
                                    editor.putInt("char_stars", (int) ratingBar.getRating());
                                    editor.apply();
                                    dialog.dismiss();
                                }
                            });

                            dialog.setContentView(view);
                            dialog.setCanceledOnTouchOutside(true);
                            //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                            Window dialogWindow = dialog.getWindow();
                            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                            lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            lp.gravity = Gravity.BOTTOM;
                            dialogWindow.setAttributes(lp);
                            dialog.show();
                        }
                    });
                }else if (item.getItemId() == R.id.navigation_artifacts){
                    art_pg.setVisibility(View.VISIBLE);
                    char_pg.setVisibility(View.GONE);
                    home_pg.setVisibility(View.GONE);
                    weapon_pg.setVisibility(View.GONE);
                    setting_pg.setVisibility(View.GONE);
                }else if (item.getItemId() == R.id.navigation_home){
                    home_pg.setVisibility(View.VISIBLE);
                    char_pg.setVisibility(View.GONE);
                    art_pg.setVisibility(View.GONE);
                    weapon_pg.setVisibility(View.GONE);
                    setting_pg.setVisibility(View.GONE);

                    getDOW();
                    char_reload();
                    weapon_reload();

                }else if (item.getItemId() == R.id.navigation_weapons){
                    weapon_pg.setVisibility(View.VISIBLE);
                    char_pg.setVisibility(View.GONE);
                    home_pg.setVisibility(View.GONE);
                    art_pg.setVisibility(View.GONE);
                    setting_pg.setVisibility(View.GONE);
                }else if (item.getItemId() == R.id.navigation_settings){
                    setting_pg.setVisibility(View.VISIBLE);
                    char_pg.setVisibility(View.GONE);
                    home_pg.setVisibility(View.GONE);
                    weapon_pg.setVisibility(View.GONE);
                    art_pg.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void char_list_reload() {
        String name ,element,weapon,nation,sex;
        int rare,isComing;
        charactersList.clear();

        String json_base = LoadData("db/char_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                element = object.getString("element");
                weapon = object.getString("weapon");
                nation = object.getString("nation");
                sex = object.getString("sex");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Characters characters = new Characters();
                characters.setName(name);
                characters.setElement(element);
                characters.setWeapon(weapon);
                characters.setNation(nation);
                characters.setSex(sex);
                characters.setRare(rare);
                characters.setIsComing(isComing);
                charactersList.add(characters);
            }
            mAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showCharDetail (String name){

    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }

    public void getDOW (){
        Calendar c = Calendar.getInstance();
        dow = c.get(Calendar.DAY_OF_WEEK);

        TextView home_date_tv = findViewById(R.id.home_date_tv);
        if(dow == 1){home_date_tv.setText("【"+getString(R.string.sunday)+"】");}
        if(dow == 2){home_date_tv.setText("【"+getString(R.string.monday)+"】");}
        if(dow == 3){home_date_tv.setText("【"+getString(R.string.tuesday)+"】");}
        if(dow == 4){home_date_tv.setText("【"+getString(R.string.wednesday)+"】");}
        if(dow == 5){home_date_tv.setText("【"+getString(R.string.thursday)+"】");}
        if(dow == 6){home_date_tv.setText("【"+getString(R.string.friday)+"】");}
        if(dow == 7){home_date_tv.setText("【"+getString(R.string.saturday)+"】");}

    }

    public void char_reload(){
        char_ll = findViewById(R.id.char_ll);
        char_ll.removeAllViews();
        //Setup item_today_material
        int[] today_IMG = tm.today_char_IMG(dow);
        int[] today_TV = tm.today_char_TV(dow);
        for (int x = 0 ; x < today_IMG.length; x++){
        View char_view = LayoutInflater.from(this).inflate(R.layout.item_today_material, char_ll, false);
        ImageView item_img = char_view.findViewById(R.id.item_img);
        TextView item_name = char_view.findViewById(R.id.item_name);
        TextView item_dow = char_view.findViewById(R.id.item_dow);
        item_name.setText(getString(today_TV[x]));
        item_dow.setText(getString(tm.today_is(today_IMG[x])));
        item_img.setImageResource(today_IMG[x]);
        char_ll.addView(char_view);
        }
    }
    public void weapon_reload(){
        weapon_ll = findViewById(R.id.weapon_ll);
        weapon_ll.removeAllViews();
        //Setup item_today_material
        int[] today_IMG = tm.today_weapon_IMG(dow);
        int[] today_TV = tm.today_weapon_TV(dow);
        for (int x = 0 ; x < today_IMG.length; x++){
        View char_view = LayoutInflater.from(this).inflate(R.layout.item_today_material, weapon_ll, false);
        ImageView item_img = char_view.findViewById(R.id.item_img);
        TextView item_name = char_view.findViewById(R.id.item_name);
        TextView item_dow = char_view.findViewById(R.id.item_dow);
        item_name.setText(getString(today_TV[x]));
        item_dow.setText(getString(tm.today_is(today_IMG[x])));
        item_img.setImageResource(today_IMG[x]);
        weapon_ll.addView(char_view);
        }
    }
    public void startInfo (String name){
        Characters_Info cif = new Characters_Info();
        Log.wtf("YES","IT's two");
        cif.setup(String.valueOf(name),context);
    }

}