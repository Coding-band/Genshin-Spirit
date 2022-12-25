package com.voc.genshin_helper.ui.MMXLVIII;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.util.CustomTextView;
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
    FrameLayout tcg_card_item, tcg_card_hp, tcg_card_dice;
    View tcg_card_include;
    LinearLayout tcg_detail_ll;
    LinearLayout tcg_intro_ll;
    ConstraintLayout tcg_scroll_sc;

    int tcg_width = 1;
    int split = 1;
    DisplayMetrics displayMetrics;

    FrameLayout tcg_card_from_adapter;

    boolean isCardAdapterGONE = false;

    public void setup(FrameLayout tcg_card,TCG tcg,int tcg_width,Context context,Activity activity,SharedPreferences sharedPreferences,SharedPreferences.Editor editor, int[] screenPos) {
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        this.screenPos = screenPos;
        this.tcg = tcg;
        this.tcg_card_from_adapter = tcg_card;
        this.tcg_width = tcg_width;
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

        /** Method of tcg_detail*/
        tcg_card_include = view.findViewById(R.id.tcg_card_include);
        tcg_detail_ll = view.findViewById(R.id.tcg_detail_ll);
        tcg_intro_ll = view.findViewById(R.id.tcg_intro_ll);
        tcg_scroll_sc = view.findViewById(R.id.tcg_scroll_sc);

        /** Method of tcg_card*/
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



        // init tcg_card
        split = 0;
        isCardAdapterGONE = true;
        tcg_card_change();

        tcg_detail_ll.setY(displayMetrics.widthPixels - displayMetrics.density*(120) - ((displayMetrics.widthPixels - displayMetrics.density*(32))/2));
        tcg_intro_ll.setAlpha(0f);
        tcg_detail_ll.setAlpha(0f);
        // init tcg_detail -> ANIMATION
        tcg_card_include.setX(screenPos[0]);
        tcg_card_include.setY(screenPos[1]);
        Animation ani = new ZoomAnimation(
                tcg_card_include,
                (int) (screenPos[0]),
                (int) (screenPos[1]),
                (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2),
                false);
        ani.setDuration(500);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tcg_card_from_adapter.animate()
                        .alpha(0.0f)
                        .setDuration(100)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                tcg_card_from_adapter.setVisibility(View.INVISIBLE);
                                tcg_card_from_adapter.setAlpha(1.0f);
                            }
                        });

                tcg_detail_ll.animate()
                        .alpha(1.0f)
                        .translationY(0)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
                tcg_intro_ll.animate()
                        .alpha(1.0f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tcg_card_include.startAnimation(ani);
        tcg_card_dx();

        // init tcg_detail -> INFORMATION



        /** Method of dialog */
        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (isCardAdapterGONE && tcg_card_from_adapter != null){
                    isCardAdapterGONE = false;

                    tcg_card_change();
                    Animation ani = new ZoomAnimation(
                            tcg_card_include,
                            (int) (screenPos[0]),
                            (int) (screenPos[1]),
                            (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2),
                            true);
                    ani.setDuration(500);
                    ani.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tcg_card_from_adapter.animate()
                                            .alpha(1.0f)
                                            .setDuration(100)
                                            .setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    tcg_card_from_adapter.setVisibility(View.VISIBLE);
                                                    if (dialog != null){
                                                        dialog.dismiss();
                                                    }
                                                }
                                            });
                                }
                            },300);
                            tcg_detail_ll.animate()
                                    .alpha(0.0f)
                                    .translationY(displayMetrics.widthPixels - displayMetrics.density*(120) - ((displayMetrics.widthPixels - displayMetrics.density*(32))/2))
                                    .setDuration(500)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                        }
                                    });
                            tcg_intro_ll.animate()
                                    .alpha(0.0f)
                                    .setDuration(500)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                        }
                                    });
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    tcg_card_include.startAnimation(ani);
                    tcg_card_dx();
                    return true;
                }
                return false;
            }
        };
        dialog.setOnKeyListener(keyListener);
        dialog.setContentView(view);
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
                if (split < 100){
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
        int widthNew;
        View view;
        boolean isZoomOutAnim = false;

        public ZoomAnimation(View view, int fromX, int fromY, int widthNew, boolean isZoomOutAnim) {
            this.view = view;
            this.fromX = fromX;
            this.fromY = fromY;
            this.widthNew = widthNew;
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
            view.setY ((fromY - displayMetrics.density*(60))*(1-interpolatedTime_auth)+ displayMetrics.density*(60));
            view.setX ((fromX - 0)*(1-interpolatedTime_auth));
            layoutParams.height = (int) (tcg_width*12/7 + (widthNew-tcg_width)*12/7*interpolatedTime_auth);
            layoutParams.width = (int) (tcg_width + (widthNew-tcg_width)*interpolatedTime_auth);
            System.out.println("(fromX, fromY, widthNew) : ("+fromX+", "+fromY+", "+widthNew+")");

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
