package com.voc.genshin_helper.data;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SipTik.CalculatorDB_SipTik;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.CustomTextView;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class TCGAdapter extends RecyclerView.Adapter<TCGAdapter.ViewHolder> {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private Context context;
    private List<TCG> tcgList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;
    private Activity activity ;
    private TCG tcg ;
    private ArrayList<TCG> tcgA = new ArrayList<TCG>();
    private SharedPreferences sharedPreferences;

    public TCGAdapter(Context context, List<TCG> tcgList, Activity activity, SharedPreferences sharedPreferences) {
        this.context = context;
        this.tcgList = tcgList;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artifact_ico_rectangle_2048, parent, false);
        // P.S. TCG will only have one type displayment
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tcg_card, parent, false);
        return new ViewHolder(v, (OnItemClickListener) mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.tcg = tcgList.get(position);
        ItemRss item_rss = new ItemRss();
        int width = 0, height = 0;
        int count = 3;

        int radius = 80;
        int margin = 0;
        Transformation transformation = new RoundedCornersTransformation(radius, margin);

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);
        final int radius_circ_siptik = 80;
        final int margin_circ_siptik = 0;
        final Transformation transformation_circ_siptik = new RoundedCornersTransformation(radius_circ_siptik, margin_circ_siptik);
        final int radius_circ_siptik_ico = 120;
        final int margin_circ_siptik_ico = 0;
        final Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        // Weird
        tcgA.add(tcg);

        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getTCGByName(tcg.getName(),context)[0],context))
                .fit()
                .error (R.drawable.hu_tao_unknown)
                .into (holder.tcg_card_img);

    }

    @Override
    public int getItemCount() {
        return tcgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tcg_press_mask ;
        TextView tcg_card_name, tcg_card_name_base;
        ImageView tcg_card_img, tcg_card_kwang;
        ImageView tcg_hp_bg;
        CustomTextView tcg_hp_tv;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            tcg_hp_bg = itemView.findViewById(R.id.tcg_hp_bg);
            tcg_card_name = itemView.findViewById(R.id.tcg_card_name);
            tcg_card_name_base = itemView.findViewById(R.id.tcg_card_name_base);
            tcg_card_img = itemView.findViewById(R.id.tcg_card_img);
            tcg_card_kwang = itemView.findViewById(R.id.tcg_card_kwang);
            tcg_hp_tv = itemView.findViewById(R.id.tcg_hp_tv);
            tcg_press_mask = itemView.findViewById(R.id.tcg_press_mask);
            tcg_press_mask.startAnimation(buttonClick);

            tcg_press_mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof Desk2048) {
                        (((Desk2048) context)).startCharInfo(String.valueOf(tcg_card_name_base.getText()), activity);
                    }
                }
            });

        }
    }




    public void filterList(List<TCG> filteredList) {
        tcgList = filteredList;
        notifyDataSetChanged();
    }

}