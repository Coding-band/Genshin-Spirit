package com.voc.genshin_helper.easter_egg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.util.BackgroundReload;

import java.util.ArrayList;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

public class EasterEgg_LYS extends AppCompatActivity {

    /** UI & System Method*/
    Context context;
    Activity activity;

    ImageView monster_img1,monster_img2,monster_img3,monster_img4;
    ImageView boss_img;
    TextView boss_tv;
    TextView point_tv;
    TextView wave_tv;
    ProgressBar boss_pb;

    /** Game Method*/
    int maxMove = 20;
    int minMove = -20;
    int screenWidth = 0;
    int screenHeight = 0;
    float moveMultiple = 10f;
    LYS lys ;
    ArrayList<LYS> monsterArray = new ArrayList<LYS>();
    String[] monsterNameLib = {"梁日昇"};
    int[] monsterFileNameLib = {R.raw.lys};
    int[] monsterSoundSpawnLib = {R.raw.lys_spawn};
    int[] monsterSoundMovingLib = {R.raw.lys_moving};
    int[] monsterSoundEndLib = {R.raw.lys_end};

    int point = 0;
    int randPos = 0;
    int killed_cnt = 0;
    int wave = 0;
    int[] monsterCnt = {5,10,15,30,60};
    boolean isGameEndByUser = false;

    MediaPlayer monsterMoving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_egg_lys);

        /** Init */
        context = this;
        activity = this;
        lys = new LYS();
        BackgroundReload.BackgroundReload(context,activity);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        monster_img1 = findViewById(R.id.monster_img1);
        monster_img2 = findViewById(R.id.monster_img2);
        monster_img3 = findViewById(R.id.monster_img3);
        monster_img4 = findViewById(R.id.monster_img4);
        boss_img = findViewById(R.id.boss_img);
        boss_pb = findViewById(R.id.boss_pb);
        boss_tv = findViewById(R.id.boss_tv);
        point_tv = findViewById(R.id.point_tv);
        wave_tv = findViewById(R.id.wave_tv);

        refreshText();
        waveToDo();
    }

    public void waveToDo (){
        LYS monster = new LYS();
        randPos = (int) (Math.random() * monsterNameLib.length);
        Picasso.get()
                .load (monsterFileNameLib[randPos])
                .error (R.drawable.paimon_lost)
                .into (monster_img1);

        monster.setType("Monster");
        monster.setName(monsterNameLib[randPos]);
        monster.setHP(1);
        monster.setPoint(1);
        monster.setTv(null);
        monster.setImg(monster_img1);


        refreshText();

        System.out.println("lys.getImg() : "+ String.valueOf(monster.getImg() == null));

        // Add monster into array
        monsterArray.add(monster);

        System.out.println("monsterArray.getImg() : "+ String.valueOf(monsterArray.get(0).getImg() == null));
        System.out.println("monsterArray.getImg() : "+ String.valueOf( monsterArray.get(killed_cnt).getImg() == null));

        // Randomize x,y that monster appear
        randomSpawn(monsterArray.get(killed_cnt));

        // Set listener for letting user knowing
        monsterArray.get(killed_cnt).getImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monsterGotKilled(monster);
            }
        });
    }

    public void randomMove(LYS monster){
        int x = (int) monster.getImg().getX();
        int y = (int) monster.getImg().getY();

        int randX = (int) ((Math.random()*(maxMove)+Math.random()*(minMove))*moveMultiple);
        int randY = (int) ((Math.random()*(maxMove)+Math.random()*(minMove))*moveMultiple);

        if(x+randX>100 && x+randX < screenWidth-100){
            x = x + randX;
        }else if(x+randX<100){
            x = x + Math.abs(randX);
        }else if (x+randX>screenWidth-100){
            x =  x - Math.abs(randX);
        }

        if(y+randY>100 && y+randY < screenHeight-100){
            y = y + randY;
        }else if(y+randY<100){
            y = y + Math.abs(randY);
        }else if (y+randY>screenHeight-100){
            y =  y - Math.abs(randY);
        }

        monster.getImg().setX(x);
        monster.getImg().setY(y);

        System.out.println(monster.getName()+" : ("+x+","+y+")");

        if (monster.isAlive() && isGameEndByUser == false){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    randomMove(monster);
                }}, 100);
        }
    }

    public void randomSpawn(LYS monster){
        int x = (int) (Math.random()*(screenWidth/2));
        int y = (int) (Math.random()*(screenHeight/2));

        System.out.println("monster.getImg() : "+ String.valueOf(monster.getImg() == null));

        monster.getImg().setX(x);
        monster.getImg().setY(y);
        monster.getImg().setVisibility(View.VISIBLE);

        playSpawnSound(monster);
        if (monster.isAlive() && isGameEndByUser == false){
            Handler h = new Handler();
            h.postDelayed(new Runnable(){
                @Override
                public void run() {
                    playMovingSound(monster);
                }}, 5000);
        }
        randomMove(monster);
    }

    public void monsterGotKilled(LYS monster){
        endMovingSound(monster);
        monster.setAlive(false);
        monster.getImg().setVisibility(View.GONE);
        point = point + monster.getPoint();
        killed_cnt = killed_cnt+1;
        if (killed_cnt > monsterCnt[wave]){
            wave = wave+1;
        }
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            @Override
            public void run() {
                waveToDo();
            }}, 3000);
    }

    public void playSpawnSound(LYS monster){
        monsterMoving = null;
        monsterMoving = MediaPlayer.create(context,monsterSoundSpawnLib[randPos]);
        monsterMoving.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                playMovingSound(monster);
            }}, 5000);

    }
    public void playMovingSound(LYS monster){
        monsterMoving = null;
        if (monster.isAlive() && isGameEndByUser == false){
            monsterMoving = MediaPlayer.create(context,monsterSoundMovingLib[randPos]);
            monsterMoving.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    playMovingSound(monster);
                }}, 5000);
        }
    }
    public void endMovingSound(LYS monster){
        monsterMoving = null;
        if (monster.isAlive() && isGameEndByUser == false){
            monsterMoving = MediaPlayer.create(context,monsterSoundEndLib[randPos]);
            monsterMoving.start();
        }
    }

    public void refreshText(){
        point_tv.setText("Point : "+String.valueOf(point));
        wave_tv.setText("Wave : "+String.valueOf(wave+1));
    }

    // Object of monster
    public static class LYS {
        String type = "Monster";
        int HP = 1;
        int point = 1;
        ImageView img;
        TextView tv;
        String name;
        String icon_path;
        boolean isAlive = true;

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean alive) {
            isAlive = alive;
        }

        public String getIconPath() {
            return icon_path;
        }

        public void setIconPath(String icon_path) {
            this.icon_path = icon_path;
        }

        public ImageView getImg() {
            return img;
        }

        public void setImg(ImageView img) {
            this.img = img;
        }

        public TextView getTv() {
            return tv;
        }

        public void setTv(TextView tv) {
            this.tv = tv;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getHP() {
            return HP;
        }

        public void setHP(int HP) {
            this.HP = HP;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            isGameEndByUser=true;
            monsterMoving = null;
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isGameEndByUser=true;
        monsterMoving = null;
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        isGameEndByUser=true;
        monsterMoving = null;
        finish();
    }
}
