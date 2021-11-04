package com.voc.genshin_helper.data;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.TOP;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorDBActivity;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.util.ArrayList;
import java.util.List;

/*
 * Package com.voc.genshin_helper.data.CalculatorDBAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */

public class CalculatorDBAdapter extends RecyclerView.Adapter<CalculatorDBAdapter.ViewHolder> {

    private Context context;
    private List<CalculatorDB> dbList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;

    public CalculatorDBAdapter(Context context, List<CalculatorDB> dbList) {
        this.context = context;
        this.dbList = dbList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = this.context;
        View v;
        ViewHolder evh = null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator_db, parent, false);
        evh = new ViewHolder(v, (OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CalculatorDB db = dbList.get(position);
        ItemRss itemRss = new ItemRss();

        holder.name = db.getName();

        holder.db_sheet_tv.setText(db.getName());
        holder.db_sheet_info.setText(
                context.getString(R.string.title_char)+" : "+String.valueOf(db.getChar_cnt())+" / "+
                context.getString(R.string.title_weapons)+" : "+String.valueOf(db.getWeapon_cnt())+" / "+
                context.getString(R.string.title_artifacts)+" : "+String.valueOf(db.getArtifact_cnt())
        );


    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView db_sheet_tv,db_sheet_info;
        public ImageView db_sheet_ico;
        public LinearLayout db_sheet_bg ;
        public String name ;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            db_sheet_ico = itemView.findViewById(R.id.db_sheet_ico);
            db_sheet_tv = itemView.findViewById(R.id.db_sheet_tv);
            db_sheet_tv = itemView.findViewById(R.id.db_sheet_tv);
            db_sheet_bg = itemView.findViewById(R.id.db_sheet_bg);
            db_sheet_info = itemView.findViewById(R.id.db_sheet_info);


            db_sheet_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                        (((CalculatorDBActivity) context)).transferDataToUI(name);
                    }
                }
            });

        }
    }




    public void filterList(List<CalculatorDB> filteredList) {
        dbList = filteredList;
        notifyDataSetChanged();
    }
}