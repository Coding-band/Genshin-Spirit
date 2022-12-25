package com.voc.genshin_helper.ui.MMXLVIII;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.util.CustomTextView;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;

public class TCG_Info_2048 {

    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String itemName = "N/A";
    int[] screenPos = new int[2];
    ItemRss item_rss;
    TCG tcg;


    ImageView tcg_press_mask ;
    TextView tcg_card_name, tcg_card_name_base;
    ImageView tcg_card_img, tcg_card_kwang;
    ImageView tcg_hp_bg, tcg_dice_bg;
    CustomTextView tcg_hp_tv, tcg_dice_tv;
    FrameLayout tcg_card_item, tcg_card_hp, tcg_card_dice, tcg_card;

    int img_width_base = 1;
    int split = 1;
    DisplayMetrics displayMetrics;

    int width, height = 1;
    FrameLayout tcg_card_from_adapter;

    boolean isCardAdapterGONE = false;

    public void setup(String itemName, TCG tcg, Context context, Activity activity, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, int[] screenPos, int width, int height, FrameLayout tcg_card) {
        this.itemName = itemName;
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        this.screenPos = screenPos;
        this.tcg = tcg;
        this.width = width;
        this.height = height;
        this.tcg_card_from_adapter = tcg_card;
        show();
    }

    public void show() {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_tcg_detail, null);
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;
        item_rss = new ItemRss();

        isCardAdapterGONE = true;
        tcg_card_from_adapter.setVisibility(View.INVISIBLE);

        /** Method of tcg_card*/
        tcg_card = view.findViewById(R.id.tcg_card);
        tcg_hp_bg = view.findViewById(R.id.tcg_hp_bg);
        tcg_dice_bg = view.findViewById(R.id.tcg_dice_bg);
        tcg_card_name = view.findViewById(R.id.tcg_card_name);
        tcg_card_name_base = view.findViewById(R.id.tcg_card_name_base);
        tcg_card_img = view.findViewById(R.id.tcg_card_img);
        tcg_card_kwang = view.findViewById(R.id.tcg_card_kwang);
        tcg_hp_tv = view.findViewById(R.id.tcg_hp_tv);
        tcg_dice_tv = view.findViewById(R.id.tcg_dice_tv);
        tcg_card_item = view.findViewById(R.id.tcg_card_item);
        tcg_card_hp = view.findViewById(R.id.tcg_card_hp);
        tcg_card_dice = view.findViewById(R.id.tcg_card_dice);
        tcg_press_mask = view.findViewById(R.id.tcg_press_mask);


        int curr = width_curr;

        int img_width = 320;
        int width_a = (int) (width_curr - displayMetrics.density*(4+4));

        if ((width_a/img_width - (int)(width_a/img_width)) > 0){
            img_width = img_width + ((width_a/img_width - (int)(width_a/img_width)) / (int)(width_a/img_width));
        }

        img_width_base = img_width;
        split = 0;
        tcg_card_change();
        tcg_card_dx();

        if (tcg_card != null){
            Animation ani = new ZoomAnimation(tcg_card,width,height,(int) (displayMetrics.density*(175)), (int) (displayMetrics.density*(300)), false);
            ani.setDuration(500);
            tcg_card.startAnimation(ani);
        }

        DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK) {
                    if (isCardAdapterGONE && tcg_card_from_adapter != null){
                        tcg_card_from_adapter.setVisibility(View.VISIBLE);
                        isCardAdapterGONE = false;
                    }
                }
                return false;
            }
        };


        /** Method of dialog */
        dialog.setOnKeyListener(keylistener );
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048_less));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void tcg_card_dx() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (split < 200){
                    tcg_card_change();
                    tcg_card_dx();
                    split++;
                }
            }
        },50);
    }

    public void tcg_card_change(){
        tcg_card_img.setImageDrawable(FileLoader.loadIMG2Drawable(item_rss.getTCGByName(tcg.getName(),context)[0],context));
        tcg_card_name.setText(item_rss.getTCGByName(tcg.getName(),context)[1]);
        tcg_card_name_base.setText(tcg.getName());
        tcg_press_mask.getLayoutParams().width = (int) (0);
        tcg_press_mask.getLayoutParams().height = (int) (0);

        switch (tcg.getType()){
            case TCG.CHAR:{
                tcg_card_hp.setVisibility(View.VISIBLE);
                tcg_card_dice.setVisibility(View.GONE);

                tcg_hp_tv.setText(String.valueOf(tcg.getHP()));
                break;
            }
            case TCG.EQUIP:
            case TCG.SUPPORT:
            case TCG.EVENT:
            case TCG.BACKSIDE:{
                tcg_card_hp.setVisibility(View.GONE);
                tcg_card_dice.setVisibility(View.VISIBLE);
                int diceType = R.drawable.bg_tcg_dice_specific;

                switch (tcg.getDiceType()){
                    case TCG.Anemo: diceType = R.drawable.bg_tcg_dice_anemo;break;
                    case TCG.Cryo: diceType = R.drawable.bg_tcg_dice_cryo;break;
                    case TCG.Dendro: diceType = R.drawable.bg_tcg_dice_dendro;break;
                    case TCG.Electro: diceType = R.drawable.bg_tcg_dice_electro;break;
                    case TCG.Geo: diceType = R.drawable.bg_tcg_dice_geo;break;
                    case TCG.Hydro: diceType = R.drawable.bg_tcg_dice_hydro;break;
                    case TCG.Pyro: diceType = R.drawable.bg_tcg_dice_pyro;break;
                    case TCG.SPEC: diceType = R.drawable.bg_tcg_dice_specific;break;
                    case TCG.RAND: diceType = R.drawable.bg_tcg_dice_random;break;
                }

                tcg_dice_bg.setImageResource(diceType);
                tcg_dice_tv.setText(String.valueOf(tcg.getDiceCost()));
                break;
            }
        }
    }

    public class ZoomAnimation extends Animation {
        int fromX;
        int fromY;
        int toX;
        int toY;
        View view;
        boolean isZoomOutAnim = false;

        public ZoomAnimation(View view, int fromX, int fromY, int toX, int toY, boolean isZoomOutAnim) {
            this.view = view;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
            this.isZoomOutAnim = isZoomOutAnim;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            float interpolatedTime_auth = interpolatedTime;
            if (isZoomOutAnim){
                interpolatedTime_auth = (1-interpolatedTime);
            }

            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.height = (int) (fromY + (toY - fromY)*interpolatedTime_auth);
            layoutParams.width = (int) (fromX + (toX - fromX)*interpolatedTime_auth);
            view.setLayoutParams(layoutParams);
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}
