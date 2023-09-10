package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.voc.genshin_helper.R;

public class CustomEditTextView extends androidx.appcompat.widget.AppCompatEditText {
    private boolean lock = false;
    private Context context ;
    private ImageView team_search;
    private int iconINT = R.drawable.ic_2048_search_selected;
    private CustomEditTextView customEditTextView;
    private Dialog dialog;
    public CustomEditTextView(Context context) {
        super(context);
    }

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void isLock (boolean lock){
        this.lock = lock;
    }
    public void editTextRealObj (CustomEditTextView customEditTextView){
        this.customEditTextView = customEditTextView;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_BACKSLASH)){
            super.onKeyPreIme(keyCode, event);
            System.out.println("customEditTextView.getText() : TTT"+customEditTextView.getText()+"TTT");
            if(lock && (customEditTextView.getText() != null)){
                if (!customEditTextView.getText().toString().equals("")){
                    return true;
                }else{
                    if (dialog != null){
                        if (dialog.isShowing()){
                            dialog.dismiss();
                            ImageViewAnimatedChange(context, team_search, iconINT);
                        }
                    }
                }
                return false;
            }else{
                if (dialog != null){
                    if (dialog.isShowing()){
                        dialog.dismiss();
                        ImageViewAnimatedChange(context, team_search, iconINT);
                    }
                }
            }
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }


    //https://stackoverflow.com/questions/7161500/creating-animation-on-imageview-while-changing-image-resource
    public static void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {
                v.setImageResource(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
            }
        });
        v.startAnimation(anim_out);
    }

    public void dialogCase(Context context, ImageView team_search, Dialog dialog, int ic_2048_search) {
        this.context = context;
        this.team_search = team_search;
        this.dialog = dialog;
        this.iconINT = ic_2048_search;

    }
}
